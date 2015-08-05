package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: twofive
     */
    Scanner in;
    PrintWriter out;
    private int[] xs;
    private int[] ys;
    private int[] dp;
    private int[] r;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        xs = new int[25];
        ys = new int[25];
        dp = new int[6 * 6 * 6 * 6 * 6];
        r = new int[5];
        Arrays.fill(xs, -1);
        Arrays.fill(ys, -1);
        if (in.next().charAt(0) == 'N') {
            int n = in.nextInt();
            for (int i = 0; i < 25; i++) {
                for (int j = 0; j < 25; j++) if (xs[j] == -1) {
                    xs[j] = i / 5;
                    ys[j] = i % 5;
                    int res = calc();
                    if (res >= n) {
                        out.print((char) ('A' + j));
                        break;
                    } else {
                        n -= res;
                    }
                    xs[j] = -1;
                    ys[j] = -1;
                }
            }
            out.println();
        } else {
            char[] cs = in.next().toCharArray();
            int res = 0;
            for (int i = 0; i < 25; i++) {
                for (int j = 0; j < cs[i] - 'A'; j++) if (xs[j] == -1) {
                    xs[j] = i / 5;
                    ys[j] = i % 5;
                    res += calc();
                    xs[j] = -1;
                    ys[j] = -1;
                }
                xs[cs[i] - 'A'] = i / 5;
                ys[cs[i] - 'A'] = i % 5;
            }
            out.println(res + 1);
        }
    }

    int calc() {
        Arrays.fill(dp, -1);
        Arrays.fill(r, 0);
        return dfs(0);
    }

    int dfs(int ch) {
        if (ch == 25) return 1;
        int state = 0;
        for (int i : r) state = state * 6 + i;
        if (dp[state] != -1) return dp[state];
        int res = 0;
        if (xs[ch] == -1) {
            for (int i = 0; i < 5; i++) if ((i == 0 || r[i] < r[i - 1]) && r[i] < 5) {
                r[i]++;
                res += dfs(ch + 1);
                r[i]--;
            }
        } else {
            int i = xs[ch];
            if ((i == 0 || r[i] < r[i - 1]) && r[i] == ys[ch]) {
                r[i]++;
                res += dfs(ch + 1);
                r[i]--;
            }
        }
        return dp[state] = res;
    }
}
