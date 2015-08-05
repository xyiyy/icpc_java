package main;

import com.shu_mj.ds.Hash2;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    long M = (long) (1e9 + 7);
    void run() {
        char[] s = in.next().toCharArray();
        char[] t = in.next().toCharArray();
        int n = s.length;
        int m = t.length;
        Hash2 h = new Hash2(Math.max(n, m));
        long[] sh = h.build(s);
        long[] th = h.build(t);
        long[] dp = new long[n + 1];
        long[] pre = new long[n + 1];
        long[] ipre = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i >= m && h.get(sh, i - m, i) == h.get(th, 0, m)) {
                dp[i] += i - m + 1;
//                for (int j = 1; j <= i - m; j++) {
//                    dp[i] += dp[j] * (i - m - j + 1);
//                }
                dp[i] += pre[i - m] * (i - m) % M - ipre[i - m];
            } else dp[i] += dp[i - 1];
            dp[i] %= M;
            pre[i] = dp[i] + pre[i - 1];
            ipre[i] = dp[i] * (i - 1) % M + ipre[i - 1];
            pre[i] %= M;
            ipre[i] %= M;
        }
        pre[n] += M;
        pre[n] %= M;
        out.println(pre[n]);
    }
}
