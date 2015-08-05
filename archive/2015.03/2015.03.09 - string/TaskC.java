package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int cs = 1;
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            int t = in.nextInt();
            int p = in.nextInt();
            int q = in.nextInt();
            if (n == 0) break;
            out.printf("Case %d: ", cs++);
            solve(n, m, t, p, q);
        }
    }

    private void solve(int n, int m, int t, int p, int q) {
        long R = 1000000007;
        long C = 107;
        char[][] maps = in.nextCharMap(n);
        long[][] hs = new long[n][m];
        for (int i = 0; i < n; i++) {
            long pw = 1;
            for (int j = 0; j < q; j++) {
                hs[i][j] = (j > 0 ? hs[i][j - 1] : 0) * C + maps[i][j];
                pw *= C;
            }
            for (int j = q; j < m; j++) {
                hs[i][j] = hs[i][j - 1] * C + maps[i][j] - pw * maps[i][j - q];
            }
        }
        long[][] hs2 = new long[n][m];
        for (int j = 0; j < m; j++) {
            long pw = 1;
            for (int i = 0; i < p; i++) {
                hs2[i][j] = (i > 0 ? hs2[i - 1][j] : 0) * R + hs[i][j];
                pw *= R;
            }
            for (int i = p; i < n; i++) {
                hs2[i][j] = hs2[i - 1][j] * R + hs[i][j] - pw * hs[i - p][j];
            }
        }
        int cnt = 0;
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        for (int i = 0; i < t; i++) {
            long h = get(in.nextCharMap(p), R, C, p, q);
            Num.inc(map, h);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i >= p - 1 && j >= q - 1) {
                    if (map.containsKey(hs2[i][j])) {
                        cnt += map.get(hs2[i][j]);
                        map.remove(hs2[i][j]);
                    }
                }
            }
        }
        out.println(cnt);
    }

    private long get(char[][] maps, long R, long C, int n, int m) {
        long[][] hs = new long[n][m];
        for (int i = 0; i < n; i++) {
            long[] h = hs[i];
            for (int j = 0; j < m; j++) {
                h[j] = (j > 0 ? h[j - 1] : 0) * C + maps[i][j];
            }
        }
        for (int j = m - 1; j < m; j++) {
            for (int i = 0; i < n; i++) {
                hs[i][j] = (i > 0 ? hs[i - 1][j] : 0) * R + hs[i][j];
            }
        }
        return hs[n - 1][m - 1];
    }
}
