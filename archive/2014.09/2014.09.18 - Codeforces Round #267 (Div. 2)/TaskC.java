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
        int m = in.nextInt();
        int k = in.nextInt();
        long[] is = new long[n + 1];
        for (int i = 1; i <= n; i++) is[i] = in.nextLong();
        long[] ss = new long[n + 1];
        for (int i = 1; i <= n; i++) ss[i] = is[i] + ss[i - 1];
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k && i >= j * m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - m][j - 1] + ss[i] - ss[i - m]);
            }
        }
        out.println(dp[n][k]);
    }
}
