package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int sn = in.nextInt();
        int tn = in.nextInt();
        int k = in.nextInt();
        String s = in.next();
        String t = new StringBuilder(in.next()).reverse().toString();
        if (tn > sn) {
            out.println(0);
            return ;
        }
        int N = Integer.highestOneBit(sn + tn) << 1;
        double[] sr = new double[N];
        double[] si = new double[N];
        double[] tr = new double[N];
        double[] ti = new double[N];
        int[] cnt = new int[sn - tn + 1];
        for (char c : "AGCT".toCharArray()) {
            // a * b
            Arrays.fill(sr, 0);
            Arrays.fill(si, 0);
            Arrays.fill(tr, 0);
            Arrays.fill(ti, 0);
            for (int i = 0, l = 0, r = 0, crt = 0; i < sn; i++) {
                while (r <= i + k && r < sn) {
                    if (s.charAt(r++) == c) crt++;
                }
                while (l < i - k) {
                    if (s.charAt(l++) == c) crt--;
                }
                sr[i] = crt > 0 ? 0 : 1;
            }
            for (int i = 0; i < tn; i++) {
                tr[i] = t.charAt(i) == c ? 1 : 0;
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
            for (int i = 0; i + tn <= sn; i++) {
                cnt[i] += (int) (sr[i + tn - 1] + 0.5);
            }
        }
        out.println(Algo.count(cnt, 0));
    }
}
