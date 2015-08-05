package main;

import com.shu_mj.ds.Rational;
import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;
    PrintStream err = System.err;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        while (in.hasNext()) run();
    }

    void run() {
        int n = in.nextInt();
        int k = in.nextInt();
        String s = in.next();
        Rational p = new Rational(new BigInteger(s.substring(0, s.indexOf('/'))), new BigInteger(s.substring(s.indexOf('/') + 1)));
        int[] is = in.nextIntArray(n);
        int N = Integer.highestOneBit(n + 1) << 2;
        double[] real = new double[N];
        double[] imag = new double[N];
        for (int i = 1; i <= n; i++) {
            real[i] = is[i - 1];
        }
        Algo.fft(1, real, imag);
        for (int i = 0; i < N; i++) {
            double re = real[i] * real[i] - imag[i] * imag[i];
            double im = imag[i] * real[i] * 2;
            real[i] = re;
            imag[i] = im;
        }
        Algo.fft(-1, real, imag);
        for (int i = 1; i <= n; i++) {
            real[i * 2] -= is[i - 1] * is[i - 1];
            real[i * 2] += is[i - 1] * (is[i - 1] - 1);
        }
        long fit = 0;
        for (int i = k; i < N; i++) {
            fit += (long) (real[i] + 0.5) / 2;
        }
//        for (int i = 0; i < N; i++) {
//            err.print((long) (real[i] + 0.5) + " ");
//        }
//        err.println();
        int fit2 = 0;
        for (int i = Math.max(1, k); i <= n; i++) {
            fit2 += is[i - 1];
        }
        int all2 = (int) Algo.sum(is);
        long all = (long) all2 * (all2 - 1) / 2;
        Rational q = Rational.ONE.sub(p);
//        Algo.debug(fit, all, fit2, all2);
        Rational res = new Rational(fit2, all2).mul(q).add(new Rational(fit, all).mul(p));
        if (res.compareTo(Rational.ZERO) > 0) out.println(res);
        else out.println("Impossible.");
        int cnt = 0;
        for (int i = 1; i < N; i++) {
            if (p.compareTo(Rational.ZERO) > 0 && real[i] > 0.5 ||
                    q.compareTo(Rational.ZERO) > 0 && i <= n && is[i - 1] > 0) {
                cnt++;
            }
        }
        out.println(cnt);
    }

}
