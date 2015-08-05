package main;

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
        int t = in.nextInt();
        while (t-- != 0) solve();
    }

    private void solve() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        if (n <= 2) {
            out.println(n);
            return ;
        }
        Arrays.sort(is);
        int[][] dp = new int[n][n];
        Map<Integer, Integer> index = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            index.put(is[i], i);
        }
        int res = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int d = is[i] - is[j];
                int f = is[j] - d;
                if (index.containsKey(f)) {
                    dp[i][j] = dp[j][index.get(f)] + 1;
                } else {
                    dp[i][j] = 2;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        out.println(res);
    }
}
