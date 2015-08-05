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
        int t = in.nextInt() - 1;
        int[] is = in.nextIntArray(n - 1);
        for (int i = 0; i <= n; i += is[i]) {
            if (i == t) {
                out.println("YES");
                return ;
            }
            if (i == n - 1) break;
        }
        out.println("NO");
    }
}
