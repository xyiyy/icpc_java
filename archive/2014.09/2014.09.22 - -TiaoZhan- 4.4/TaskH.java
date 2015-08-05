package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskH {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) solve();
    }

    private void solve() {
        int n = in.nextInt();
        int s = in.nextInt();
        int[] T = new int[n + 1];
        int[] F = new int[n + 1];
        for (int i = 0; i < n; i++) {
            T[i] = in.nextInt();
            F[i] = in.nextInt();
        }
        for (int i = n - 2; i >= 0; i--) {
            T[i] += T[i + 1];
            F[i] += F[i + 1];
        }
        int[] dp = new int[n + 1];
        int[] deq = new int[n + 1];
        int b = 0, e = 0;
        deq[e++] = n;
        for (int i = n - 1; i >= 0; i--) {
            while (e - b > 1 && f(T, F, dp, deq[b], i) >= f(T, F, dp, deq[b + 1], i)) b++;
            int k = deq[b];
            dp[i] = dp[k] + (s + T[i] - T[k]) * F[i];
            while (e - b > 1 && check(T, F, dp, deq[e - 2], deq[e - 1], i)) e--;
            deq[e++] = i;
        }
        out.println(dp[0]);
    }

    private boolean check(int[] t, int[] f, int[] dp, int a, int b, int c) {
        int a1 = -t[a];
        int a2 = -t[b];
        int a3 = -t[c];
        int b1 = dp[a];
        int b2 = dp[b];
        int b3 = dp[c];
        return (a2 - a1) * (b3 - b2) >= (b2 - b1) * (a3 - a2);
    }

    private int f(int[] t, int[] f, int[] dp, int k, int i) {
        return -t[k] * f[i] + dp[k];
    }
}
