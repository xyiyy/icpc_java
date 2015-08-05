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
        int n = in.nextInt();
        int m = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        char[][] maps = in.nextCharMap(n);
        int[][] ps = new int[2][m];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (maps[j][i] == '.') {
                    ps[0][i]++;
                } else {
                    ps[1][i]++;
                }
            }
        }
        int[][][] dp = new int[m + 1][2][y + 1];
        final int INF = 12341234;
        Algo.fill(dp, INF);
        dp[0][0][0] = dp[0][1][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k <= y; k++) if (dp[i][j][k] < INF) {
                    if (k < y) dp[i + 1][j][k + 1] = Math.min(dp[i + 1][j][k + 1], dp[i][j][k] + ps[j][i]);
                    if (k >= x) dp[i + 1][j ^ 1][1] = Math.min(dp[i + 1][j ^ 1][1], dp[i][j][k] + ps[j ^ 1][i]);
                }
            }
        }
        int res = INF;
        for (int i = 0; i < 2; i++) {
            for (int j = x; j <= y; j++) {
                res = Math.min(res, dp[m][i][j]);
            }
        }
        out.println(res);
    }
}
