package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskJ {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        T[] ts = new T[n + 1];
        V[] vs = new V[n + 1];
        for (int i = 1; i <= n; i++) {
            ts[i] = new T(i, in.nextInt());
            vs[i] = new V();
        }
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            vs[u].add(v);
            vs[v].add(u);
        }
        bfs(ts, vs);
        int q = in.nextInt();
        while (q-- != 0) {
            int u = in.nextInt();
            int v = in.nextInt();
            makeRoot(ts[v]);
            out.println(access(ts[u]).ans);
        }
    }

    private void bfs(T[] ts, V[] vs) {
        int n = ts.length;
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(1);
        boolean[] vis = new boolean[n];
        vis[1] = true;
        while (!que.isEmpty()) {
            int crt = que.poll();
            for (int v : vs[crt]) if (!vis[v]) {
                vis[v] = true;
                ts[v].pre = ts[crt];
                que.add(v);
            }
        }
    }

    class V extends ArrayList<Integer> {

    }

    final int INF = Integer.MAX_VALUE / 4;
    T NULL = new T(0, 0, INF, -INF, 0, 0, false, 0, null, null, null);
    class T {
        int id;
        int key;
        int min;
        int max;
        int ans;
        int rAns;
        boolean rev;
        double p;
        T pre;
        T left;
        T right;

        T(int id, int key, int min, int max, int ans, int rAns, boolean rev, double p, T pre, T left, T right) {
            this.id = id;
            this.key = key;
            this.min = min;
            this.max = max;
            this.ans = ans;
            this.rAns = rAns;
            this.rev = rev;
            this.p = p;
            this.pre = pre;
            this.left = left;
            this.right = right;
        }

        T(int id, int key) {
            this(id, key, key, key, 0, 0, false, Math.random(), NULL, NULL, NULL);
        }

        T change(T left, T right) {
            this.left = left; left.pre = this;
            this.right = right; right.pre = this;
            min = Math.min(left.min, right.min);
            min = Math.min(key, min);
            max = Math.max(left.max, right.max);
            max = Math.max(key, max);
            ans = Math.max(left.ans, right.ans);
            rAns = Math.max(left.rAns, right.rAns);
            if (right != NULL) {
                ans = Math.max(ans, Math.max(left.max, key) - right.min);
                rAns = Math.max(rAns, right.max - Math.min(left.min, key));
            }
            if (left != NULL) {
                ans = Math.max(ans, left.max - Math.min(key, right.min));
                rAns = Math.max(rAns, Math.max(right.max, key) - left.min);
            }
            return this;
        }

        T setRev() {
            if (this == NULL) return NULL;
            rev ^= true;
            T t = left; left = right; right = t;
            int a = ans; ans = rAns; rAns = a;
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

}
