package main;

import com.shu_mj.graph.SCC;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
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
        SCC.V[] vs = new SCC.V[n * 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new SCC.V();
        }
        while (m-- != 0) {
            int u = in.nextInt();
            int v = in.nextInt();
            int c = in.nextInt();
            char t = in.next().charAt(0);
            if (t == 'A') {
                if (c == 1) {
                    vs[u + n].add(vs[u]);
                    vs[v + n].add(vs[v]);
                } else {
                    vs[u].add(vs[v + n]);
                    vs[v].add(vs[u + n]);
                }
            } else if (t == 'O') {
                if (c == 1) {
                    vs[u + n].add(vs[v]);
                    vs[v + n].add(vs[u]);
                } else {
                    vs[u].add(vs[u + n]);
                    vs[v].add(vs[v + n]);
                }
            } else {
                if (c == 1) {
                    vs[u].add(vs[v + n]);
                    vs[v].add(vs[u + n]);
                    vs[v + n].add(vs[u]);
                    vs[u + n].add(vs[v]);
                } else {
                    vs[u].add(vs[v]);
                    vs[v + n].add(vs[u + n]);
                    vs[v].add(vs[u]);
                    vs[u + n].add(vs[v + n]);
                }
            }
        }
        SCC.scc(vs);
        for (int i = 0; i < n; i++) {
            if (vs[i].comp == vs[i + n].comp) {
                out.println("NO");
                return ;
            }
        }
        out.println("YES");
    }
}
