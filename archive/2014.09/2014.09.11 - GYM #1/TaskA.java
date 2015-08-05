package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
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
        int[] is = in.nextIntArray(n);
        boolean[][] dp = new boolean[2][k];
        dp[1][0] = true;
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i & 1], false);
            for (int j = 0; j < k; j++) if (dp[(i - 1) & 1][j]) {
                dp[i & 1][((j + is[i]) % k + k) % k] = true;
                dp[i & 1][((j - is[i]) % k + k) % k] = true;
            }
        }
        if (dp[(n - 1) & 1][0]) out.println("Divisible");
        else out.println("Not divisible");
    }
}
