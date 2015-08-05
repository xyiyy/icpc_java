package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: buylow
     */
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        long[] is = in.nextLongArray(n);
        int[] dp = new int[n];
//        long[] cnt = new long[n];
        BigInteger[] cnt = new BigInteger[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
//            cnt[i] = 1;
            cnt[i] = BigInteger.ONE;
            Set<Long> pre = new HashSet<Long>();
            for (int j = i - 1; j >= 0; j--) {
                if (is[i] < is[j]) {
                    if (dp[i] < dp[j] + 1) {
                        pre.clear();
                        pre.add(is[j]);
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (dp[i] == dp[j] + 1) {
                        if (!pre.contains(is[j])) {
                            pre.add(is[j]);
//                            cnt[i] += cnt[j];
                            cnt[i] = cnt[i].add(cnt[j]);
                        }
                    }
                }
            }
        }
        int x = 0;
        for (int i = 0; i < n; i++) if (dp[i] > dp[x]) x = i;
//        long r = 0;
        BigInteger r = BigInteger.ZERO;
        Set<Long> set = new HashSet<Long>();
        for (int i = n - 1; i >= 0; i--) {
            if (dp[i] == dp[x]) {
                if (!set.contains(is[i])) {
                    set.add(is[i]);
//                    r += cnt[i];
                    r = r.add(cnt[i]);
                }
            }
        }
        out.println(dp[x] + " " + r);
    }
}
