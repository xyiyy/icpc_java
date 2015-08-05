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
        int t = in.nextInt();
        while (t-- != 0) {
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        Arrays.sort(is);
        int g = 0;
        if (n % 2 == 0) {
            for (int i = 1; i < n; i += 2) {
                g ^= is[i] - is[i - 1] - 1;
            }
        } else {
            for (int i = 0; i < n; i += 2) {
                g ^= is[i] - (i > 0 ? is[i - 1] : 0) - 1;
            }
        }
        if (g == 0) out.println("Bob will win");
        else out.println("Georgia will win");
    }
}
