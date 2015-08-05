package main;

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
        int t = in.nextInt();
        int k = in.nextInt();
        final int N = 100000;
        int[] dp = new int[N + 1];
        dp[0] = 1;
        final int M = (int) (1e9 + 7);
        for (int i = 1; i <= N; i++) {
            dp[i] += dp[i - 1];
            dp[i] %= M;
            if (i - k >= 0) dp[i] += dp[i - k];
            dp[i] %= M;
        }
        for (int i = 1; i <= N; i++) {
            dp[i] += dp[i - 1];
            dp[i] %= M;
        }
        while (t-- != 0) {
            int a = in.nextInt();
            int b = in.nextInt();
            out.println((dp[b] - dp[a - 1] + M) % M);
        }
    }
}
