package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;
    private int x0;
    private int y0;
    private int x1;
    private int y1;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0) {
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int[] xs = new int[n];
        int[] ys = new int[n];
        for (int i = 0; i < n; i++) {
            xs[i] = in.nextInt();
            ys[i] = in.nextInt();
        }
        double res = -calc(xs, ys) + calc(ys, xs);
        res /= 2;
        int s = sign(xs, ys);
        while (s == 0) ;
        res *= s;
        if (Math.abs(res) < 1e-6) res = 0;
        out.printf("%.2f%n", res);
    }

    private int sign(int[] xs, int[] ys) {
        int n = xs.length;
        for (int i = 0; i < n; i++) {
            int s = side(xs[i], ys[i], xs[(i + 1) % n], ys[(i + 1) % n], xs, ys);
            if (s != 0) return s;
        }
        return 0;
    }

    private int side(int x0, int y0, int x1, int y1, int[] xs, int[] ys) {
        int s = 0;
        boolean f = true;
        int n = xs.length;
        int x = x1 - x0;
        int y = y1 - y0;
        for (int i = 0; i < n; i++) {
            int u = xs[i] - x0;
            int v = ys[i] - y0;
            int c = x * v - y * u;
            if (c != 0) {
                c /= Math.abs(c);
                if (f) {
                    f = false;
                    s = c;
                } else {
                    if (s != c) return 0;
                }
            }
        }
        return s;
    }

    double calc(int[] xs, int[] ys) {
        int n = xs.length;
        double res = 0;
        for (int i = 0; i < n; i++) {
            x0 = xs[i];
            y0 = ys[i];
            x1 = xs[(i + 1) % n];
            y1 = ys[(i + 1) % n];
//            res += (x1 - x0) / 6 * (f(x0, y0) + 4 * f((x1 + x0) / 2, (y1 + y0) / 2) + f(x1, y1));
            if (x0 == x1) continue;
            res += (x1 - x0) / 6. * (f(x0) + 4 * f((x1 + x0) / 2.) + f(x1));
//            Algo.debug(i, res);
        }
        return res;
    }

    double f(double x) {
        double y = y0 + (x - x0) * 1. / (x1 - x0) * (y1 - y0);
//        Algo.debug("f", x, y, x0, y0, x1, y1);
        return x * y + y * y / 2;
    }

    private double f(double x, double y) {
        return y * y / 2 + x * y;
    }
}
