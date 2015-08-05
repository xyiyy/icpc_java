package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0) solve();
    }

    private void solve() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        int c = 0;
        for (int i = 1; i < n - 1; i++) {
            if (is[i] > is[i - 1] && is[i] > is[i + 1]) c++;
        }
        out.println(c);
    }
}
