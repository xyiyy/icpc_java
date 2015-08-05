package main;

import com.shu_mj.math.Num;
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
        int n = in.nextInt();
        int k = in.nextInt();
        long M = (long) (1e9 + 7);
        long[][] dp = new long[k + 1][n + 1];
        List<Long>[] ls = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            ls[i] = Num.factors(i);
        }
        dp[0][1] = 1;
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                for (long u : ls[j]) {
                    dp[i][j] += dp[i - 1][(int) u];
                    dp[i][j] %= M;
                }
            }
        }
        out.println(Algo.sum(dp[k]) % M);
    }
}
