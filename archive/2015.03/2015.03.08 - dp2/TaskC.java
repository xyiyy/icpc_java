package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        char[] cs = in.next().toCharArray();
        int m = cs.length;
        int n = in.nextInt();
        int[][][] dp = new int[m + 1][n + 1][2];
        int INF = 12341234;
        Algo.fill(dp, -INF);
        dp[0][0][0] = 0;
        dp[0][0][1] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k < 2; k++) if (dp[i][j][k] > -INF) {
                    int I = i + 1;
                    int J = j;
                    int K = k ^ (cs[i] == 'T' ? 1 : 0);
                    for (; J <= n; J += 2) dp[I][J][K] = Math.max(dp[I][J][K], dp[i][j][k] + (K == k ? (k == 0 ? 1 : -1) : 0));
                    J = j + 1;
                    K = k ^ (cs[i] == 'F' ? 1 : 0);
                    for (; J <= n; J += 2) dp[I][J][K] = Math.max(dp[I][J][K], dp[i][j][k] + (K == k ? (k == 0 ? 1 : -1) : 0));
                }
            }
        }
        out.println(Algo.max(dp[m][n]));
    }
}
