package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1892 {
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
        int[] cnt = new int[sn - tn + 1];
        for (char c = 'a'; c <= 'c'; c++) {
            for (int i = 0; i < sn; i++) {
                is[i] = s.charAt(i) == c ? 0 : 1;
            }
            for (int i = 0; i < tn; i++) {
                it[i] = t.charAt(i) == c ? 1 : 0;
            }
            Algo.reverse(it);
            // a * b
            int[] s11 = new int[N];
            Arrays.fill(sr, 0);
            Arrays.fill(si, 0);
            Arrays.fill(tr, 0);
            Arrays.fill(ti, 0);
            for (int i = 0; i < sn; i++) {
                sr[i] = is[i];
            }
            for (int i = 0; i < tn; i++) {
                tr[i] = it[i];
            }
            Algo.fft(1, sr, si);
            Algo.fft(1, tr, ti);
            for (int i = 0; i < N; i++) {
                double re = sr[i] * tr[i] - si[i] * ti[i];
                double im = sr[i] * ti[i] + si[i] * tr[i];
                sr[i] = re;
                si[i] = im;
            }
            Algo.fft(-1, sr, si);
            for (int i = 0; i < N; i++) {
                s11[i] = (int) (sr[i] + 0.5);
            }
            for (int i = 0; i + tn <= sn; i++) {
                cnt[i] += s11[i + tn - 1];
            }
        }
        int res = tn;
        for (int i = 0; i + tn <= sn; i++) {
            res = Math.min(res, cnt[i]);
        }
        out.println(res);
    }
}
