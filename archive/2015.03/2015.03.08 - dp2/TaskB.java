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
        int a = in.nextInt();
        int b = in.nextInt();
        int[] hs = in.nextIntArray(n);
        int A = n + 1, B = a + 1, C = 2;
        int[] dp = new int[A * B * C];
        final int INF = 12341234;
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 0, s = 0; i < n; i++) {
            for (int j = 0; j <= a; j++) {
                for (int k = 0; k < 2; k++) if (dp[i * B * C + j * C + k] < INF) {
                    if (a - j >= hs[i]) {
                        dp[(i + 1) * B * C + (j + hs[i]) * C + 0] = Math.min(dp[(i + 1) * B * C + (j + hs[i]) * C + 0], dp[i * B * C + j * C + k] + (k == 0 ? 0 : Math.min(i == 0 ? 0 : hs[i - 1], hs[i])));
                    }
                    if (s - j + hs[i] <= b) {
                        dp[(i + 1) * B * C + j * C + 1] = Math.min(dp[(i + 1) * B * C + j * C + 1], dp[i * B * C + j * C + k] + (k == 1 ? 0 : Math.min(i == 0 ? 0 : hs[i - 1], hs[i])));
                    }
                }
            }
            s += hs[i];
        }
        int min = INF;
        for (int i = 0; i <= a; i++) {
            for (int j = 0; j < 2; j++) {
                min = Math.min(min, dp[n * B * C + i * C + j]);
            }
        }
        out.println(min < INF ? min : -1);
    }
}
