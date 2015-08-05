package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1738 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            solve(n);
        }
    }

    private void solve(int n) {
        int[] is = in.nextIntArray(n);
        int[] ss = new int[n + 1];
        for (int i = 0; i < n; i++) ss[i + 1] = ss[i] + is[i];
        out.println(dfs(ss, 1, n));
    }

    private int dfs(int[] is, int b, int e) {
        if (b == e) return 0;
        int m = b;
        for (int i = b; i <= e; i++) {
            if (Math.abs(is[i] - is[b - 1] - (is[e] - is[i])) < Math.abs(is[m] - is[b - 1] - (is[e] - is[m]))) {
                m = i;
            }
        }
        return is[e] - is[b - 1] + dfs(is, b, m) + dfs(is, m + 1, e);
    }
}
