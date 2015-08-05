package main;

import com.shu_mj.graph.SCC;
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
        int n = in.nextInt();
        int m = in.nextInt();
        SCC.V[] vs = new SCC.V[n];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new SCC.V();
        }
        while (m-- != 0) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vs[u].add(vs[v]);
        }
        n = SCC.scc(vs);
        int ans = 0;
        SCC.V u = null;
        for (SCC.V v : vs) if (v.comp == n - 1) {
            ans++;
            u = v;
        }
        for (SCC.V v : vs) v.visit = false;
        SCC.dfsRev(u, 0);
        for (SCC.V v : vs) if (!v.visit) {
            out.println(0);
            return ;
        }
        out.println(ans);
    }
}
