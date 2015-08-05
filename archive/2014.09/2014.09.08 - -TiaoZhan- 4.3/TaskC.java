package main;

import com.shu_mj.ds.BIT;
import com.shu_mj.ds.RMQ;
import com.shu_mj.tpl.Scanner;

import java.io.PrintWriter;
import java.util.*;

public class TaskC {

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        new T2(in, out).run();
    }
}
class T2 implements Runnable {
    Scanner in;
    PrintWriter out;

    T2(Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }
    public void run() {
        int n = in.nextInt();
        int q = in.nextInt();
        int u = in.nextInt() - 1;
        V[] vs = new V[n + n - 1];
        for (int i = 0; i < n + n - 1; i++) {
            vs[i] = new V();
            if (i >= n) {
                int a = in.nextInt() - 1;
                int b = in.nextInt() - 1;
                vs[i].w = in.nextInt();
                vs[a].add(i);
                vs[i].add(a);
                vs[b].add(i);
                vs[i].add(b);
            }
        }
        T[] ts = bfs(vs);
        while (q-- != 0) {
            if (in.nextInt() == 0) {
                int v = in.nextInt() - 1;
                makeRoot(ts[u]);
                out.println(access(ts[v]).sum);
                u = v;
            } else {
                int v = in.nextInt() - 1 + n;
                int w = in.nextInt();
                makeRoot(ts[v]);
                access(ts[v]);
                ts[v].key = ts[v].sum = w;
            }
        }
    }

    private T[] bfs(V[] vs) {
        int n = vs.length;
        T[] ts = new T[n];
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(0);
        ts[0] = new T(0, vs[0].w);
        while (!que.isEmpty()) {
            int crt = que.poll();
            for (int v : vs[crt]) if (ts[v] == null) {
                ts[v] = new T(v, vs[v].w);
                ts[v].pre = ts[crt];
                que.add(v);
            }
        }
        return ts;
    }

    class V extends ArrayList<Integer> {
        int w;
    }

    class T {
        int id;
        int key;
        int sum;
        boolean rev;
        double p;
        T pre;
        T left;
        T right;

        T(int id, int key, int sum, boolean rev, double p, T pre, T left, T right) {
            this.id = id;
            this.key = key;
            this.sum = sum;
            this.rev = rev;
            this.p = p;
            this.pre = pre;
            this.left = left;
            this.right = right;
        }

        T(int id, int key) {
            this(id, key, key, false, Math.random(), NULL, NULL, NULL);
        }

        T change(T left, T right) {
            this.left = left; left.pre = this;
            this.right = right; right.pre = this;
            sum = left.sum + right.sum + key;
            return this;
        }

        T setRev() {
            if (this == NULL) return NULL;
            rev ^= true;
            T t = left; left = right; right = t;
            return this;
        }
        
        T push() {
            if (rev) {
                left.setRev();
                right.setRev();
                rev ^= true;
            }
            return this;
        }
    }
    T merge(T t1, T t2) {
        if (t1 == NULL) return t2;
        if (t2 == NULL) return t1;
        if (t1.p < t2.p) return t1.push().change(t1.left, merge(t1.right, t2));
        return t2.push().change(merge(t1, t2.left), t2.right);
    }

    T[] split(T t) {
        pushDownAllMark(t);
        T[] res = new T[2];
        res[1] = t.right;
        res[0] = t.change(t.left, NULL);
        T tcp = t;
        for (;;) {
            if (t.pre.left == t) {
                t = t.pre;
                res[1] = t.change(res[1], t.right);
            } else if (t.pre.right == t) {
                t = t.pre;
                res[0] = t.change(t.left, res[0]);
            } else {
                res[0].pre = t.pre;
                res[1].pre = tcp;
                return res;
            }
        }
    }

    T access(T t) {
        T last = NULL;
        while (t != NULL) {
            T[] ss = split(t);
            t = ss[0].pre;
            last = merge(ss[0], last);
        }
        last.pre = NULL;
        return last;
    }
    T makeRoot(T t) {
        return access(t).setRev();
    }
    T getRoot(T t) {
        t = access(t);
        while (t.push().left != NULL) t = t.left;
        return t;
    }
    void link(T x, T y) {
        makeRoot(x).pre = y;
    }

    void cut(T x, T y) {
        makeRoot(y);
        access(y);
        while (x.pre.left == x || x.pre.right == x) x = x.pre;
        x.pre = NULL;
    }

    private void pushDownAllMark(T t) {
        if (t.pre.left == t || t.pre.right == t) pushDownAllMark(t.pre);
        t.push();
    }

    T NULL = new T(-1, 0);

}

class Ta implements Runnable {
    Scanner in;
    PrintWriter out;
    private BIT bit;
    private int k;
    private int[] firstEulerId;
    private int[] euler;
    private int[] depth;
    private int n;
    private V[] vs;
    private int[] es;
    private RMQ rmq;
    private int[] w;

    Ta (Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }
    public void run() {
        n = in.nextInt();
        int q = in.nextInt();
        int s = in.nextInt();
        w = new int[n - 1];
        vs = new V[n];
        for (int i = 0; i < n; i++) vs[i] = new V();
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            w[i] = in.nextInt();
            vs[u].add(new E(v, i));
            vs[v].add(new E(u, i));
        }
        init();
        int u = s - 1;
        while (q-- != 0) {
            if (in.nextInt() == 0) {
                int v = in.nextInt() - 1;
                int p = lca(u, v);
                out.println(bit.sum(firstEulerId[p] + 1, firstEulerId[u] + 1) + bit.sum(firstEulerId[p] + 1, firstEulerId[v] + 1));
                u = v;
            } else {
                int i = in.nextInt() - 1;
                int c = in.nextInt();
                bit.add(es[i * 2], c - w[i]);
                bit.add(es[i * 2 + 1], w[i] - c);
                w[i] = c;
            }
        }
    }

    private void init() {
        bit = new BIT(n * 2 - 1);
        k = 0;
        firstEulerId = new int[n];
        euler = new int[n * 2 - 1];
        depth = new int[n * 2 - 1];
        es = new int[(n - 1) * 2];
        dfs(n / 2, -1, 0);
        rmq = new RMQ(depth);
    }

    private int lca(int u, int v) {
        return euler[rmq.query(Math.min(firstEulerId[u], firstEulerId[v]), Math.max(firstEulerId[u], firstEulerId[v]))];
    }

    private void dfs(int u, int fa, int dep) {
        firstEulerId[u] = k;

        euler[k] = u;
        depth[k] = dep;
        k++;

        for (E e : vs[u]) if (e.to != fa) {
            bit.add(k, w[e.eid]);
            es[e.eid * 2] = k;
            dfs(e.to, u, dep + 1);
            bit.add(k, -w[e.eid]);
            es[e.eid * 2 + 1] = k;
            euler[k] = u;
            depth[k] = dep;
            k++;

        }
    }

    class E {
        int to;
        int eid;

        E(int to, int eid) {
            this.to = to;
            this.eid = eid;
        }
    }
    class V extends ArrayList<E> {

    }
}
