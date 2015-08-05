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
        int t = in.nextInt();
        while (t-- != 0) solve();
    }

    private void solve() {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] is = in.nextIntArray(n);
        long[] S = new long[n + 1];
        for (int i = 0; i < n; i++) S[i + 1] = is[i] + S[i];
        long[] dp = new long[n + 1];
        int s = 0;
        int t = 1;
        int[] deq = new int[n];
        for (int i = k; i <= n; i++) {
            if (i - k >= k) {
                while (t - s > 1 && check(deq[t - 2], deq[t - 1], i - k, dp, S, is)) t--;
                deq[t++] = i - k;
            }
            while (t - s > 1 && f(deq[s], i, dp, S, is) >= f(deq[s + 1], i, dp, S, is)) s++;
            dp[i] = S[i] + f(deq[s], i, dp, S, is);
        }
        out.println(dp[n]);
    }

    private long f(int j, int x, long[] dp, long[] S, int[] is) {
        return -is[j] * 1L * x + dp[j] - S[j] + is[j] * 1L * j;
    }

    private boolean check(int f1, int f2, int f3, long[] dp, long[] S, int[] is) {
        long a1 = -is[f1];
        long a2 = -is[f2];
        long a3 = -is[f3];
        long b1 = dp[f1] - S[f1] + is[f1] * 1L * f1;
        long b2 = dp[f2] - S[f2] + is[f2] * 1L * f2;
        long b3 = dp[f3] - S[f3] + is[f3] * 1L * f3;
        return (a2 - a1) * (b3 - b2) >= (b2 - b1) * (a3 - a2);
    }

}
