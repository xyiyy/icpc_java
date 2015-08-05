package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int t = in.nextInt();
        int[] V = in.nextIntArray(n);
        int[] C = in.nextIntArray(n);
        int maxV = Algo.max(V);
        maxV *= maxV;
        int[] change = new int[maxV];
        Arrays.fill(change, -1);
        change[0] = 0;
        for (int i : V) {
            for (int j = i; j < maxV; j++) if (change[j - i] != -1) {
                if (change[j] == -1 || change[j] > change[j - i] + 1) {
                    change[j] = change[j - i] + 1;
                }
            }
        }
        int[] dp = new int[t + maxV];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        int[] deq = new int[t + maxV];
        int[] id = new int[t + maxV];
        for (int i = 0; i < n; i++) {
            int v = V[i];
            int c = C[i];
            for (int j = 0; j < v; j++) {
                int b = 0, e = 0;
                for (int k = 0; k * v + j < t + maxV; k++) {
                    int l = k * v + j;
                    if (dp[l] != -1) {
                        while (b < e && deq[e - 1] >= dp[l] - k) e--;
                        id[e] = k;
                        deq[e++] = dp[l] - k;
                    }
                    if (b != e) dp[l] = deq[b] + k;
                    if (b != e && id[b] == k - c) b++;
                }
            }
        }
        int ans = -1;
//        Algo.debug(dp);
//        Algo.debug(change);
        for (int i = t; i < t + maxV; i++) {
            if (dp[i] != -1 && change[i - t] != -1) {
                int tmp = dp[i] + change[i - t];
                if (ans == -1 || ans > tmp) {
                    ans = tmp;
                }
            }
        }
        out.println(ans);
    }
}
