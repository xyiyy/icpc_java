package main;

import com.shu_mj.tpl.PII;
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
        int n = in.nextInt();
        int[] ts = new int[n];
        int[] ws = new int[n];
        in.nextIntArray(n, ts, ws);
        int oc = Algo.count(ts, 1);
        int tc = Algo.count(ts, 2);
        int[] ow = new int[oc];
        int[] tw = new int[tc];
        for (int i = 0, o = 0, t = 0; i < n; i++) {
            if (ts[i] == 1) ow[o++] = ws[i];
            else tw[t++] = ws[i];
        }
        Arrays.sort(ow);
        Arrays.sort(tw);
        for (int i = 1; i < oc; i++) {
            ow[i] += ow[i - 1];
        }
        for (int i = 1; i < tc; i++) {
            tw[i] += tw[i - 1];
        }
        int l = 0, r = (int) Algo.sum(ws) + 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (fit(ow, tw, oc, tc, m)) r = m;
            else l = m + 1;
        }
        out.println(l);
    }

    private boolean fit(int[] ow, int[] tw, int oc, int tc, int m) {
        for (int o = 0; o <= m && o <= oc; o++) {
            for (int t = 0; o + t * 2 <= m && t <= tc; t++) {
                int w = o + t * 2;
                int top = (oc - o - 1 < 0 ? 0 : ow[oc - o - 1]) + (tc - t - 1 < 0 ? 0 : tw[tc - t - 1]);
                if (top <= w) return true;
            }
        }
        return false;
    }
}
