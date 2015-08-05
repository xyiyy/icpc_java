package main;

import com.shu_mj.graph.SCC;
import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        SCC.V[] vs = new SCC.V[n];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new SCC.V();
        }
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            while (x != 0) {
                vs[i].add(vs[x - 1]);
                x = in.nextInt();
            }
        }
        n = SCC.scc(vs);
        if (n == 1) {
            out.println(1);
            out.println(0);
            return ;
        }
        T[] ts = new T[n];
        T[] rs = new T[n];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new T();
            rs[i] = new T();
        }
        for (SCC.V u : vs) {
            for (SCC.V v : u.fs) {
                ts[u.comp].add(ts[v.comp]);
                rs[v.comp].add(rs[u.comp]);
            }
        }
        int ansA = 0;
        for (T t : ts) if (!t.vis) {
            ansA++;
            dfs(t);
        }
        int ansB = 0;
        Algo.reverse(rs);
        for (T t : rs) if (!t.vis) {
            ansB++;
            dfs(t);
        }
        out.println(ansA);
        out.println(Math.max(ansA, ansB));
    }

    private void dfs(T t) {
        t.vis = true;
        for (T t0 : t) if (!t0.vis) dfs(t0);
    }

    class T extends ArrayList<T> {
        boolean vis;
    }
}
