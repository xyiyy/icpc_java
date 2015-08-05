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
        int k = in.nextInt();
        int[] is = in.nextIntArray(n);
        int min = Algo.min(is);
        int max = Algo.max(is);
        if (max - min > k) out.println("NO");
        else {
            out.println("YES");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < is[i]; j++) {
                    out.print((Math.max(0, j - min) + 1) + " ");
                }
                out.println();
            }
        }
    }
}
