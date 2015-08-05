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
        int l = in.nextInt();
        int[] is = in.nextIntArray(n);
        Arrays.sort(is);
        double s = 0, t = l;
        for (int i = 0; i < 1000; i++) {
            double m = (s + t) / 2;
            if (fit(is, n, l, m)) {
                t = m;
            } else {
                s = m;
            }
        }
        out.printf("%.15f%n", s);
    }

    private boolean fit(int[] is, int n, int l, double m) {
        double b = 0;
        for (int i : is) {
            if (i - m <= b) {
                b = i + m;
            } else {
                return false;
            }
        }
        return b >= l;
    }
}
