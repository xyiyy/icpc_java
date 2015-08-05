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
        int n = in.nextInt();
        int s = in.nextInt();
        int[][] mat = in.nextIntMatrix(n, 2);
        int ans = -1;
        for (int[] is : mat) {
            if (is[1] == 0 && s >= is[0]) {
                ans = Math.max(ans, 0);
            }
            if (s > is[0] && is[1] != 0) {
                ans = Math.max(ans, (100 - is[1]));
            }
        }
        out.println(ans);
    }
}
