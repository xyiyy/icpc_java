package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: ratios
     */
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int[] is = in.nextIntArray(3);
        int[][] iss = in.nextIntMatrix(3, 3);
        int ai = -1, aj = -1, ak = -1, r = 0;
        if (is[0] + is[1] + is[2] == 0) {
            out.println("0 0 0 1");
            return ;
        }
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                for (int k = 0; k < 100; k++) {
                    if (i + j + k == 0) continue;
                    int[] js = new int[3];
                    for (int u = 0; u < 3; u++) js[u] = iss[0][u] * i + iss[1][u] * j + iss[2][u] * k;
                    int ra = ratio(js, is);
                    if (ra < 0) continue;
                    if (r == 0 || i + j + k < ai + aj + ak) {
                        ai = i;
                        aj = j;
                        ak = k;
                        r = ra;
                    }
                }
            }
        }
        if (r == 0) out.println("NONE");
        else out.println(ai + " " + aj + " " + ak + " " + r);
    }

    private int ratio(int[] js, int[] is) {
        if (js[0] * is[1] != js[1] * is[0] || js[2] * is[1] != js[1] * is[2]) return -1;
        for (int i = 0; i < 3; i++) {
            if (is[0] != 0) {
                if (js[0] % is[0] != 0) return -1;
                return js[0] / is[0];
            }
        }
        return -1;
    }
}
