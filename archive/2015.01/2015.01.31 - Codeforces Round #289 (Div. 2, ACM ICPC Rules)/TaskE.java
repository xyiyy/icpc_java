package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int[] v = new int[128];
        v['I'] = v['E'] = v['A'] = v['O'] = v['U'] = v['Y'] = 1;
        char[] cs = in.next().toCharArray();
        int n = cs.length;
        long[] res = new long[n + 1];
        long[] left = new long[n + 1];
        long[] right = new long[n + 1];
        long[] mid = new long[n + 1];
        for (int i = 0; i < n; i++) {
            if (v[cs[i]] == 1) {
                int l = i + 1;
                int r = n - i;
                int min = Math.min(l, r);
                int max = Math.max(l, r);
                for (int j = 1; j < min; j++) {
                    res[j] += j;
                }
                left[min]++;
                for (int j = min; j < max; j++) {
                    res[j] += min;
                }
                mid[min] += min;
                mid[max] -= min;
                for (int j = max; j <= n; j++) {
                    res[j] += n - j + 1;
                }
                right[max - 1]++;
            }
        }
//        for (int i = 0, crt = 0; i <= n; i++) {
//            crt += mid[i];
//            res[i] += crt;
//        }
//        for (int i = 0, crt = (int) Algo.sum(left); i <= n; i++) {
//            crt -= left[i];
//            res[i] += (long) crt * i;
//        }
//        for (int i = n, crt = (int) Algo.sum(right); i > 0; i--) {
//            crt -= right[i];
//            res[i] += (long) crt * (n - i + 1);
//        }
        double ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += res[i] * 1.0 / i;
        }
        out.printf("%.7f%n", ans);
    }
}
