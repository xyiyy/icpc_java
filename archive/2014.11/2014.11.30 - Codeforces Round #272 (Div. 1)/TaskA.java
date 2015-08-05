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
        long sum = 0;
        long mod = (long) (1e9 + 7);
        long a = in.nextInt();
        long b = in.nextInt();
        for (long i = 1; i < b; i++) {
            long x = b * i % mod * ((1 + a) * a / 2 % mod) % mod + a * i % mod;
            sum = (sum + x) % mod;
        }
        out.println(sum);
    }
}
