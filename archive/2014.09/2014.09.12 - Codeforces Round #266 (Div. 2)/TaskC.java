package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        if (n <= 2) {
            out.println(0);
            return ;
        }
        long[] is = in.nextLongArray(n);
        long sum = Algo.sum(is);
        if (sum % 3 != 0) {
            out.println(0);
            return ;
        }
        sum /= 3;
        long[] left = new long[n];
        long crt = 0;
        for (int i = 0; i < n; i++) {
            crt += is[i];
            if (crt == sum) left[i] = 1;
        }
        long[] right = new long[n];
        crt = 0;
        for (int i = n - 1; i >= 0; i--) {
            crt += is[i];
            if (crt == sum) right[i] = 1;
            if (i < n - 1) right[i] += right[i + 1];
        }
        long ans = 0;
        for (int i = 0; i < n - 2; i++) {
            if (left[i] == 1) {
                ans += right[i + 2];
            }
        }
        out.println(ans);
    }
}
