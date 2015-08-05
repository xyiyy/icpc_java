package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: job
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
        int m1 = in.nextInt();
        int m2 = in.nextInt();
        int[] as = in.nextIntArray(m1);
        int[] bs = in.nextIntArray(m2);
        int[] ma = work(m1, as, n);
        int[] mb = work(m2, bs, n);
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, ma[i] + mb[n - i - 1]);
        }
        out.println(ma[n - 1] + " " + res);
    }

    private int[] work(int m, int[] is, int n) {
        int[] dy = new int[m];
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int x = 0;
            for (int j = 0; j < m; j++) {
                if (dy[j] + is[j] < dy[x] + is[x]) {
                    x = j;
                }
            }
            res[i] = dy[x] + is[x];
            dy[x] += is[x];
        }
        return res;
    }
}
