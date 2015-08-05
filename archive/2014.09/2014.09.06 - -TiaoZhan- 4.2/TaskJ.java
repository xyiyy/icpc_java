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
        int n = in.nextInt();
        int[] is = { 0, 6, 12, 22, 30, 32, 44, 54, 64, 76, 86, 98, 110, 118, 130, 132, 162, 170, 184, 194, 202, 282, 290, 302, 356, 1046 };
        for (int i : is) if (i == n) {
            out.println(2);
            return ;
        }
        out.println(1);
    }
}
