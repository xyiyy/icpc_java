package main;

import com.shu_mj.geo.P;
import com.shu_mj.tpl.PII;
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
        int[] ws = new int[n];
        int[] hs = new int[n];
        in.nextIntArray(n, ws, hs);
        int[] ss = Algo.merge(ws, hs);
        Arrays.sort(ss);
        ss = Algo.unique(ss);
        int res = Integer.MAX_VALUE;
        for (int hi = ss.length - 1; hi >= 0; hi--) {
            int h = ss[hi];
            PII[] ps = new PII[n];
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (hs[i] > h) {
                    ps[i] = new PII(hs[i], ws[i]);
                    cnt++;
                } else {
                    ps[i] = new PII(ws[i], hs[i]);
                }
            }
            if (cnt > n / 2) {
                break;
            }
            Arrays.sort(ps, new Comparator<PII>() {
                @Override
                public int compare(PII o1, PII o2) {
                    int v1 = o1.y - o1.x;
                    int v2 = o2.y - o2.x;
                    return v1 - v2;
                }
            });
            int w = 0, rh = 0;
            for (PII p : ps) {
                if (p.x > p.y && p.x <= h) {
                    if (cnt < n / 2) {
                        int t = p.x; p.x = p.y; p.y = t;
                        cnt++;
                    }
                }
                w += p.x;
                rh = Math.max(rh, p.y);
            }
            res = Math.min(res, w * rh);
        }
        out.println(res);
    }
}
