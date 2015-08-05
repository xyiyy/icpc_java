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
        int w = in.nextInt();
        int b = in.nextInt();
        double[][] dp = new double[w + 1][b + 1];
        dp[w][b] = 1;
        double res = 0;
        for (int i = w; i >= 0; i--) {
            for (int j = b; j >= 0; j--) {
                if (i + j != 0) res += dp[i][j] * i / (i + j);
                if (j >= 2 && i >= 1) dp[i - 1][j - 2] += dp[i][j] * j * (j - 1) * i / (i + j) / (i + j - 1) / (i + j - 2);
                if (j >= 3) dp[i][j - 3] += dp[i][j] * j * (j - 1) * (j - 2) / (i + j) / (i + j - 1) / (i + j - 2);
            }
        }
//        Algo.debugTable(dp);
        out.printf("%.9f%n", res);
    }
}
