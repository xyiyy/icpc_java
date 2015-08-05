package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1891 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        while (in.hasNext()) run();
    }

    void run() {
        String s = in.next();
        int sn = s.length();
        String t = in.next();
        int tn = t.length();
        int N = Integer.highestOneBit(sn + tn) << 1;
        double[] sr = new double[N];
        double[] si = new double[N];
        double[] tr = new double[N];
        double[] ti = new double[N];
        int[] is = new int[sn];
        int[] it = new int[tn];
        for (int i = 0; i < sn; i++) {
            is[i] = s.charAt(i) == 'a' ? 1 : s.charAt(i) == 'b' ? -1 : 0;
        }
        for (int i = 0; i < tn; i++) {
            it[i] = t.charAt(i) == 'a' ? 1 : t.charAt(i) == 'b' ? -1 : 0;
        }
        Algo.reverse(it);
        // a^4 * b^2
        int[] s42 = new int[N];
        Arrays.fill(sr, 0);
        Arrays.fill(si, 0);
        Arrays.fill(tr, 0);
        Arrays.fill(ti, 0);
        for (int i = 0; i < sn; i++) {
            sr[i] = is[i] * is[i] * is[i] * is[i];
        }
        for (int i = 0; i < tn; i++) {
            tr[i] = it[i] * it[i];
        }
        Algo.fft(1, sr, si);
        Algo.fft(1, tr, ti);
        for (int i = 0; i < N; i++) {
            double re = sr[i] * tr[i] - si[i] * ti[i];
            double im = sr[i] * ti[i] + si[i] * tr[i];
            sr[i] = re; si[i] = im;
        }
        Algo.fft(-1, sr, si);
        for (int i = 0; i < N; i++) {
            s42[i] = (int) (sr[i] + 0.5);
        }

        // a^2 * b^4
        int[] s24 = new int[N];
        Arrays.fill(sr, 0);
        Arrays.fill(si, 0);
        Arrays.fill(tr, 0);
        Arrays.fill(ti, 0);
        for (int i = 0; i < sn; i++) {
            sr[i] = is[i] * is[i];
        }
        for (int i = 0; i < tn; i++) {
            tr[i] = it[i] * it[i] * it[i] * it[i];
        }
        Algo.fft(1, sr, si);
        Algo.fft(1, tr, ti);
        for (int i = 0; i < N; i++) {
            double re = sr[i] * tr[i] - si[i] * ti[i];
            double im = sr[i] * ti[i] + si[i] * tr[i];
            sr[i] = re; si[i] = im;
        }
        Algo.fft(-1, sr, si);
        for (int i = 0; i < N; i++) {
            s24[i] = (int) (sr[i] + 0.5);
        }

        // a^3 * b^3
        int[] s33 = new int[N];
        Arrays.fill(sr, 0);
        Arrays.fill(si, 0);
        Arrays.fill(tr, 0);
        Arrays.fill(ti, 0);
        for (int i = 0; i < sn; i++) {
            sr[i] = is[i] * is[i] * is[i];
        }
        for (int i = 0; i < tn; i++) {
            tr[i] = it[i] * it[i] * it[i];
        }
        Algo.fft(1, sr, si);
        Algo.fft(1, tr, ti);
        for (int i = 0; i < N; i++) {
            double re = sr[i] * tr[i] - si[i] * ti[i];
            double im = sr[i] * ti[i] + si[i] * tr[i];
            sr[i] = re; si[i] = im;
        }
        Algo.fft(-1, sr, si);
        for (int i = 0; i < N; i++) {
            s33[i] = (int) Math.round(sr[i]);
        }

        int res = tn;
        for (int i = 0; i + tn <= sn; i++) {
            int sum = s42[i + tn - 1] + s24[i + tn - 1] - 2 * s33[i + tn - 1];
//            Algo.debug(i, sum, s42[i + tn - 1], s24[i + tn - 1], s33[i + tn - 1]);
            res = Math.min(res, sum / 4);
        }
        out.println(res);
    }
}
