package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
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
            int k = in.nextInt();
            if (n == 0 && k == 0) break;
            solve(n, k);
        }
    }

    int k;
    private void solve(int n, int k) {
        this.k = k;
        V[] vs = new V[n];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new V();
        }
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int d = in.nextInt();
            vs[u].es.add(new E(vs[v], d));
            vs[v].es.add(new E(vs[u], d));
        }
        dfs(vs[0], null, 0);
        out.println(go(vs[0], null));
    }

    private int go(V v, V fa) {
        if (v.son == null) {
            return 0;
        }
        int res = 0;
        res += go(v.son.to, v);
        res += query(v.son.to, v.depth + k);
        v.t = insert(v.son.to.t, v.t);
        for (E e : v.es) if (e.to != fa && e.to != v.son.to) {
            res += go(e.to, v);
            int n = e.to.t.size;
            toArray(e.to.t);
            for (int i = 0; i < n; i++) {
                T t = ts[i];
                res += query(v, 2 * v.depth + k - t.key);
            }
            for (int i = 0; i < n; i++) {
                T t = ts[i];
                v.t = insert(v.t, t);
            }
        }
        return res;
    }

    private T insert(T t1, T t2) {
        T[] ss = splitKey(t1, t2.key);
        return merge(merge(ss[0], t2), ss[1]);
    }

    private int query(V v, int k) {
        T[] ss = splitKey(v.t, k);
        int res = ss[0].size;
        v.t = merge(ss[0], ss[1]);
        return res;
    }

    T[] ts = new T[100000];
    int id;

    void toArray(T t) {
        id = 0;
        inOrder(t);
    }

    private void inOrder(T t) {
        if (t == NULL) return ;
        inOrder(t.left);
        T right = t.right;
        t.change(NULL, NULL);
        ts[id++] = t;
        inOrder(right);
    }

    private void dfs(V v, V fa, int dep) {
        v.size = 1;
        v.t = new T(dep);
        v.depth = dep;
        for (E e : v.es) if (e.to != fa) {
            dfs(e.to, v, dep + e.dis);
            v.size += e.to.size;
            if (v.son == null || v.son.to.size < e.to.size) {
                v.son = e;
            }
        }
    }

    class V {
        List<E> es = new ArrayList<E>();
        E son;
        int size;
        int depth;
        T t;
    }

    class E {
        V to;
        int dis;

        E(V to, int dis) {
            this.to = to;
            this.dis = dis;
        }
    }

    class T {
        int key, size;
        double p;
        T left, right;

        T(int key, int size, double p, T left, T right) {
            this.key = key;
            this.size = size;
            this.p = p;
            this.left = left;
            this.right = right;
        }

        T(int key) {
            this(key, 1, Math.random(), NULL, NULL);
        }

        T change(T left, T right) {
            size = left.size + right.size + 1;
            this.left = left;
            this.right = right;
            return this;
        }
        T push() {
            if (this != NULL) {

            }
            return this;
        }
    }

    T NULL = new T(0, 0, 0, null, null);

    T[] splitSize(T t, int size) {
        T[] res;
        if (size <= 0) {
            res = new T[] { NULL, t };
        } else if (size <= t.push().left.size) {
            res = splitSize(t.left, size);
            res[1] = t.change(res[1], t.right);
        } else {
            res = splitSize(t.right, size - t.left.size - 1);
            res[0] = t.change(t.left, res[0]);
        }
        return res;
    }

    T[] splitKey(T t, int key) {
        T[] res;
        if (t == NULL) {
            res = new T[] { NULL, NULL };
        } else if (key < t.push().key) {
            res = splitKey(t.left, key);
            res[1] = t.change(res[1], t.right);
        } else {
            res = splitKey(t.right, key);
            res[0] = t.change(t.left, res[0]);
        }
        return res;
    }

    void print(T t, String indent) {
        if (t != NULL) {
            print(t.push().right, indent + "      ");
            System.err.printf("%s%3d%3d%n", indent, t.key, t.size);
            print(t.left, indent + "      ");
        }
        if (indent.length() == 0)
            System.err.println("----------------------------------");
    }

    T merge(T t1, T t2) {
        if (t1 == NULL) return t2;
        if (t2 == NULL) return t1;
        if (t1.p < t2.p) return t1.push().change(t1.left, merge(t1.right, t2));
        return t2.push().change(merge(t1, t2.left), t2.right);
    }

}
