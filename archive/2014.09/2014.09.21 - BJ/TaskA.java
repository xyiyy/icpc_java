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
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d:%n", i);
            solve();
        }
    }

    private void solve() {
        long A = in.nextInt();
        long B = in.nextInt();
        int m = in.nextInt();
        long k = 1000 * B + 1000 * 1001 / 2;
        while (m-- != 0) {
            int a = in.nextInt();
            int b = in.nextInt();
            int x = in.nextInt();
            long ans = 0;
            for (int i = 1; i <= x; i++) {
                int up = i * b / a;
                if (up >= 1000) {
                    ans += k * ((x - i + 1) * A + (x + i) * (x - i + 1) / 2);
                    break;
                }
                ans += (up * B + up * (up + 1) / 2) * (i + A);
            }
            out.println(ans);
        }
    }
}
