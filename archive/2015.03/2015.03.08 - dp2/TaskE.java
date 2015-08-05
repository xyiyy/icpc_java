package main;

import com.shu_mj.math.Num;
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
        int n = in.nextInt();
        long m = in.nextLong();
        int k = in.nextInt();
        final long M = (long) (1e9 + 7);
        long[][] C = Num.combinationTable(n, M);
        long[][] dp = new long[n + 1][k + 1];
        dp[0][0] = 1;
        long[][] ps = new long[n + 1][n + 1];
        for (int u = 0; u <= n; u++) {
            for (int i = 0; i <= n; i++) {
                ps[u][i] = Num.pow(C[n][u], (m - i + n) / n, M);
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) if (dp[i - 1][j] != 0) {
                for (int u = 0; u + j <= k && u <= n; u++) {
//                    dp[i][u + j] += dp[i - 1][j] * Num.pow(C[n][u], (m - i + n) / n, M) % M;
                    dp[i][u + j] += dp[i - 1][j] * ps[u][i] % M;
//                    Algo.debug(i, j, u, dp[i - 1][j], C[n][u], (m - i + n) / n, Num.pow(C[n][u], (m - i + n) / n, M));
                    dp[i][u + j] %= M;
                }
            }
        }
        out.println(dp[n][k]);
    }
}
