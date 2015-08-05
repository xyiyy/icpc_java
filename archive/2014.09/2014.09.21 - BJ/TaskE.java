package main;

import com.shu_mj.graph.SCC;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        SCC.V[] vs = new SCC.V[n];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new SCC.V();
        }
        int[][] input = new int[n][];
        for (int i = 0; i < n; i++) {
            input[i] = in.nextIntArray(in.nextInt());
            for (int j : input[i]) {
                vs[i].add(vs[j - 1]);
            }
        }
        int cnt = SCC.scc(vs);
        boolean[][] G = new boolean[cnt][cnt];
        int[] num = new int[cnt];
        for (int i = 0; i < n; i++) {
            for (int j : input[i]) {
                G[vs[i].comp][vs[j - 1].comp] = true;
            }
            num[vs[i].comp]++;
        }

    }
}
