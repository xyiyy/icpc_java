package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
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
        if (n < 3) {
            out.println(n);
            return ;
        }
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = 1;
            if (i > 0 && is[i - 1] < is[i]) left[i] += left[i - 1];
        }
        int[] right = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            right[i] = 1;
            if (i < n - 1 && is[i + 1] > is[i]) right[i] += right[i + 1];
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0) res = Math.max(res, left[i - 1] + 1);
            if (i < n - 1) res = Math.max(res, right[i + 1] + 1);
            if (i > 0 && i < n - 1 && is[i - 1] + 1 < is[i + 1]) res = Math.max(res, left[i - 1] + right[i + 1] + 1);
        }
        out.println(res);
    }
}
