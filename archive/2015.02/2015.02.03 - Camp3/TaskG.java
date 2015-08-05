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
        int m = in.nextInt();
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new V();
        }
        int[] ind = new int[n];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            if (u > v) {
                out.println("No");
                return ;
            }
            if (u != v) {
                vs[u].add(v);
                ind[v]++;
            }
        }
        if (Algo.count(ind, 0) != 1) {
            out.println("No");
            return ;
        }
        for (int i = 0; i < n; i++) {
            if (ind[i] != 0) {
                out.println("No");
                return ;
            }
            for (int u : vs[i]) {
                ind[u]--;
                if (ind[u] == 0 && u != i + 1) {
                    out.println("No");
                    return ;
                }
            }
        }
        out.println("Yes");
    }
    class V extends ArrayList<Integer> {

    }
}
