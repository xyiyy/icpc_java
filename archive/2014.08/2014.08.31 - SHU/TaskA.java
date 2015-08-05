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
        while (in.hasNext()) solve();
    }

    private void solve() {
        int n = in.nextInt();
        int x = in.nextInt();
        int cnt = (n + 4) / 5;
        double[] ps = new double[cnt + 1];
        ps[0] = 0;
        ps[1] = 1;
        double upper = (99 - x) * 0.01;
        double lower = (x + 1) * 0.01;
        for (int i = 2; i <= n; i++) {
            for (int j = cnt; j > 0; j--) {
                ps[j] = ps[j] * upper + ps[j - 1] * lower;
            }
        }
        double res = Algo.sum(ps);
        if (res < 1e-6) out.println("Happy Yaoge!");
        else out.printf("%.6f%n", res);
    }
}
