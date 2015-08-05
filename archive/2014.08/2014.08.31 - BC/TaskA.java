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
        while (in.hasNext()) solve();
    }

    private void solve() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        for (int i = 0; i < n; i++) is[i]--;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) if (!vis[i]) {
            out.print("(" + (i + 1));
            vis[i] = true;
            int x = is[i];
            while (x != i) {
                out.print(" " + (x + 1));
                vis[x] = true;
                x = is[x];
            }
            out.print(")");
        }
        out.println();
    }
}
