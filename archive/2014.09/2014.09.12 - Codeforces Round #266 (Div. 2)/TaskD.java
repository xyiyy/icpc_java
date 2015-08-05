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
        int n = in.nextInt();
        int h = in.nextInt();
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = h - in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            if (is[i] < 0 || i > 0 && is[i] - is[i - 1] > 1 || i < n - 1 && is[i] - is[i + 1] > 1 || i == 0 && is[i] > 1 || i == n - 1 && is[i] > 1) {
                out.println(0);
                return ;
            }
        }
        int max = Algo.max(is);
        long[] dp = new long[n + 1];
        final long M = (long) (1e9 + 7);
        for (int i = 0; i <= n; i++) {
            if (i <= 1) dp[i] = 1;
            else dp[i] = dp[i - 1] * 2 % M;
        }
        long crt = 1;
        for (int i = max; i > 0; i--) {
            for (int j = 0; j < n; ) {
                if (is[j] == i) {
                    int k = j;
                    while (k < n && is[k] >= i) k++;
                    crt *= dp[k - j];
                    crt %= M;
                    j = k;
                } else {
                    j++;
                }
            }
        }
        out.println(crt);
    }
}
