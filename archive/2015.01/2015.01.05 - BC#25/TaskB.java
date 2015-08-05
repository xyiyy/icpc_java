package main;

import com.shu_mj.math.Num;
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
        while (in.hasNext()) run();
    }

    static final long M = 1000000007;
    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        long[][] C = Num.combinationTable(Math.max(n, m), M);
        long[][] dp = new long[n + 1][m + 1];
//        long[] pow2 = new long[n * m + 1];
//        for (int i = 0; i <= n * m; i++) {
//            pow2[i] = (i == 0 ? 1 : pow2[i - 1] * 2 % M);
//        }
        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) if (dp[i][j] != 0) {
                for (int k = 1; k <= m; k++) {
                    for (int l = Math.max(0, k - j); l <= Math.min(k, m - j); l++) {
                        dp[i + 1][j + l] += dp[i][j] * C[m - j][l] % M * C[j][k - l] % M;
                        dp[i + 1][j + l] %= M;
//                        Algo.debug("dp[" + (i + 1) + "][" + (j + l) + "]", i, j, k, l, dp[i][j], C[m - j][l], C[j][k - l]);
                    }
                }
            }
        }
//        Algo.debugTable(dp);
        out.println(dp[n][m]);
    }
}
