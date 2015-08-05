package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1890 {
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
            is[i] = s.charAt(i) - 'a';
        }
        for (int i = 0; i < tn; i++) {
            it[i] = t.charAt(i) - 'a';
        }
        Algo.reverse(it);
        int[] ss = new int[sn + 1];
        for (int i = 0; i < sn; i++) {
            ss[i + 1] = ss[i] + is[i] * is[i];
            sr[i] = is[i];
        }
        int[] ts = new int[tn + 1];
        for (int i = 0; i < tn; i++) {
            ts[i + 1] = ts[i] + it[i] * it[i];
            tr[i] = it[i];
        }
        Algo.fft(1, sr, si);
        Algo.fft(1, tr, ti);
        for (int i = 0; i < N; i++) {
            double re = sr[i] * tr[i] - si[i] * ti[i];
            double im = sr[i] * ti[i] + si[i] * tr[i];
            sr[i] = re; si[i] = im;
        }
        Algo.fft(-1, sr, si);
        int res = tn;
        for (int i = 0; i + tn <= sn; i++) {
            int sum = ss[i + tn] - ss[i] + ts[tn] - ts[0];
            int ab = (int) (sr[i + tn - 1] + 0.5);
            sum -= 2 * ab;
            res = Math.min(res, sum);
        }
        out.println(res);
    }
}
