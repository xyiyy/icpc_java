package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task5029 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            if (n == 0 && m == 0) break;
            solve(n, m);
        }
    }

    private void solve(int n, int m) {
        V[] vs = new V[n];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new V();
        }
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vs[u].add(v);
            vs[v].add(u);
        }
        LCA lca = new LCA(vs, 0);
        V[] line = new V[n];
        V[] point = new V[n];
        for (int i = 0; i < n; i++) {
            line[i] = new V();
            point[i] = new V();
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int c = in.nextInt();
            int p = lca.lca(u, v);
            if (u != p) {
                line[u].add(c);
                line[p].add(-c);
            }
            if (v != p) {
                line[v].add(c);
                line[p].add(-c);
            }
            point[p].add(c);
        }
        int[] ans = new int[n];
        dfs(vs, 0, -1, line, point, ans);
        for (int i : ans) {
            out.println(i);
        }
    }

    T dfs(V[] vs, int v, int p, V[] line, V[] point, int[] ans) {
        T t = NULL;
        for (int u : vs[v]) if (u != p) {
            t = merge2(dfs(vs, u, v, line, point, ans), t);
        }
        for (int i : line[v]) {
            if (i > 0) {
                t = insert(t, i, 1);
            } else {
                t = remove(t, -i, 1);
            }
        }
        for (int i : point[v]) {
            t = insert(t, i, 1);
        }
        ans[v] = t.max.key;
        for (int i : point[v]) {
            t = remove(t, i, 1);
        }
        return t;
    }
    T merge2(T a, T b) {
        if (a.size > b.size) return merge2(b, a);
        return postOrder(a, b);
    }
    T postOrder(T a, T b) {
        if (a == NULL) return b;
        b = postOrder(a.left, b);
        b = postOrder(a.right, b);
        return insert(b, a.change(NULL, NULL));
    }
    T insert(T t, int key, int val) {
        /*
        T[] ss = splitKey(t, key);
        T[] ss0 = splitKey(ss[0], key - 1);
        if (ss0[1] == NULL) {
            ss0[1] = new T(key, val);
        } else {
            ss0[1].val += val;
        }
        return merge(merge(ss0[0], ss0[1]), ss[1]);
        */
        return put(t, key, val);
    }

    T remove(T t, int key, int val) {
        /*
        T[] ss = splitKey(t, key);
        T[] ss0 = splitKey(ss[0], key - 1);
        ss0[1].val -= val;
        if (ss0[1].val == 0) return merge(ss0[0], ss[1]);
        return merge(merge(ss0[0], ss0[1]), ss[1]);
        */
        return remove(t, key);
    }
    T insert(T t, T i) {
        /*
        T[] ss = splitKey(t, i.key);
        T[] ss0 = splitKey(ss[0], i.key - 1);
        if (ss0[1] != NULL) ss0[1].val += i.val;
        else ss0[1] = i;
        return merge(merge(ss0[0], ss0[1]), ss[1]);
        */
        return put(t, i.key, i.val);
    }
    T max(T a, T b) {
        if (a == NULL) return b;
        if (b == NULL) return a;
        if (a.val > b.val || a.val == b.val && a.key < b.key) return a;
        return b;
    }
    class T {
        int key;
        int val;
        T max;
        int size;
        double p;
        T left, right;

        T(int key, int val, T max, int size, double p, T left, T right) {
            this.key = key;
            this.val = val;
            this.max = max;
            this.size = size;
            this.p = p;
            this.left = left;
            this.right = right;
        }

        T(int key, int val) {
            this(key, val, NULL, 1, Math.random(), NULL, NULL);
            max = this;
        }

        T change(T left, T right) {
            this.left = left;
            this.right = right;
            size = left.size + right.size + 1;
            max = max(max(left.max, right.max), this);
            return this;
        }

        T normal() {
            if (left != NULL && left.p < p && (right == null || left.p < right.p)) {
                return left.change(left.left, change(left.right, right));
            } else if (right != NULL && right.p < p) {
                return right.change(change(left, right.left), right.right);
            }
            return this;
        }
    }

    T put(T t, int key, int val) {
        if (t == NULL) return new T(key, val);
        if (key < t.key) return t.change(put(t.left, key, val), t.right).normal();
        if (key > t.key) return t.change(t.left, put(t.right, key, val)).normal();
        t.val += val;
        return t.change(t.left, t.right);
    }

    T remove(T t, int key) {
        if (t == NULL) return NULL;
        if (key < t.key) return t.change(remove(t.left, key), t.right);
        if (key > t.key) return t.change(t.left, remove(t.right, key));
        t.val--;
        if (t.val == 0) return merge(t.left, t.right);
        return t.change(t.left, t.right);
    }

    T NULL = new T(0, 0); {
        NULL.size = 0;
    }

    T[] splitKey(T t, int key) {
        T[] res;
        if (t == NULL) {
            res = new T[] { NULL, NULL };
        } else if (key < t.key) {
            res = splitKey(t.left, key);
            res[1] = t.change(res[1], t.right);
        } else {
            res = splitKey(t.right, key);
            res[0] = t.change(t.left, res[0]);
        }
        return res;
    }

    T merge(T t1, T t2) {
        if (t1 == NULL) return t2;
        if (t2 == NULL) return t1;
        if (t1.p < t2.p) return t1.change(t1.left, merge(t1.right, t2));
        return t2.change(merge(t1, t2.left), t2.right);
    }

}
class V extends ArrayList<Integer> {

}
class LCA {
    List<Integer>[] vs;
    int root;
    int[] depth;
    int[][] pre;

    LCA(List<Integer>[] vs, int root) {
        this.vs = vs;
        this.root = root;
        int n = vs.length;
        depth = new int[n];
        pre = new int[Algo.log2(n) + 1][n];
        dfs(root, -1, 0);
        for (int k = 0; k + 1 < pre.length; k++) {
            for (int v = 0; v < n; v++) {
                if (pre[k][v] < 0) pre[k + 1][v] = -1;
                else pre[k + 1][v] = pre[k][pre[k][v]];
            }
        }
    }
    void dfs(int v, int p, int d) {
        pre[0][v] = p;
        depth[v] = d;
        for (int u : vs[v]) if (u != p) {
            dfs(u, v, d + 1);
        }
    }

    int lca(int u, int v) {
        if (depth[u] > depth[v]) { int t = u; u = v; v = t; }
        v = climb(v, depth[v] - depth[u]);
        if (u == v) return u;
        for (int k = pre.length - 1; k >= 0; k--) {
            if (pre[k][u] != pre[k][v]) {
                u = pre[k][u];
                v = pre[k][v];
            }
        }
        return pre[0][u];
    }

    int climb(int v, int d) {
        for (int k = 0; k < pre.length; k++) {
            if ((d >> k & 1) != 0) v = pre[k][v];
        }
        return v;
    }
}
