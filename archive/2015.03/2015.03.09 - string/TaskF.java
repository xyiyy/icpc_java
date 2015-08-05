package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        while (in.hasNext()) run();
    }

    void run() {
        int n = in.nextInt();
        int l = in.nextInt();
        String[] ss = new String[n];
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            ss[i] = in.next();
            w[i] = in.nextInt();
        }
        List<String> pfx = new ArrayList<String>();
        for (String s : ss) {
            for (int j = 0; j <= s.length(); j++) {
                pfx.add(s.substring(0, j));
            }
        }
        Collections.sort(pfx);
        String[] pf = Algo.unique(pfx.toArray(new String[0]));
        int K = pf.length;
        boolean[] ng = new boolean[K];
        int[] index = new int[K];
        int[][] next = new int[K][4];
        for (int i = 0; i < K; i++) {
            ng[i] = false;
            for (int j = 0; j < n; j++) {
                ng[i] |= ss[j].length() <= pf[i].length()
                        && pf[i].substring(pf[i].length() - ss[j].length(), pf[i].length()).equals(ss[j]);
                if (ss[j].length() <= pf[i].length()
                        && pf[i].substring(pf[i].length() - ss[j].length(), pf[i].length()).equals(ss[j])) {
                    index[i] |= (1 << j);
                }
            }
            for (int j = 0; j < 4; j++) {
                String s = pf[i] + "AGCT".charAt(j);
                int k;
                for (;;) {
                    k = Algo.lowerBound(pf, s);
                    if (k < K && pf[k].equals(s)) break;
                    s = s.substring(1, s.length());
                }
                next[i][j] = k;
            }
        }
        int[] ws = new int[1 << n];
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    ws[i] += w[j];
                }
            }
        }
        int[][][] dp = new int[l + 1][1 << n][K];
        int INF = 12341234;
        Algo.fill(dp, -INF);
        dp[0][0][0] = 0;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < (1 << n); j++) {
                for (int k = 0; k < K; k++) if (dp[i][j][k] > -INF) {
                    for (int c = 0; c < 4; c++) {
                        int nk = next[k][c];
                        int nj = j | index[nk];
                        dp[i + 1][nj][nk] = Math.max(dp[i + 1][nj][nk], ws[nj]);
                    }
                }
            }
        }
        int res = -INF;
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < K; j++) {
                res = Math.max(res, dp[l][i][j]);
            }
        }
        if (res < 0) {
            out.println("No Rabbit after 2012!");
        } else {
            out.println(res);
        }
    }
}
