package main;

import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Task1006 {
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
        }
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        T[] ts = new T[n + 1];
        for (int i = 1; i <= n; i++) {
            ts[i] = new T(i, in.nextInt());
        }
        V[] vs = new V[n + 1];
        for (int i = 1; i <= n; i++) vs[i] = new V();
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            vs[u].add(v);
            vs[v].add(u);
        }
        build(vs, ts);
        while (m-- != 0) {
            int c = in.nextInt();
            if (c == 1) {
                int x = in.nextInt();
                int y = in.nextInt();
                int a = in.nextInt();
                int b = in.nextInt();
                cut(ts[x], ts[y]);
                link(ts[a], ts[b]);
            } else if (c == 2) {
                int a = in.nextInt();
                int b = in.nextInt();
                int x = in.nextInt();
                makeRoot(ts[a]);
                access(ts[b]).setCol(x);
            } else if (c == 3) {
                int a = in.nextInt();
                int b = in.nextInt();
                int d = in.nextInt();
                makeRoot(ts[a]);
                access(ts[b]).setAdd(d);
            } else {
                int a = in.nextInt();
                int b = in.nextInt();
                makeRoot(ts[a]);
//                for (int i = 1; i <= n; i++) {
//                    Algo.debug(ts[i]);
//                }
                T t = access(ts[b]);
//                for (int i = 1; i <= n; i++) {
//                    Algo.debug(ts[i]);
//                }
                out.println(t.max2 == -INF ? "ALL SAME" : t.max2 + " " + t.max2Cnt);
            }
//            for (int i = 1; i <= n; i++) {
//                Algo.debug(ts[i]);
//            }
        }
    }

    private void build(V[] vs, T[] ts) {
        int n = vs.length;
        boolean[] vis = new boolean[n];
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(1);
        vis[1] = true;
        while (!que.isEmpty()) {
            int crt = que.poll();
            for (int v : vs[crt]) if (!vis[v]) {
                ts[v].pre = ts[crt];
                que.add(v);
                vis[v] = true;
            }
        }
    }

    class V extends ArrayList<Integer> {

    }
    final int INF = Integer.MAX_VALUE;

    T NULL = new T(0, -INF, 0, INF, 0, -INF, -INF, 0, 0, false, 0, null, null, null);
    class T {
        int id;
        int key;
        int size;
        int col;
        int add;
        int max1;
        int max2;
        int max1Cnt;
        int max2Cnt;
        boolean rev;
        double p;
        T pre;
        T left;
        T right;

        T(int id, int key, int size, int col, int add, int max1, int max2, int max1Cnt, int max2Cnt, boolean rev, double p, T pre, T left, T right) {
            this.id = id;
            this.key = key;
            this.size = size;
            this.col = col;
            this.add = add;
            this.max1 = max1;
            this.max2 = max2;
            this.max1Cnt = max1Cnt;
            this.max2Cnt = max2Cnt;
            this.rev = rev;
            this.p = p;
            this.pre = pre;
            this.left = left;
            this.right = right;
        }

        T(int id, int key) {
            this(id, key, 1, INF, 0, key, -INF, 1, 0, false, Math.random(), NULL, NULL, NULL);
        }

        T change(T left, T right) {
            this.left = left; left.pre = this;
            this.right = right; right.pre = this;
            size = left.size + right.size + 1;
//            if (col != INF || add != 0) throw new RuntimeException();
            max1 = Math.max(left.max1, right.max1);
            max1 = Math.max(max1, key);
            max1Cnt = 0;
            if (key == max1) max1Cnt++;
            if (this.left.max1 == max1) max1Cnt += this.left.max1Cnt;
            if (this.left.max2 == max1) max1Cnt += this.left.max2Cnt;
            if (this.right.max1 == max1) max1Cnt += this.right.max1Cnt;
            if (this.right.max2 == max1) max1Cnt += this.right.max2Cnt;

            max2 = Math.max(left.max2, right.max2);
            if (left.max1 != max1) max2 = Math.max(max2, left.max1);
            if (right.max1 != max1) max2 = Math.max(max2, right.max1);
            if (key != max1) max2 = Math.max(max2, key);
            max2Cnt = 0;
            if (max2 != -INF) {
                if (key == max2) max2Cnt++;
                if (this.left.max1 == max2) max2Cnt += this.left.max1Cnt;
                if (this.left.max2 == max2) max2Cnt += this.left.max2Cnt;
                if (this.right.max1 == max2) max2Cnt += this.right.max1Cnt;
                if (this.right.max2 == max2) max2Cnt += this.right.max2Cnt;
            }
            return this;
        }

        T setRev() {
            if (this == NULL) return NULL;
            rev ^= true;
            T t = left; left = right; right = t;
            return this;
        }

        T setCol(int c) {
            if (this == NULL) return NULL;
            col = c;
            key = c;
            add = 0;
            max1 = c;
            max1Cnt = left.size + right.size + 1;
            max2 = -INF;
            max2Cnt = 0;
            return this;
        }

        T setAdd(int a) {
            if (this == NULL) return NULL;
            if (col == INF) {
                key += a;
                add += a;
                max1 += a;
                if (max2 != -INF) max2 += a;
            } else {
                col += a;
                key += a;
                max1 += a;
            }
            return this;
        }

        T push() {
            if (rev) {
                left.setRev();
                right.setRev();
                rev ^= true;
            }
            if (col != INF) {
                left.setCol(col);
                right.setCol(col);
                col = INF;
            }
            if (add != 0) {
                left.setAdd(add);
                right.setAdd(add);
                add = 0;
            }
            return this;
        }

        @Override
        public String toString() {
            return "T{" +
                    "id=" + id +
                    ", key=" + key +
                    ", size=" + size +
                    ", col=" + col +
                    ", add=" + add +
                    ", max1=" + max1 +
                    ", max2=" + max2 +
                    ", max1Cnt=" + max1Cnt +
                    ", max2Cnt=" + max2Cnt +
                    ", rev=" + rev +
                    ", pre=" + pre.id +
                    ", left=" + left.id +
                    ", right=" + right.id +
                    '}';
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
