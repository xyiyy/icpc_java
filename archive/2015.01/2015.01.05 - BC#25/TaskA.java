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
        while (in.hasNext()) run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        boolean[][] g = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            g[u][v] = true;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    g[i][j] |= g[i][k] & g[k][j];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (g[i][i]) {
                out.println("NO");
                return ;
            }
        }
        out.println("YES");
    }
}
