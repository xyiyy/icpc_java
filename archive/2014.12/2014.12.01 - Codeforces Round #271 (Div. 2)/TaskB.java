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
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        int m = in.nextInt();
        for (int i = 1; i < n; i++) is[i] += is[i - 1];
        while (m-- != 0) {
            int x = in.nextInt();
            out.println(Algo.lowerBound(is, x) + 1);
        }
    }
}
