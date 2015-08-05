package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        int g = Num.gcd(x, y);
        x /= g;
        y /= g;
        int k = x + y;
        while (n-- != 0) {
            int a = in.nextInt();
            a %= k;
            if (a == 0 || x == y || a == x + y - 1) {
                out.println("Both");
            } else {
                out.println(calc(x, y, a) ? "Vanya" : "Vova");
            }
        }
    }

    private boolean calc(int x, int y, int n) {
        int l = 0, r = x;
        while (l < r) {
            int m = (l + r) / 2;
            long times = hit(x, y, m);
            if (times > n) r = m;
            else l = m + 1;
        }
        if (hit(x, y, l - 1) == n) return true;
        return false;
    }

    private long hit(int x, int y, int m) {
        return m + (long) m * y / x;
    }
}
