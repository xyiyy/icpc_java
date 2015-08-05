package main;

import com.shu_mj.tpl.PII;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: spin
     */
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = 5;
        int[] sp = new int[n];
        PII[][] pss = new PII[n][];
        for (int i = 0; i < n; i++) {
            sp[i] = in.nextInt();
            int c = in.nextInt();
            pss[i] = new PII[c];
            for (int j = 0; j < c; j++) {
                int b = in.nextInt();
                int e = in.nextInt();
                pss[i][j] = new PII(b, e);
            }
        }
        for (int i = 0; i < 360; i++) {
            if (fit(n, sp, pss, i)) {
                out.println(i);
                return ;
            }
        }
        out.println("none");
    }

    private boolean fit(int n, int[] sp, PII[][] pss, int t) {
        int[] c = new int[361];
        for (int i = 0; i < n; i++) {
            for (PII p : pss[i]) {
                int b = p.x + sp[i] * t % 360 + 360;
                int e = b + p.y;
                b %= 360; e %= 360;
//                if ((p.x > p.y) ^ (b > e)) Algo.debug(p, b, e);
                if (b > e) {
                    c[b]++;
                    c[360]--;
                    c[0]++;
                    c[e + 1]--;
                } else {
                    c[b]++;
                    c[e + 1]--;
                }
            }
        }
        int crt = 0;
        for (int i = 0; i <= 360; i++) {
            crt += c[i];
            if (crt >= n) return true;
        }
        return false;
    }
}
