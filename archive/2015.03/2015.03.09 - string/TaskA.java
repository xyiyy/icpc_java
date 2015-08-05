package main;

import com.shu_mj.ds.Hash2;
import com.shu_mj.ds.KMP;
import com.shu_mj.ds.SuffixArray;
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
        run();
    }

    void run() {
        char[] cs = in.next().toCharArray();
        int n = cs.length;
        Hash2 h = new Hash2(n);
        long[] hs = h.build(cs);
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (h.get(hs, 0, i + 1) == h.get(hs, n - i - 1, n)) cnt++;
        }
        out.println(cnt);
        int[] is = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int l = 1, r = n - i + 2;
            while (l < r) {
                int m = (l + r) / 2;
                if (i + m <= n && h.get(hs, i, i + m) == h.get(hs, 0, m)) l = m + 1;
                else r = m;
            }
            is[l - 1]++;
        }
        for (int i = n - 1; i >= 0; i--) {
            is[i] += is[i + 1];
        }
        for (int i = 0; i < n; i++) {
            if (h.get(hs, 0, i + 1) == h.get(hs, n - i - 1, n)) {
                out.println((i + 1) + " " + is[i + 1]);
            }
        }
    }
}
