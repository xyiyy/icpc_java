package main;

import com.shu_mj.ds.Palindrome;
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
        while (in.hasNext()) run();
    }

    void run() {
        char[] cs = in.next().toCharArray();
        int[] ps = Palindrome.palindrome(cs);
        int n = cs.length;
        long[] f = new long[n + 1];
        for (int i = 0; i < n; i++) {
            f[i]++;
            f[i + (ps[i * 2] + 1) / 2]--;
            if (ps[i * 2 + 1] != 0) {
                f[i + 1]++;
                f[i + 1 + ps[i * 2 + 1] / 2]--;
            }
        }
        for (int i = 0; i < n; i++) {
            f[i + 1] += f[i];
        }
        for (int i = 0; i < n; i++) {
            f[i + 1] += f[i];
        }
        for (int i = 0; i < n; i++) {
            f[i + 1] += f[i];
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            res += get(f, i - 1) - get(f, i - 2 - ps[i * 2] / 2);
            res += get(f, i - 1) - get(f, i - 1 - ps[i * 2 + 1] / 2);
        }
        out.println(res);
    }

    private long get(long[] f, int i) {
        return i < 0 ? 0 : f[i];
    }
}
