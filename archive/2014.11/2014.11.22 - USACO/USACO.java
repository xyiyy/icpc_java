package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: beads
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
        char[] cs = new char[n * 2];
        char[] is = in.next().toCharArray();
        System.arraycopy(is, 0, cs, 0, n);
        System.arraycopy(is, 0, cs, n, n);
        int res = 0;
        for (int i = 0; i < n * 2; i++) {
            res = Math.max(res, calc(cs, i, 'r', 'b'));
            res = Math.max(res, calc(cs, i, 'b', 'r'));
        }
        out.println(res);
    }

    private int calc(char[] cs, int l, char lc, char rc) {
        int n = cs.length / 2;
        int m = l;
        while (l >= 0 && m - l < n && (cs[l] == lc || cs[l] == 'w')) l--;
        l++;
        int r = m + 1;
        while (r < n * 2 && r - l < n && (cs[r] == rc || cs[r] == 'w')) r++;
        return r - l;
    }
}
