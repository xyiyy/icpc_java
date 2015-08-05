package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0) {
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        for (int round = 0; round < (int) (5e5); round++) {
            int m = 0, M = 0;
            for (int i = 0; i < n; i++) {
                if (is[i] < is[m]) m = i;
                if (is[i] > is[M]) M = i;
            }
            if (m == M) {
                out.println(is[m]);
                return ;
            }
            is[M] = is[m] = is[M] - is[m];
        }
        out.println("Nooooooo!");
    }
}
