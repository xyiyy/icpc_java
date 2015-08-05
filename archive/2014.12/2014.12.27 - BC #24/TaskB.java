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

    final long M = (long) (1e9 + 7);
    void run() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        long[][] C = Num.combinationTable(n, M);
        if (n == 1) out.println(1);
        else if (n == 2) out.println(2);
        else {
            long[][] dp = new long[n][n];
            for (int i = 1; i < n; i++) dp[i][i - 1] = 1;
            for (int l = 1; l <= n; l++) {
                for (int i = 0; i < n; i++) {
                    int j = i + l - 1;
                    if (j >= n) break;
                    if (i == j) dp[i][j] = 1;
//                    else if (i + 1 == j) dp[i][j] = 2;
                    else for (int k = i; k <= j; k++) if (k == i || k == j || is[k - 1] == is[k + 1]) {
                        long ll = (k == 0 ? 1 : dp[i][k - 1]);
                        long rr = (k == n - 1 ? 1 : dp[k + 1][j]);
                        dp[i][j] += ll * rr % M * C[j - i][k - i] % M;
                        dp[i][j] %= M;
                    }
//                    Algo.debug(i, j, dp[i][j]);
                }
            }
            out.println(dp[0][n - 1]);
        }
    }
}
