package main;

import com.shu_mj.graph.SCC;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] ss = new int[n];
        int[] tt = new int[n];
        int[] dd = new int[n];
        for (int i = 0; i < n; i++) {
            String s = in.next();
            String t = in.next();
            dd[i] = in.nextInt();
            ss[i] = Integer.parseInt(s.substring(0, 2)) * 60 + Integer.parseInt(s.substring(3));
            tt[i] = Integer.parseInt(t.substring(0, 2)) * 60 + Integer.parseInt(t.substring(3));
        }
        SCC.V[] vs = new SCC.V[n * 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new SCC.V();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (cross(ss[i], ss[i] + dd[i], ss[j], ss[j] + dd[j])) {
                    // ~x_i | ~x_j := x_i -> ~x_j & x_j -> ~x_i
                    vs[j].add(vs[i + n]);
                    vs[i].add(vs[j + n]);
                }
                if (cross(ss[i], ss[i] + dd[i], tt[j] - dd[j], tt[j])) {
                    vs[j + n].add(vs[i + n]);
                    vs[i].add(vs[j]);
                }
                if (cross(tt[i] - dd[i], tt[i], ss[j], ss[j] + dd[j])) {
                    vs[j].add(vs[i]);
                    vs[i + n].add(vs[j + n]);
                }
                if (cross(tt[i] - dd[i], tt[i], tt[j] - dd[j], tt[j])) {
                    vs[j + n].add(vs[i]);
                    vs[i + n].add(vs[j]);
                }
            }
        }
        SCC.scc(vs);
        for (int i = 0; i < n; i++) {
            if (vs[i].comp == vs[i + n].comp) {
                out.println("NO");
                return;
            }
        }
        out.println("YES");
        for (int i = 0; i < n; i++) {
            if (vs[i].comp > vs[i + n].comp) {
                print(ss[i], ss[i] + dd[i]);
            } else {
                print(tt[i] - dd[i], tt[i]);
            }
        }
    }

    private void print(int s, int t) {
        out.printf("%02d:%02d %02d:%02d%n", s / 60, s % 60, t / 60, t % 60);
    }

    private boolean cross(int a, int b, int c, int d) {
        return a < d && c < b;
    }
}
