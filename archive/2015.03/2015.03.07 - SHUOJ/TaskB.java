package main;

import com.shu_mj.math.Num;
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
        try {
        } catch (Throwable e) {
            for (;;) ;
        }
    }

    void run() {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            out.printf("Case #%d: ", i + 1);
            solve();
            try {
            } catch (Throwable e) {
                for (;;) ;
            }
            System.gc();
        }
    }

    private void solve() {
        int n = in.nextInt();
        long k = in.nextInt();
        System.gc();
        try {
            int[] is = in.nextIntArray(n);
            if (fit(is, k)) out.println("Yes.");
            else out.println("No.");
        } catch (OutOfMemoryError e) {
            for (;;) ;
        }
    }

    private boolean fit(int[] is, long k) {
        int n = is.length;
        long[] ss = new long[n];
        for (int i = 0; i < n; i++) {
            ss[i] = (i > 0 ? ss[i - 1] : 0) + (i % 2 == 0 ? 1 : -1) * is[i];
        }
        TreeSet<Long> set = new TreeSet<Long>();
        for (int i = n - 1; i >= 0; i--) {
            set.add(ss[i]);
            long v = k;
            if (i % 2 == 1) v *= -1;
            if (i > 0) v += ss[i - 1];
            if (set.contains(v)) return true;
        }
        return false;
    }

}
