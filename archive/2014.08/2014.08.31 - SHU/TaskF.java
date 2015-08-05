package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) solve();
    }

    private void solve() {
        int n = in.nextInt();
        int[][] mat = in.nextIntMatrix(n, 3);
        int[] cs = new int[3];
        int[] have = new int[n];
        for (int i = 0; i < n; i++) {
            int[] is = mat[i];
            cs[0] += is[0];
            cs[1] += is[1];
            cs[2] += is[2];
            int h = is[0] + is[1] * 2 + is[2] * 4;
            have[i] = h - 3;
            if (h < 3) {
                out.println("Angry Yaoge!");
                return ;
            }
        }
        for (int i : have) {
            int four = Math.min(i / 4, cs[2]);
            i -= four * 4;
            cs[2] -= four;
            int two = Math.min(i / 2, cs[1]);
            i -= two * 2;
            cs[1] -= two;
            int one = Math.min(i, cs[0]);
            i -= one;
            cs[0] -= one;
            if (i != 0) {
                out.println("Sad Yaoge!");
                return ;
            }
        }
        out.println("Happy Yaoge!");
    }
}
