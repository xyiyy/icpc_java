package main;

import com.shu_mj.math.Num;
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
        while (in.hasNext()) solve();
    }

    private void solve() {
        int n = in.nextInt();
        int m = n - in.nextInt();
        int[] ae5 = Num.modFact(n, 5);
        int[] ae2 = Num.modFact(n, 2);
        int[] bf5 = Num.modFact(m, 5);
        int[] bf2 = Num.modFact(m, 2);
        int a5 = ae5[0], e5 = ae5[1], a2 = ae2[0], e2 = ae2[1];
        int b5 = bf5[0], f5 = bf5[1], b2 = bf2[0], f2 = bf2[1];
        e5 -= f5;
        e2 -= f2;
        int x = Math.min(e5, e2), mod5, mod2;
        if (e5 - x > 0) mod5 = 0;
        else mod5 = a5 * (int) Num.inv(b5, 5) * (int) Num.inv(Num.pow(2, x, 5), 5) % 5;
        if (e2 - x > 0) mod2 = 0;
        else mod2 = a2 * (int) Num.inv(b2, 2) * (int) Num.inv(Num.pow(5, x, 2), 2) % 2;
        for (int i = 1; i < 10; i++) if (i % 2 == mod2 && i % 5 == mod5) {
            out.println(i);
        }
    }
}
