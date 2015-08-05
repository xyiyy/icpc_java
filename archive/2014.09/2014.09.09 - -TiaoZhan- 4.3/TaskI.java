package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskI {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        V[] vs = new V[n + m + 1];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new V();
            if (i > n) {
                int u = in.nextInt();
                int v = in.nextInt();
                vs[i].w = in.nextInt();
                in.next();
                vs[u].add(i);
                vs[i].add(u);
                vs[v].add(i);
                vs[i].add(v);
            }
        }
        T[] ts = bfs(vs);
        int q = in.nextInt();
        while (q-- != 0) {
            int u = in.nextInt();
            int v = in.nextInt();
            makeRoot(ts[u]);
            out.println(access(ts[v]).sum);
        }
    }

    private T[] bfs(V[] vs) {
        int n = vs.length;
        T[] ts = new T[n];
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(1);
        ts[1] = new T(1, vs[1].w);
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

    T NULL = new T(0, 0);

}
