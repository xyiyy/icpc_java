package main;

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
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            solve();
        }
    }

    private void solve() {
        int x = in.nextInt();
        int b = in.nextInt();
        int n = Math.min(x, b);
        double[][] dp = new double[b + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 0; i < b; i++) {
            for (int j = 0; j <= n; j++) if (dp[i][j] != 0) {
                double p = j * 1.0 / x;
                dp[i + 1][j] += dp[i][j] * p;
                if (j < n) dp[i + 1][j + 1] += dp[i][j] * (1 - p);
            }
        }
        double ave = (1.0 + x) / 2;
        double res = 0;
        for (int i = 0; i <= n; i++) {
            res += i * ave * dp[b][i];
        }
        out.printf("%.3f%n", res);
    }
}
