package main;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TheKingsArmyDiv1 {
    public int getNumber(String[] state) {
        if (state[0].indexOf('H') == -1 && state[1].indexOf('H') == -1) return -1;
        if (state[0].indexOf('S') == -1 && state[1].indexOf('S') == -1) return 0;
        int n = state[0].length();
        int res = n * 2;
        res = Math.min(res, way1(state));
        final int INF = n * 2;
        int[][][][] dp = new int[n][n][2][2];
        for (int[][][] iss : dp) Algo.fill(iss, INF);
        int[] maps = new int[n];
        for (int i = 0; i < n; i++) {
            if (state[0].charAt(i) == 'H') maps[i] |= 1;
            if (state[1].charAt(i) == 'H') maps[i] |= 2;
        }
        for (int l = 0; l <= n; l++) {
            for (int i = 0; i < n; i++) {
                int j = i + l - 1;
                if (j < 0) continue;
                if (j >= n) break;
                for (int u = 0; u < 2; u++) {
                    for (int v = 0; v < 2; v++) {
                        if (j < i) dp[i][j][u][v] = 0;
                        else if (j == i) {
                            if (u == v) {
                                if (maps[i] == 0) dp[i][j][u][v] = 2;
                                else if (maps[i] == 3) dp[i][j][u][v] = 1;
                                else if ((maps[i] & (1 << u)) != 0) dp[i][j][u][v] = 1;
                            }
                        } else {
                            int d = INF;
                            for (int k = i; k <= j; k++) {
                                for (int lu = 0; lu < 2; lu++) {
                                    for (int rv = 0; rv < 2; rv++) {
                                        int o1 = k - 1 < i ? INF : dp[i][k - 1][u][lu];
                                        int o2 = k + 1 >= j ? INF : dp[k + 1][j][rv][v];
                                        if (lu != rv) {
                                            if (maps[k] == 0) d = Math.min(d, o1 + o2 + 1);
                                            else d = Math.min(d, o1 + o2);
                                        } else {
                                            if ((maps[k] & (1 << lu)) != 0) d = Math.min(d, o1 + o2 - 1);
                                            else d = Math.min(d, o1 + o2);
                                        }
                                    }
                                }
                            }
                            dp[i][j][u][v] = d;
                        }
                    }
                }
            }
        }
        for (int u = 0; u < 2; u++) {
            for (int v = 0; v < 2; v++) {
                res = Math.min(res, dp[0][n - 1][u][v]);
            }
        }
        return res;
    }

    private int way1(String[] state) {
        int n = state[0].length();
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (state[0].charAt(i) == 'S' || state[1].charAt(i) == 'S') {
                res++;
            }
        }
        return res;
    }
}
