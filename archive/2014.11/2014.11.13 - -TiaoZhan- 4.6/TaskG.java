package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        V[] vs = new V[n + 1];
        T[] ts = new T[n + 1];
        for (int i = 1; i <= n; i++) {
            vs[i] = new V();
            ts[i] = new T(i, INF);
        }
        for (int i = 1; i < n; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            vs[u].add(v);
            vs[v].add(u);
        }
        bfs(vs, ts);
        int q = in.nextInt();
        while (q-- > 0) {
            int t = in.nextInt();
            int v = in.nextInt();
            if (t == 0) {
                access(ts[v]);
                int old = ts[v].key;
                dec(ts[v].set, old);
                int now = old < INF ? INF : 0;
                ts[v].key = now;
                inc(ts[v].set, now);
                update(ts[v]);
            } else {
                int ans = access(ts[v]).bottom;
                out.println(ans < INF ? ans : -1);
            }
        }
    }

    private void bfs(V[] vs, T[] ts) {
        dfs(vs, ts, 1, 0);
    }

    private void dfs(V[] vs, T[] ts, int v, int fa) {
        for (int u : vs[v]) if (u != fa) {
            dfs(vs, ts, u, v);
            ts[u].pre = ts[v];
            inc(ts[v].set, ts[u].top);
        }
        ts[v].change(ts[v].left, ts[v].right);
    }

    class V extends ArrayList<Integer> {

    }

    final int INF = 12341234;
    T NULL = new T(0, INF, INF, INF, 0, 0, null, null, null);
    class T {
        int id;
        int key;
        int top;
        int bottom;
        int size;
        TreeMap<Integer, Integer> set = new TreeMap<Integer, Integer>();
        double p;
        T pre;
        T left;
        T right;

        T(int id, int key, int top, int bottom, int size, double p, T pre, T left, T right) {
            this.id = id;
            this.key = key;
            this.top = top;
            this.bottom = bottom;
            this.size = size;
            this.p = p;
            this.pre = pre;
            this.left = left;
            this.right = right;
            inc(set, key);
        }

        T(int id, int key) {
            this(id, key, key, key, 1, Math.random(), NULL, NULL, NULL);
        }

        T change(T left, T right) {
            this.left = left; left.pre = this;
            this.right = right; right.pre = this;
            size = left.size + right.size + 1;
            int min = Math.min(set.firstKey() + 1, key);
            top = Math.min(left.top, left.size + Math.min(min, right.top + 1));
            bottom = Math.min(right.bottom, right.size + Math.min(min, left.bottom + 1));
            return this;
        }

        @Override
        public String toString() {
            return "" + id + ": "
                    + "P " + pre.id
                    + ", L " + left.id
                    + ", R " + right.id
                    + ", K " + key
                    + ", T " + top
                    + ", B " + bottom
                    + ", " + set;
        }
    }

    private void inc(TreeMap<Integer, Integer> set, int key) {
        if (!set.containsKey(key)) set.put(key, 1);
        else set.put(key, set.get(key) + 1);
    }

    private void dec(TreeMap<Integer, Integer> set, int key) {
        if (set.get(key) == 1) set.remove(key);
        else set.put(key, set.get(key) - 1);
    }

    T merge(T t1, T t2) {
        if (t1 == NULL) return t2;
        if (t2 == NULL) return t1;
        if (t1.p < t2.p) return t1.change(t1.left, merge(t1.right, t2));
        return t2.change(merge(t1, t2.left), t2.right);
    }

    T[] split(T t) {
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
            if (ss[1] != NULL) {
                inc(ss[1].pre.set, ss[1].top);
            }
            if (last != NULL) {
                dec(ss[1].pre.set, last.top);
            }
            update(ss[1].pre);
            t = ss[0].pre;
            last = merge(ss[0], last);
        }
        last.pre = NULL;
        return last;
    }

    private void update(T t) {
        t.change(t.left, t.right);
        if (t.pre.right == t || t.pre.left == t) update(t.pre);
    }

}
