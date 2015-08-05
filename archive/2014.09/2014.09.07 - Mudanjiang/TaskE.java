package main;

import com.shu_mj.ds.BIT;
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
        int p = in.nextInt();
        char[] cs = in.next().toCharArray();
        BIT b0 = new BIT(p);
        BIT b1 = new BIT(p);
        for (int i = 0, s = 1; i < p; i++, s *= -1) {
            b0.add(i, (cs[i] - '0') * s);
            b1.add(i, (cs[i] - '0') * s * -1);
        }
        int q = in.nextInt();
        while (q-- != 0) {
            int t = in.nextInt();
            int l = in.nextInt() - 1;
            int r = in.nextInt();
            if (t == 1) { // set

            } else { // query

            }
        }
    }
}
