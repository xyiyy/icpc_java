package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
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
        BigInteger n = in.nextBigInteger().add(BigInteger.valueOf(2));
        int N = 400;
        BigInteger[] cnt = new BigInteger[N + 1];
        for (int i = N; i > 0; i--) {
            BigInteger x = n.divide(BigInteger.ONE.shiftLeft(i - 1)).subtract(BigInteger.valueOf(2));
            cnt[i] = x.add(BigInteger.ONE).divide(BigInteger.valueOf(2)).add(x.compareTo(BigInteger.valueOf(2)) >= 0 ? BigInteger.ONE : BigInteger.ZERO);
        }
        BigInteger res = BigInteger.ZERO;
//        Algo.debug(cnt);
        for (int i = N - 1; i > 0; i--) {
            BigInteger c = cnt[i].subtract(cnt[i + 1]);
            res = res.add(c.multiply(BigInteger.valueOf((i + 1) / 2)));
        }
        out.println(res);
    }
    void run2() {
        long n = in.nextLong();
        int N = 40;
        long[] cnt = new long[N + 1];
        for (int i = N; i > 0; i--) {
            long x = (n + 2) / (1L << (i - 1)) - 2;
            cnt[i] = (x + 1) / 2 + (x >= 2 ? 1 : 0);
        }
        long res = 0;
        for (int i = N - 1; i > 0; i--) {
            long c = cnt[i] - cnt[i + 1];
            res += (i + 1) / 2 * c;
        }
        out.println(res);
    }
}
