package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            if (n == 0 && m == 0) break;
            solve(n, m);
        }
    }

    private void solve(int n, int m) {
        int[] is = in.nextIntArray(n);
        boolean[][] dp = new boolean[2][m + 1];
        dp[0][0] = true;
        for (int i : is) {
            for (int j = m; j >= i; j--) {
                dp[0][j] |= dp[1][j - i];
                dp[1][j] |= dp[0][j - i];
            }
        }
        int cnt = 0;
        for (int i = 1; i <= m; i++) {
            if (dp[1][i] && !dp[0][i]) cnt++;
        }
        out.println(cnt);
    }
}
