package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
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
        while (in.hasNext()) {
            int d = in.nextInt();
            if (d == -1) break;
            solve(d);
        }
    }

    private void solve(int d) {
        int[] a = new int[d];
        int[] b = new int[d];
        for (int i = 1; i < d; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < d; i++) {
            b[i] = in.nextInt();
        }
        BigInteger m = in.nextBigInteger();
        BigInteger k = in.nextBigInteger();
        int[] mm = split(m, d);
        int[] kk = split(k, d);
        if (mm.length != kk.length) {
            out.println("NO");
            return ;
        }
        int n = mm.length;
        BigInteger[] A = new BigInteger[n];
        Arrays.fill(A, BigInteger.ONE);
        BigInteger[] B = new BigInteger[n];
        BigInteger[] M = new BigInteger[n];
        int[] aa = calc(a);
        int[] bb = calc(b);
        for (int i = 0; i < n; i++) {
            int[] cc;
            int[] c;
            if (i < n - 1) { cc = bb; c = b; }
            else { cc = aa; c = a; }
            int x = mm[i];
            int cnt = 0;
            while (x != kk[i]) {
                cnt++;
                x = c[x];
                if (x == mm[i]) {
                    out.println("NO");
                    return ;
                }
            }
            B[i] = BigInteger.valueOf(cnt);
            M[i] = BigInteger.valueOf(cc[mm[i]]);
        }
        BigInteger[] rs = Num.congruence(A, B, M);
        if (rs == null) {
            out.println("NO");
        } else {
            out.println(rs[0]);
        }
    }

    private int[] calc(int[] a) {
        int n = a.length;
        int[] r = new int[n];

        for (int i = 0; i < n; i++) if (r[i] == 0) {
            int c = 0;
            int x = i;
            do {
                c++;
                x = a[x];
            } while (x != i);

            do {
                r[x] = c;
                x = a[x];
            } while (x != i);
        }
        return r;
    }

    private int[] split(BigInteger m, int d) {
        List<Integer> list = new ArrayList<Integer>();
        BigInteger D = BigInteger.valueOf(d);
        do {
            list.add(m.mod(D).intValue());
            m = m.divide(D);
        } while (!m.equals(BigInteger.ZERO));
        return Algo.unBox(list.toArray(new Integer[0]));
    }
}
