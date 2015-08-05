package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1004 {
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
        int m = in.nextInt();
        double[][] p = in.nextDoubleMatrix(n, m);
        double[][] dp = new double[2][1 << n];
        boolean[][] vis = new boolean[2][1 << n];
        vis[1][0] = true;
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i & 1], 0);
            Arrays.fill(vis[i & 1], false);
            if (vis[(i - 1) & 1][(1 << n) - 1]) {
                vis[(i - 1) & 1][(1 << n) - 1] = false;
                dp[(i - 1) & 1][0] = dp[(i - 1) & 1][(1 << n) - 1];
                vis[(i - 1) & 1][0] = true;
            }
            for (int j = 0; j < (1 << n); j++) if (vis[(i - 1) & 1][j]) {
                for (int k = 0; k < n; k++) if ((j & (1 << k)) == 0) {
                    dp[i & 1][j | (1 << k)] = Math.max(dp[i & 1][j | (1 << k)], dp[(i - 1) & 1][j] + p[k][i]);
                    vis[i & 1][j | (1 << k)] = true;
                }
            }
        }
        double ans = 0;
        for (double d : dp[(m - 1) & 1]) ans = Math.max(ans, d);
        out.printf("%.5f%n", ans);
    }
}
