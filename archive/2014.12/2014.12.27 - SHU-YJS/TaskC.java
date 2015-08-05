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
        while (in.hasNext()) run();
    }

    void run() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        int k = in.nextInt();
        int[] cs = new int[3000 + 1];
        int[] ls = new int[3000 + 1];
        int[] rs = new int[3000 + 1];
        for (int i : is) {
            cs[i]++;
            ls[i - k]++;
            rs[i + k]++;
        }
        for (int i = 1; i < 3000 + 1; i++) {
            cs[i] += cs[i - 1];
            ls[i] += ls[i - 1];
            rs[i] += rs[i - 1];
        }
        int cnt = 0;
        int x = -1;
        for (int i = 0; i < n; i++) {
            if (fit(cs, ls, rs, k, is[i])) {
                cnt++;
                x = i + 1;
            }
        }
        if (cnt == 1) {
            out.println("YES");
            out.println(x);
        } else {
            out.println("NO");
            out.println(cnt);
        }
    }

    private boolean fit(int[] cs, int[] ls, int[] rs, int k, int i) {
        int r = ls[i + k];
        int l = (i - k == 0 ? 0 : rs[i - k - 1]) + 1;
        int m = (cs[3000] + 1) / 2;
        return l <= m && m <= r || (cs[3000] % 2 == 0 && l <= m + 1 && m + 1 <= r);
    }
}
