package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            out.printf("Case %d: ", i + 1);
            solve();
        }
    }

    private void solve() {
        long L = in.nextLong();
        long R = in.nextLong();
        long c = in.nextLong();
        int k = in.nextInt();
        out.println(and(L, R, c, k) + " " + xor(L, R, c, k) + " " + or(L, R, c, k));
    }

    private long or(long l, long r, long c, int k) {
        return or(r, c, k) - or(l - 1, c, k);
    }

    private long or(long r, long c, int k) {
        if (r < 0) return 0;
        int[][][] dp = new int[33][2][65];
        dp[32][1][0 + 32] = 1;
        for (int i = 32; i > 0; i--) {
            for (int l = 0; l < 2; l++) {
                for (int j = 0; j < 65; j++) {
                    int v = dp[i][l][j];
                    if (v != 0) {
                        int bc = (int) ((c >> (i - 1)) & 1L);
                        int br = (int) ((r >> (i - 1)) & 1L);
                        if (l == 0) {
                            dp[i - 1][l][j + 1] += v;
                            if (bc == 0) dp[i - 1][l][j - 1] += v;
                        } else {
                            if (br == 1) dp[i - 1][1][j + 1] += v;
                            if (bc == 0) dp[i - 1][br ^ 1][j - 1] += v;
                        }
                    }
                }
            }
        }
        long res = 0;
        for (int i = -k; i <= k; i++) {
            res += dp[0][0][i + 32];
            res += dp[0][1][i + 32];
        }
        return res;
    }

    private long xor(long l, long r, long c, int k) {
        return xor(r, c, k) - xor(l - 1, c, k);
    }

    private long xor(long r, long c, int k) {
        if (r < 0) return 0;
        if (c != 0) return 0;
        int[][][] dp = new int[33][2][65];
        dp[32][1][0 + 32] = 1;
        for (int i = 32; i > 0; i--) {
            for (int l = 0; l < 2; l++) {
                for (int j = 0; j < 65; j++) {
                    int v = dp[i][l][j];
                    if (v != 0) {
                        int br = (int) ((r >> (i - 1)) & 1L);
                        dp[i - 1][l == 0 || br == 1 ? 0 : 1][j - 1] += v;
                        if (l == 0 || br == 1) {
                            dp[i - 1][l][j + 1] += v;
                        }
                    }
                }
            }
        }
        long res = 0;
        for (int i = -k; i <= k; i++) {
            res += dp[0][0][i + 32];
            res += dp[0][1][i + 32];
        }
        return res;
    }

    private long and(long L, long R, long c, int k) {
        return and(R, c, k) - and(L - 1, c, k);
    }

    private long and(long r, long c, int k) {
        if (r < 0) return 0;
        int[][][] dp = new int[33][2][65];
        dp[32][1][0 + 32] = 1;
        for (int i = 32; i > 0; i--) {
            for (int l = 0; l < 2; l++) {
                for (int j = 0; j < 65; j++) {
                    int v = dp[i][l][j];
                    if (v != 0) {
                        int bc = (int) ((c >> (i - 1)) & 1L);
                        int br = (int) ((r >> (i - 1)) & 1L);
                        dp[i - 1][l == 0 || br == 1 ? 0 : 1][j - 1] += v;
                        if (bc != 0) {
                            if (l == 0 || br == 1) {
                                dp[i - 1][l][j + 1] += v;
                            }
                        }
                    }
                }
            }
        }
        long res = 0;
        for (int i = -k; i <= k; i++) {
            res += dp[0][0][i + 32];
            res += dp[0][1][i + 32];
        }
        return res;
    }
}
