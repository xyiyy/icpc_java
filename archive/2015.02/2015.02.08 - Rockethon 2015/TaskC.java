package main;

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
        int n = in.nextInt();
        int[] L = new int[n];
        int[] R = new int[n];
        in.nextIntArray(n, L, R);
        double res = 0;
        int min = Algo.min(L);
        int max = Algo.max(R);
//        int sum = 0;
        for (int i = 0; i < (1 << n); i++) if (Integer.bitCount(i) >= 2) {
            int l = min, r = max;
            double p = 1;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    l = Math.max(L[j], l);
                    r = Math.min(R[j], r);
                    p *= 1. / (R[j] - L[j] + 1);
                }
            }
            for (int val = l; val <= r; val++) {
                double pp = p;
                for (int j = 0; j < n; j++) {
                    if ((i & (1 << j)) == 0) {
                        if (L[j] >= val) pp = 0;
                        else pp *= (Math.min(R[j] + 1, val) - L[j]) / (R[j] - L[j] + 1.);
                    }
                }
                res += pp * val;
            }
        }
//        Algo.debug(res);
        for (int i = 1; i + 1 < (1 << n); i++) {
            int l = min, r = max;
            double p = 1;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    l = Math.max(L[j], l);
                    r = Math.min(R[j], r);
                    p *= 1. / (R[j] - L[j] + 1);
                }
            }
            for (int val = l; val <= r; val++) {
                for (int top = 0; top < n; top++) if ((i & (1 << top)) == 0) {
                    double pp = p;
                    if (R[top] <= val) continue;
                    pp *= (R[top] - Math.max(val, L[top] - 1)) / (R[top] - L[top] + 1.);
                    for (int j = 0; j < n; j++) if ((i & (1 << j)) == 0 && j != top) {
                        if (L[j] >= val) pp = 0;
                        else pp *= (Math.min(val, R[j] + 1) - L[j]) / (R[j] - L[j] + 1.);
                    }
//                    Algo.debug(top, val, i, pp);
                    res += pp * val;
                }
            }
        }
        out.printf("%.10f%n", res);
//        for (int i = L[0]; i <= R[0]; i++) {
//            for (int j = L[1]; j <= R[1]; j++) {
//                for (int k = L[2]; k <= R[2]; k++) {
//                    int[] is = new int[] { i, j, k };
//                    Arrays.sort(is);
//                    sum += is[1];
//                }
//            }
//        }
//                out.printf("%.10f%n", sum / (R[0] - L[0] + 1.) / (R[1] - L[1] + 1) / (R[2] - L[2] + 1));
    }
}
