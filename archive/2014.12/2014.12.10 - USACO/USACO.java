package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: rockers
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
        int t = in.nextInt();
        int m = in.nextInt();
        int[] is = in.nextIntArray(n);
        int ans = 0;
        for (int i = 0; i < (1 << n); i++) {
            if (fit(is, i, t, m)) {
                ans = Math.max(ans, Integer.bitCount(i));
            }
        }
        out.println(ans);
    }

    private boolean fit(int[] is, int x, int t, int m) {
        for (int i = 0, crt = 0, cnt = 0; i < is.length; i++) {
            if ((x & (1 << i)) != 0) {
                if (is[i] > t) return false;
                if (crt + is[i] > t) {
                    crt = is[i];
                    cnt++;
                    if (cnt >=   m) return false;
                } else {
                    crt += is[i];
                }
            }
        }
        return true;
    }
}
