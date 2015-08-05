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
        int xa = in.nextInt();
        int ya = in.nextInt();
        int xb = in.nextInt();
        int yb = in.nextInt();
        int n = in.nextInt();
        int res = 0;
        for (int i = 0; i < n; i++) {
            long a = in.nextInt();
            long b = in.nextInt();
            long c = in.nextInt();
            if ((a * xa + b * ya + c > 0) ^ (a * xb + b * yb + c > 0)) res++;
        }
        out.println(res);
    }
}
