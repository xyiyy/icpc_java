package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int i = 1;
        while (in.hasNext()) {
            out.printf("Case %d: ", i++);
            solve();
            out.println();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        int g = is[0];
        for (int i = 1; i < n; i++) {
            g = Num.gcd(g, is[i]);
        }
        if (g != 1) out.println(-1);
        else {
            out.println((n - 1) * 2);
            for (int i = 1; i < n; i++) {
                out.println("1 " + (i + 1));
            }
            for (int i = 1; i < n; i++) {
                out.println("1 " + (i + 1));
            }
        }
    }
}
