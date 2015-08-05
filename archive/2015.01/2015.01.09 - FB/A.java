package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class A {
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
            out.printf("Case #%d: ", i);
            solve();
        }
    }

    private void solve() {
        char[] cs = in.next().toCharArray();
        int n = cs.length;
        int min = toi(cs);
        int max = toi(cs);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Algo.swap(cs, i, j);
                if (cs[0] != '0') {
                    int v = toi(cs);
                    min = Math.min(min, v);
                    max = Math.max(max, v);
                }
                Algo.swap(cs, i, j);
            }
        }
        out.println(min + " " + max);
    }

    private int toi(char[] cs) {
        return Integer.parseInt(String.valueOf(cs));
    }
}
