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
        while (in.hasNext()) run();
    }

    void run() {
        int n = in.nextInt();
        int a0 = in.nextInt();
        int an1 = in.nextInt();
        int[] c = in.nextIntArray(n);
        double l = -1e6, r = 1e6;
        for (int i = 0; i < 100; i++) {
            double m = (l + r) / 2;
            if (calc(n, c, a0, m) > an1) r = m;
            else l = m;
        }
        out.printf("%.2f%n", l);
    }

    private double calc(int n, int[] c, double a0, double a1) {
        double a2 = 0;
        for (int i = 2; i <= n + 1; i++) {
            a2 = 2 * a1 - a0 + 2 * c[i - 2];
            a0 = a1;
            a1 = a2;
        }
        return a2;
    }
}
