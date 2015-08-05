package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1011 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0) solve();
    }

    private void solve() {
        int n = in.nextInt();
        T[] ts = new T[n + 1];
        V[] vs = new V[n + 1];
        for (int i = 1; i <= n; i++) {
            vs[i] = new V();
            ts[i] = new T(i, in.nextInt());
        }
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            vs[u].add(v);
            vs[v].add(u);
        }
        bfs(vs, ts);
        int q = in.nextInt();
        while (q-- != 0) {
            int u = in.nextInt();
            int v = in.nextInt();
            int c = in.nextInt();
            makeRoot(ts[v]);
            T t = access(ts[u]);
            out.println(t.ans);
            t.setAdd(c);
        }
    }

    private void bfs(V[] vs, T[] ts) {
        int n = vs.length;
        boolean[] vis = new boolean[n];
        vis[1] = true;
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(1);
        while (!que.isEmpty()) {
            int crt = que.poll();
            for (int v : vs[crt]) if (!vis[v]) {
                ts[v].pre = ts[crt];
                vis[v] = true;
                que.add(v);
            }
        }
    }

    class V extends LinkedList<Integer> {

    }


    T NULL = new T(0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 0, 0, false, 0, null, null, null);
    class T {
        int id;
        int key;
        int min;
        int max;
        int ans;
        int rAns;
        int add;
        boolean rev;
        double p;
        T pre;
        T left;
        T right;

        T(int id, int key, int min, int max, int ans, int rAns, int add, boolean rev, double p, T pre, T left, T right) {
            this.id = id;
            this.key = key;
            this.min = min;
            this.max = max;
            this.ans = ans;
            this.rAns = rAns;
            this.add = add;
            this.rev = rev;
            this.p = p;
            this.pre = pre;
            this.left = left;
            this.right = right;
        }

        T(int id, int val) {
            this(id, val, val, val, 0, 0, 0, false, Math.random(), NULL, NULL, NULL);
        }

        T change(T left, T right) {
            this.left = left; left.pre = this;
            this.right = right; right.pre = this;
            min = Math.min(left.min, right.min);
            min = Math.min(min, key);
            max = Math.max(left.max, right.max);
            max = Math.max(max, key);
            ans = Math.max(left.ans, right.ans);
            ans = Math.max(ans, Math.max(left.max, key) - Math.min(right.min, key));
            rAns = Math.max(left.rAns, right.rAns);
            rAns = Math.max(rAns, Math.max(right.max, key) - Math.min(left.min, key));
            return this;
        }

        T setAdd(int a) {
            if (this == NULL) return NULL;
            add += a;
            min += a;
            max += a;
            key += a;
            return this;
        }

        T setRev() {
            if (this == NULL) return NULL;
            rev ^= true;
            { T t = left; left = right; right = t; }
            { int t = ans; ans = rAns; rAns = t; }
            return this;
        }

        T push() {
            if (rev) {
                left.setRev();
                right.setRev();
                rev ^= true;
            }
            if (add != 0) {
                left.setAdd(add);
                right.setAdd(add);
                add = 0;
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

}
