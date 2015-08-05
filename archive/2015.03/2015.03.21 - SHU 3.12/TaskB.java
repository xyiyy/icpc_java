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
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] iss = new int[n][];
        int[][] dpp = new int[n][];
        int INF = 12341234;
        for (int i = 0; i < n; i++) {
            iss[i] = in.nextIntArray(in.nextInt());
            for (int j = 1; j < iss[i].length; j++) {
                iss[i][j] += iss[i][j - 1];
            }
            dpp[i] = new int[iss[i].length + 1];
            for (int j = 0; j <= iss[i].length; j++) {
                for (int k = 0; k <= j; k++) {
                    int l = (k == 0 ? 0 : iss[i][k - 1]);
                    int r = (j - k == iss[i].length ? 0 : iss[i][iss[i].length - (j - k) - 1]);
                    dpp[i][j] = Math.max(dpp[i][j], iss[i][iss[i].length - 1] - r + l);
                }
            }
        }
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= iss[i].length; k++) if (j + k <= m) {
                    dp[i + 1][j + k] = Math.max(dp[i + 1][j + k], dp[i][j] + dpp[i][k]);
                }
            }
        }
        out.println(dp[n][m]);
    }
}
