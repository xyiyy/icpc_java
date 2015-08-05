package main;

import com.shu_mj.math.Num;
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
        long M = in.nextLong();
        // dp[val][length]
        long[][] dp = new long[201][10];
        long[][] num = new long[201][10];
        long[] ten = new long[201];
        for (int i = 0; i < 201; i++) ten[i] = i == 0 ? 1 : ten[i - 1] * 10 % M;
        for (int i = 1; i < 10; i++) dp[1][i] = i;
        for (int i = 0; i < 10; i++) num[1][i] = 1;
        for (int i = 1; i < 200; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    dp[i + 1][k] += dp[i][j] + Num.mul(num[i][j] * k % M, ten[i] % M, M);
                    num[i + 1][k] += num[i][j];
                    dp[i + 1][k] %= M;
                    num[i + 1][k] %= M;
                }
            }
        }
    }
}
