package main;

import com.shu_mj.ds.BIT;
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
        int[] is = in.nextIntArray(n);
        int[] js = in.nextIntArray(n);
        int[] ci = calc(is);
        int[] cj = calc(js);
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            int p = n - i - 1;
            c[p] += ci[p] + cj[p];
            if (p != 0) c[p - 1] += c[p] / (i + 1);
            c[p] %= (i + 1);
        }
//        Algo.debug(ci);
//        Algo.debug(cj);
//        Algo.debug(c);
        BIT b = new BIT(n);
        for (int i = 0; i < n; i++) {
            b.add(i, 1);
        }
        for (int i = 0; i < n; i++) {
            int v = b.get(c[i]);
            out.print(v + " ");
            b.add(v, -1);
        }
        out.println();
    }

    private int[] calc(int[] is) {
        int n = is.length;
        int[] c = new int[n];
        BIT b = new BIT(n);
        for (int i = 0; i < n; i++) {
            c[i] = is[i] - b.sum(0, is[i]);
            b.add(is[i], 1);
        }
        return c;
    }
}
