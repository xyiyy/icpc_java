package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
    Scanner in;
    PrintWriter out;
    private int[] is;
    private int[][] dp;
    private int n;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            solve(n);
        }
    }

    private void solve(int n) {
        this.n = n;
        int s = in.nextInt() - 1;
        is = in.nextIntArray(2 * n);
        dp = new int[n * 2][s + 1];
        Algo.fill(dp, -1);
        for (int i = 0; i < n * 2; i++) dp[i][0] = 0;
        out.println(dfs(0, s));
    }

    private int dfs(int c, int s) {
        if (dp[c][s] != -1) return dp[c][s];
        for (int i = 1; i <= is[c]; i++) {
            if(dfs((c + 1) % (n * 2), s - i) == 0) return dp[c][s] = 1;
        }
        return dp[c][s] = 0;
    }
}
