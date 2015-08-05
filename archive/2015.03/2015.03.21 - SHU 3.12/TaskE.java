package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int h = in.nextInt();
        int[][][][][] dp = new int[2][2][h + 1][h + 1][h + 1];
        final int M = (int) (1e9 + 9);
        dp[0][1][1][1][1] = 1;
        for (int i = 0; i < n; i++) {
            Algo.fill(dp[(i + 1) & 1], 0);
            for (int live = 0; live < 2; live++) {
                for (int h0 = 0; h0 <= h; h0++) {
                    for (int h1 = 0; h1 <= h; h1++) {
                        for (int h2 = 0; h2 <= h; h2++) {
                            int old = dp[i & 1][live][h0][h1][h2];
                            if (old > 0) {
                                int nh0 = (h0 == 0 || h0 == h ? 0 : h0 + 1);
                                int nh1 = (h1 == 0 || h1 == h ? 0 : h1 + 1);
                                int nh2 = (h2 == 0 || h2 == h ? 0 : h2 + 1);
                                int nLive = (live == 0 || live == h ? 0 : live + 1);
                                dp[(i + 1) & 1][h0 > 0 ? 1 : 0][nLive][nh1][nh2] += old;
                                dp[(i + 1) & 1][h1 > 0 ? 1 : 0][nh0][nLive][nh2] += old;
                                dp[(i + 1) & 1][h2 > 0 ? 1 : 0][nh0][nh1][nLive] += old;
                                dp[(i + 1) & 1][live][nh0][nh1][nh2] += old;

                                dp[(i + 1) & 1][h0 > 0 ? 1 : 0][nLive][nh1][nh2] %= M;
                                dp[(i + 1) & 1][h1 > 0 ? 1 : 0][nh0][nLive][nh2] %= M;
                                dp[(i + 1) & 1][h2 > 0 ? 1 : 0][nh0][nh1][nLive] %= M;
                                dp[(i + 1) & 1][live][nh0][nh1][nh2] %= M;
//                                Algo.debug(i, live, h0, h1, h2, old);
//                                Algo.debug(nh0, nh1, nh2);
                            }
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int live = 0; live < 2; live++) {
            for (int h0 = 0; h0 <= h; h0++) {
                for (int h1 = 0; h1 <= h; h1++) {
                    for (int h2 = 0; h2 <= h; h2++) {
                        if (live > 0 || h0 != 0 || h1 != 0 || h2 != 0) {
                            res += dp[n & 1][live][h0][h1][h2];
                            res %= M;
                        }
                    }
                }
            }
        }
        out.println(res);
    }
}
