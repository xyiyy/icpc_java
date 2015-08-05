package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class A {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        if (fit(Arrays.copyOfRange(is, 1, n), is[0] - 1, is[0] + 1) ||
                fit(Arrays.copyOfRange(is, 0, n - 1), is[n - 1] - 1, is[n - 1] + 1))
            out.println("yes");
        else out.println("no");
    }

    private boolean fit(int[] is, int s, int t) {
        int n = is.length;
        int b = 0;
        int e = n - 1;
        for (int i = 0; i < n; i++) {
            if (is[b] == s) {
                b++;
                s--;
                continue;
            }
            if (is[b] == t) {
                b++;
                t++;
                continue;
            }
            if (is[e] == s) {
                e--;
                s--;
                continue;
            }
            if (is[e] == t) {
                e--;
                t++;
                continue;
            }
            return false;
        }
        return true;
    }
}
