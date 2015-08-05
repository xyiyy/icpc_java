package main;

import com.shu_mj.tpl.PII;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        while (in.hasNext()) run();
    }

    void run() {
        int n = in.nextInt();
        int[] is = new int[n];
        int[] is2 = new int[n];
        in.nextIntArray(n, is, is2);
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(is[i] - is2[i], is2[i], i);
        }
        Arrays.sort(ps, new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                int v = o1.d - o2.d;
                if (v != 0) return -v;
                v = o1.v2 - o2.v2;
                if (v != 0) return v;
                return o1.id - o2.id;
            }
        });
        boolean f = true;
        for (P p : ps) {
            if (f) f = false; else out.print(" ");
            out.print(p.id);
        }
        out.println();
    }
    class P {
        int d;
        int v2;
        int id;

        public P(int d, int v2, int id) {
            this.d = d;
            this.v2 = v2;
            this.id = id;
        }
    }
}
