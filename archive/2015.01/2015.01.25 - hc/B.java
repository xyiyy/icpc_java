package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class B {
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
            out.printf("Case #%d: ", i + 1);
            solve();
        }
    }

    private void solve() {
        double p = in.nextDouble();
        double[] dp = new double[1 << 20];
        double res = 0;
        for (int i = (1 << 20) - 1; i >= 0; i--) {
            for (int j = 0; j < 20; j++) {
                if ((i & (1 << j)) != 0) {
                    dp[i ^ (1 << j)] += dp[i] + 1;
                }
            }
        }
        out.printf("%.5f%n", dp[0]);
    }
}
