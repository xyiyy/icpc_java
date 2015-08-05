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
        int t = in.nextInt();
        double[][] dp = new double[2][t + 1];
        double res = 0;
        double[] ps = new double[n];
        int[] ds = new int[n];
        for (int i = 0; i < n; i++) {
            ps[i] = in.nextInt() / 100.0;
            ds[i] = in.nextInt();
        }
        dp[1][0] = 1;
//        int N = Integer.highestOneBit(t + 1) << 2;
//        double[] R = new double[N];
//        double[] I = new double[N];
//        double[] R2 = new double[N];
//        double[] I2 = new double[N];
        for (int i = 0; i < n; i++) {
            double p = ps[i];
            int d = ds[i];
            Arrays.fill(dp[i & 1], 0);
//            Arrays.fill(R, 0);
//            Arrays.fill(I, 0);
//            Arrays.fill(R2, 0);
//            Arrays.fill(I2, 0);
//            for (int j = 0; j < t; j++) {
//                R[j] = dp[(i - 1) & 1][j] * p;
//            }
//            if (1 < d) R2[1] = 1;
//            for (int j = 2; j < d; j++) {
//                R2[j] = R2[j - 1] * (1 - p);
//            }
//            Algo.fft(1, R, I);
//            Algo.fft(1, R2, I2);
//            for (int j = 0; j < N; j++) {
//                double rr = R[j] * R2[j] - I[j] * I2[j];
//                double ii = R[j] * I2[j] + I[j] * R2[j];
//                R[j] = rr;
//                I[j] = ii;
//            }
//            Algo.fft(-1, R, I);
//            for (int j = 0; j <= t; j++) {
//                dp[i & 1][j] = R[j];
//            }
            double crt = 0;
            double[] qs = new double[d];
            qs[0] = 1;
            for (int j = 1; j < d; j++) {
                qs[j] = qs[j - 1] * (1 - p);
            }
            double ppp = qs[d - 1];
            for (int j = 1; j <= t; j++) {
                crt *= 1 - p;
                crt += dp[(i - 1) & 1][j - 1] * p;
                if (j - d >= 0) {
                    crt -= dp[(i - 1) & 1][j - d] * p * ppp;
                }
                dp[i & 1][j] = crt;
            }
//            double[] dd = new double[t + 1];
//            Algo.debug(p, d, dp[(i - 1) & 1]);
//            for (int j = 0; j < t; j++) if (dp[(i - 1) & 1][j] > 0) {
//                double pp = 1;
//                for (int k = 1; k < d && j + k <= t; k++) {
//                    dd[j + k] += pp * p * dp[(i - 1) & 1][j];
//                    pp *= 1 - p;
//                }
//            }
//            Algo.debug(dp[i & 1]);
//            Algo.debug(dd);
            for (int j = 0; j < t; j++) if (dp[(i & 1) ^ 1][j] > 0) {
//                double pp2 = 1;
//                for (int k = 1; k < d && j + k <= t; k++) {
////                    dp[i & 1][j + k] += pp * p * dp[(i - 1) & 1][j];
//                    pp2 *= 1 - p;
//                }
//                double pp = Math.pow(1 - p, Math.min(d - 1, t - j));
                double pp = qs[Math.min(d - 1, t - j)];
//                if (pp != pp2) Algo.debug(pp, pp2);
                if (j + d <= t) {
                    dp[i & 1][j + d] += pp * dp[(i - 1) & 1][j];
                } else {
                    res += dp[(i - 1) & 1][j] * pp * i;
                }
            }
            if (i < n - 1) res += dp[i & 1][t] * (i + 1);
            else res += Algo.sum(dp[i & 1]) * n;
            Algo.debug(i, res);
            Algo.debug(Algo.sum(dp[i & 1]));
        }
        out.printf("%.9f%n", res);
    }
}
