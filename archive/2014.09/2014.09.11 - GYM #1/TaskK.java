package main;

import com.shu_mj.ds.Hash;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskK {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        String[] ss = new String[n];
        for (int i = 0; i < n; i++) {
            ss[i] = in.next();
        }
        Arrays.sort(ss);
        String s = in.next();
        long[][] hss = new long[n][];
        Hash H = new Hash(200);
        for (int i = 0; i < n; i++) hss[i] = H.build(ss[i].toCharArray());
        long[] hs = H.build(s.toCharArray());
        int len = s.length();
        int[] dp = new int[len + 1];
        Arrays.fill(dp, 12341234);
        dp[0] = 0;
        for (int i = 0; i < len; i++) {
            dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
            int b = -1;
            for (int j = 0; j < n; j++) {
                int l = ss[j].length();
                if (b == -1 && l >= i && H.get(hss[j], 0, i) == H.get(hs, 0, i)) b = j;
                if (l > i && l <= len && H.get(hss[j], 0, i) == H.get(hs, 0, i) && H.get(hss[j], 0, l) == H.get(hs, 0, l)) {
                    dp[l] = Math.min(dp[l], dp[i] + j - b + 1);
//                    Algo.debug(i, j, dp[i], b, dp[l]);
                }
            }
        }
        out.println(dp[len] + 1);
    }
}
