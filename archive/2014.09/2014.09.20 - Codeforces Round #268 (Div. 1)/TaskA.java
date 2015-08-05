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
        if (n <= 3) out.println("NO");
        else {
            out.println("YES");
            int cnt = 0;
            for (int i = n; i >= 4; ) {
                if (i - 1 > 4) {
                    out.println(i + " - " + (i - 1) + " = 1");
                    i -= 2;
                    cnt++;
                } else {
                    break;
                }
            }
            if (n % 2 == 0) {
                out.println("4 * 3 = 12");
                out.println("12 * 2 = 24");
                cnt += 2;
            } else {
                out.println("5 - 1 = 4");
                out.println("4 - 2 = 2");
                out.println("4 * 3 = 12");
                out.println("12 * 2 = 24");
                cnt += 4;
            }
            for (int i = cnt; i < n - 1; i++) {
                out.println("24 * 1 = 24");
            }
        }
    }
}
