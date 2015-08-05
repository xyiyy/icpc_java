package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1009 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        BigInteger dp[] = new BigInteger[2010];
        BigInteger a[] = new BigInteger[2010];
        dp[0] = BigInteger.ONE;
        dp[1] = BigInteger.ONE;
        dp[2] = BigInteger.ONE;
        dp[3] = BigInteger.valueOf(3);
        dp[4] = BigInteger.valueOf(5);
        a[0] = BigInteger.ZERO;
        a[1] = BigInteger.ZERO;
        a[2] = BigInteger.ONE;
        a[3] = BigInteger.ONE;
        a[4] = BigInteger.valueOf(2);
        for (int i = 5; i < 2010; i++) {
            dp[i] = dp[i - 1].add(dp[i - 2]);
            a[i] = a[i - 2].add(dp[i - 2]);
        }
        for (int i = 1; i < 2010; i++) {
            dp[i] = dp[i].add(dp[i - 1]);
        }
        BigInteger m;
        int t, n;
        t = in.nextInt();
        while (t != 0) {
            t--;
            n = in.nextInt();
            m = in.nextBigInteger();
            if (m.compareTo(BigInteger.ONE) == 0) {
                out.println(1);
                continue;
            }
            int i = 0;
            for (i = 0; i < 2010; i++) {
                if (dp[i].compareTo(m) >= 0) break;
            }
            i--;
            out.println(a[i + 1].add(m.subtract(dp[i].add(BigInteger.ONE))).mod(BigInteger.valueOf(258280327)));
        }
    }
}
