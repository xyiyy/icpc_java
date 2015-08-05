package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
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
    void solve() {
        int l = in.nextInt();
        int v = in.nextInt();
        int u = in.nextInt();
        out.printf("%.2f%n", (Math.pow(Math.E, u * 1.0 / v) - 1) * l / u);
    }
}
