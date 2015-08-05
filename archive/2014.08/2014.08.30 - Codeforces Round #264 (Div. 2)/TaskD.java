package main;

import com.shu_mj.graph.BipartiteMatching;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
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
        int[][] iss = in.nextIntMatrix(k, n);
        int[][] rss = new int[k][n];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                rss[i][iss[i][j] - 1] = j;
            }
        }
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (fit(iss, rss, n, k, i, j)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        out.println(Algo.max(dp));
    }

    private boolean fit(int[][] iss, int[][] rss, int n, int k, int i, int j) {
        int ii = iss[0][i];
        int jj = iss[0][j];
        for (int u = 0; u < k; u++) {
            if (rss[u][ii - 1] < rss[u][jj - 1]) return false;
        }
        return true;
    }
}
