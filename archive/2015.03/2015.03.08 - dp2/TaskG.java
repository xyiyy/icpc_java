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
        for (int i = 0; i < n; i++) {
            vs[i].v = in.nextInt();
        }
        dfs(vs, 0, -1);
        out.println(vs[0].inc + vs[0].dec);
//        for (int i = 0; i < vs.length; i++) {
//            Algo.debug(i, vs[i].inc, vs[i].dec, vs[i].v);
//        }
    }

    private void dfs(V[] vs, int v, int fa) {
        long inc = 0, dec = 0;
        for (int u : vs[v]) if (u != fa) {
            dfs(vs, u, v);
            inc = Math.max(inc, vs[u].inc);
            dec = Math.max(dec, vs[u].dec);
        }
        if (vs[v].v >= inc - dec) {
            inc += vs[v].v - (inc - dec);
        } else {
            dec += (inc - dec) - vs[v].v;
        }
        vs[v].inc = inc;
        vs[v].dec = dec;
    }

    class V extends ArrayList<Integer> {
        int v;
        long inc;
        long dec;
    }
}
