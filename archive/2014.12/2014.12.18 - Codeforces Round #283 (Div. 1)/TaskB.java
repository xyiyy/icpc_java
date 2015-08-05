package main;

import com.shu_mj.geo.P;
import com.shu_mj.math.Num;
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
        int[] is = new int[n];
        int[][] cs = new int[2][n];
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt() - 1;
            if (i != 0) {
                cs[0][i] = cs[0][i - 1];
                cs[1][i] = cs[1][i - 1];
            }
            cs[is[i]][i]++;
        }

        List<PII> res = new ArrayList<PII>();
        for (int i = 1; i <= n; i++) {
            PII p = new PII(0, i);
            if (fit(is, cs, i, p)) {
                res.add(p);
            }
        }
        PII[] ps = res.toArray(new PII[0]);
        Algo.sort(ps);
        ps = Algo.unique(ps);
        out.println(ps.length);
        for (PII p : ps) out.println(p.x + " " + p.y);
    }

    private boolean fit(int[] is, int[][] cs, int t, PII p) {
        int[] sc = new int[2];
        int n = is.length;
        int[] nid = new int[2];
        int[] ss = new int[2];
        int a = -1;
        for (int i = 0; i < n; ) {
            for (int j = 0; j < 2; j++) {
                nid[j] = Algo.lowerBound(cs[j], i, n, ss[j] + t);
            }
            if (nid[0] == n && nid[1] == n) return false;
            if (nid[0] < nid[1]) {
                i = nid[0] + 1;
                sc[0]++;
                a = 0;
            } else {
                i = nid[1] + 1;
                sc[1]++;
                a = 1;
            }
            ss[0] = cs[0][i - 1];
            ss[1] = cs[1][i - 1];
        }
        int last = is[n - 1];
        p.x = sc[last];
        return a == last && sc[last] > sc[last ^ 1] && t > 0 && p.x > 0;
    }

    private boolean fit(int[] is, int t, PII p) {
        int[] tc = new int[2];
        int[] sc = new int[2];
        int n = is.length;
        for (int i : is) {
            tc[i]++;
            if (tc[i] == t) {
                sc[i]++;
                tc[0] = tc[1] = 0;
            }
        }
        int last = is[n - 1];
        p.x = sc[last];
        return tc[last] == 0 && sc[last] > sc[last ^ 1] && t > 0 && p.x > 0;
    }
}
