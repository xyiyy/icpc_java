package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
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
        int m = in.nextInt();
        int[] js = in.nextIntArray(m);
        int[] ds = Algo.merge(is, js);
        Algo.sort(ds);
        ds = Algo.unique(ds);
        int a = n * 3;
        int b = m * 3;
        int ma = a;
        int mb = b;
        int ai = 0;
        int bi = 0;
        Algo.sort(is);
        Algo.sort(js);
        for (int i : ds) {
            while (ai < n && is[ai] <= i) {
                ai++;
                a--;
            }
            while (bi < m && js[bi] <= i) {
                bi++;
                b--;
            }
            if (a - b > ma - mb || a - b == ma - mb && a > ma) {
                ma = a;
                mb = b;
            }
        }
        out.println(ma + ":" + mb);
    }
}
