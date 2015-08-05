package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1003 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d:%n", i);
            solve();
//            System.gc();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        V[] vs = new V[n + n - 1];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new V();
        }
        T[] ts = new T[n + n - 1];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new T(i, i < n);
        }
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
//            vs[u].add(i + n);
//            vs[i + n].add(v);
        }
//        bfs(vs, ts);
        for (int i = 0; i < m; i++) {
            char c = in.next().charAt(3);
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int k = in.nextInt();
//            makeRoot(ts[u]);
//            if (c == '1') access(ts[v]).setAddV(k);
//            else access(ts[v]).setAddE(k);
        }
//        for (int i = 0; i < ts.length; i++) {
//            if (ts[i].pre.left != ts[i] && ts[i].pre.right != ts[i]) {
//                push(ts[i]);
//            }
//        }
        for (int i = 0; i < n; i++) {
            if (i != 0) out.print(' ');
            out.print(ts[i].key);
        }
        out.println();
        for (int i = 0; i < n - 1; i++) {
            if (i != 0) out.print(' ');
            out.print(ts[n + i].key);
        }
        out.println();
    }

    private void bfs(V[] vs, T[] ts) {
        int n = vs.length;
        boolean[] vis = new boolean[n];
        vis[0] = true;
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(0);
        while (!que.isEmpty()) {
            int crt = que.poll();
            for (int v : vs[crt]) if (!vis[v]) {
                ts[v].pre = ts[crt];
                vis[v] = true;
                que.add(v);
            }
        }
    }

    private void push(T t) {
        if (t == NULL) return ;
        t.push();
        push(t.left);
        push(t.right);
    }


    class V extends LinkedList<Integer> {

    }


    class T {
        int id;
        boolean v;
        long key;
        long addV;
        long addE;
        boolean rev;
        double p;
        T pre;
        T left;
        T right;

        T(int id, boolean v, long key, long addV, long addE, boolean rev, double p, T pre, T left, T right) {
            this.id = id;
            this.v = v;
            this.key = key;
            this.addV = addV;
            this.addE = addE;
            this.rev = rev;
            this.p = p;
            this.pre = pre;
            this.left = left;
            this.right = right;
        }

        T(int id, boolean v) {
            this(id, v, 0, 0, 0, false, Math.random(), NULL, NULL, NULL);
        }

        T change(T left, T right) {
            this.left = left; left.pre = this;
            this.right = right; right.pre = this;
            return this;
        }

        T setAddV(long a) {
            if (this == NULL) return NULL;
            if (v) key += a;
            addV += a;
            return this;
        }

        T setAddE(long a) {
            if (this == NULL) return NULL;
            if (!v) key += a;
            addE += a;
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
            if (addV != 0) {
                left.setAddV(addV);
                right.setAddV(addV);
                addV = 0;
            }
            if (addE != 0) {
                left.setAddE(addE);
                right.setAddE(addE);
                addE = 0;
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

    void pushDownAllMark(T t) {
        if (t.pre.left == t || t.pre.right == t) pushDownAllMark(t.pre);
        t.push();
    }

    T NULL = new T(0, false);

}
