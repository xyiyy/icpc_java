package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskJ {
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
        int a = in.nextInt();
        int b = in.nextInt();
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        m -= k / n;
        k %= n;
        if (m <= 0) out.println(0);
        else {
            int res = 12341234;
            res = Math.min(res, a * m);
            res = Math.min(res, b * (n * m - k));
            res = Math.min(res, a * (m - 1) + (n - k) * b);
            out.println(res);
        }
    }
}
