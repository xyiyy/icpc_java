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
        int n = in.nextInt();
        int t = in.nextInt();
        int[][][][] dp = new int[n + 1][t + 1][4][2];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) if (i < j) {
                dp[2][0][j][0]++;
            }
        }
        for (int i = 2; i < n; i++) {
            for (int j = 0; j <= t; j++) {
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 2; l++) if (dp[i][j][k][l] > 0) {
                        int old = dp[i][j][k][l];
                        for (int u = 0; u < 4; u++) if (u != k) {
                            if ((l == 0) ^ (u < k)) {
                                dp[i + 1][j][u][l] += old;
                            } else {
                                if (l == 1) dp[i + 1][j][u][l ^ 1] += old;
                                else if (j < t) dp[i + 1][j + 1][u][l ^ 1] += old;
                            }
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int k = 0; k < 4; k++) {
            res += dp[n][t][k][1];
        }
        out.println(res);
    }
}
