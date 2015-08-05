package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1006 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            long x = in.nextLong();
            BigInteger xa = BigInteger.valueOf(x);
            BigInteger xb = BigInteger.valueOf(x * 8);
            BigInteger xc = BigInteger.valueOf(x * 7);
            out.printf("Case #%d: %d%n", i + 1, xa.multiply(xb).subtract(xc).add(BigInteger.ONE));
        }
    }
}
