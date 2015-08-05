package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Winning {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            out.printf("Case #%d: ", i + 1);
            solve();
        }
    }

    private void solve() {
        String[] ss = in.next().split("-");
        int a = Integer.parseInt(ss[0]);
        int b = Integer.parseInt(ss[1]);
        out.println(work1(a, b) + " " + work2(b));
    }

    private int work2(int b) {
        int[][] dp = new int[b + 1][b + 1];
        dp[0][0] = 1;
        for (int i = 0; i < b + b; i++) {
            for (int j = 0; j <= b; j++) {
                int k = i - j;
                if (k < 0 || k > b) continue;
                if (k < b) {
                    dp[j][k + 1] += dp[j][k];
                    dp[j][k + 1] %= M;
                }
                if (j + 1 <= k && j < b) {
                    dp[j + 1][k] += dp[j][k];
                    dp[j + 1][k] %= M;
                }
            }
        }
        return dp[b][b];
    }

    private int work1(int a, int b) {
        int[][] dp = new int[a + 1][b + 1];
        dp[1][0] = 1;
        for (int i = 1; i < a + b; i++) {
            for (int j = 0; j <= a; j++) {
                int k = i - j;
                if (k < 0 || k > b) continue;
                if (j < a) {
                    dp[j + 1][k] += dp[j][k];
                    dp[j + 1][k] %= M;
                }
                if (j > k + 1 && k < b) {
                    dp[j][k + 1] += dp[j][k];
                    dp[j][k + 1] %= M;
                }
            }
        }
        return dp[a][b];
    }

    final static int M = (int) (1e9 + 7);
}
