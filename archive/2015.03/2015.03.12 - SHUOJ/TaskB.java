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
        int t = in.nextInt();
        while (t-- != 0) {
            solve();
        }
    }

    private void solve() {
        char[] ac = in.next().toCharArray();
        char[] bc = in.next().toCharArray();
        int n = ac.length + bc.length;
        n = Integer.highestOneBit(n) << 1;
        double[] a = new double[n];
        double[] ai = new double[n];
        double[] b = new double[n];
        double[] bi = new double[n];
        for (int i = 0; i < ac.length; i++) {
            a[i] = ac[ac.length - i - 1] - '0';
        }
        for (int i = 0; i < bc.length; i++) {
            b[i] = bc[bc.length - i - 1] - '0';
        }
        Algo.fft(1, a, ai);
        Algo.fft(1, b, bi);
        for (int i = 0; i < n; i++) {
            double re = a[i] * b[i] - ai[i] * bi[i];
            double im = a[i] * bi[i] + ai[i] * b[i];
            a[i] = re;
            ai[i] = im;
        }
        Algo.fft(-1, a, ai);
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = (int) (a[i] + 0.5);
        }
        for (int i = 0; i < n - 1; i++) {
            is[i + 1] += is[i] / 10;
            is[i] %= 10;
        }
        int t = n - 1;
        while (t > 0 && is[t] == 0) t--;
        for (int i = t; i >= 0; i--) {
            out.print(is[i]);
        }
        out.println();
    }
}
