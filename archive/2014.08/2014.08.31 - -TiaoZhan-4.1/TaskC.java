package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
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
        while (in.hasNext()) {
            long a = in.nextLong();
            long b = in.nextLong();
            long c = in.nextLong();
            int k = in.nextInt();
            if (a == 0 && b == 0 && c == 0 && k == 0) break;
            solve(a, b, c, k);
        }
    }

    private void solve(long a, long b, long c, int k) {
        long d = 1L << k;
        long e = b - a;
        long[] gs = Num.exGcd(c, d);
        if (e % gs[2] != 0) {
            out.println("FOREVER");
        } else {
            long res = e / gs[2] * gs[0] % d;
            res = (res + d) % d;
            out.println(res);
        }
    }

    private void solve2(long a, long b, long c, int k) {
        long d = 1L << k;
        long e = (b - a) % d;
        long g = Num.gcd(c, Num.gcd(d, e));
        c /= g; d /= g; e /= g;
        try {
            long inv = Num.inv(c, d);
            long res = inv * e % d;
            res = (res + d) % d;
            out.println(res);
        } catch (Throwable o) {
            out.println("FOREVER");
        }
    }
}
