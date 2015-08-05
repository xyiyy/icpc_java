package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;
    private int q;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            solve(n);
            out.println(".");
        }
    }

    private void solve(int n) {
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) vs[i] = new V();
        for (int i = 0; i < n; i++) {
            for (;;) {
                int d = in.nextInt();
                if (d == 0) break;
                int c = in.nextInt();
                d--;
                vs[i].es.add(new E(vs[d], c));
                vs[d].es.add(new E(vs[i], c));
            }
        }
        dfs(vs[0], null, 0);
        dfsSet(vs[0], null);
        for (;;) {
            q = in.nextInt();
            if (q == 0) break;
            out.println(go(vs[0], null) ? "AYE" : "NAY");
        }
    }

    private void dfsSet(V v, V fa) {
        if (v.son != null) {
            dfsSet(v.son.to, v);
            v.set = new HashSet<Integer>(v.son.to.set);
        } else {
            v.set = new HashSet<Integer>();
        }
        v.set.add(v.dep);
        for (E e : v.es) if (e.to != fa && e != v.son) {
            dfsSet(e.to, v);
            v.set.addAll(e.to.set);
        }
    }

    private boolean go(V v, V fa) {
        if (v.set.contains(q + v.dep)) return true;
        for (E e : v.es) if (e.to != fa && e != v.son) {
            if (go(e.to, v)) return true;
            for (int d : e.to.set) {
                if (v.set.contains(q + v.dep * 2 - d)) return true;
            }
        }
        return false;
    }

    private void dfs(V v, V fa, int dep) {
        for (E e : v.es) if (e.to != fa) {
            dfs(e.to, v, dep + e.cost);
            if (v.son == null || v.son.to.size < e.to.size) v.son = e;
            v.size += e.to.size;
        }
        v.dep = dep;
    }

    class V {
        int dep;
        int size;
        E son;
        HashSet<Integer> set;
        ArrayList<E> es = new ArrayList<E>();
    }

    class E {
        V to;
        int cost;

        E(V to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

}
