package main;

import com.shu_mj.ds.Hash;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: theme
     */
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
        for (int i = n - 1; i > 0; i--) {
            is[i] -= is[i - 1];
        }
        is[0] = 0;
        Hash h = new Hash(n);
        long[] hs = h.build(is);
        int l = 1;
        int r = n / 2;
        while (l < r) {
            int m = (l + r) / 2;
            if (fit(h, hs, m, n)) l = m + 1;
            else r = m;
        }
        out.println(l < 5 ? 0 : l);
    }

    private boolean fit(Hash h, long[] hs, int len, int n) {
        Set<Long> set = new HashSet<Long>();
        for (int i = 0; i + len <= n; i++) {
            if (set.contains(h.get(hs, i, i + len))) return true;
            if (i - len > 0) set.add(h.get(hs, i - len, i));
        }
        return false;
    }
}
