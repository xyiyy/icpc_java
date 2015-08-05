package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1002 {
    Scanner in;
    PrintWriter out;
    private int n;
    private int m;
    private int k;
    private int[][] maps;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d:%n", i);
            solve();
        }
    }

    private void solve() {
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        int[] cs = in.nextIntArray(k);
        P[] ps = new P[k];
        for (int i = 0; i < k; i++) {
            ps[i] = new P(cs[i], i + 1);
        }
        maps = new int[n][m];
        if (!dfs(0, 0, ps)) out.println("NO");
    }

    private boolean dfs(int x, int y, P[] ps) {
        if (x == n) {
            out.println("YES");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    out.print(maps[i][j]);
                    if (j != m - 1) out.print(' ');
                    else out.println();
                }
            }
            return true;
        } else {
            if (y == m) return dfs(x + 1, 0, ps);
            ps = ps.clone();
            Arrays.sort(ps);
            if (ps[0].cnt * 2 > n * m - (x * n + y) + 1) return false;
            for (P p : ps) if (p.cnt > 0 && fit(x, y, p.color)) {
                p.cnt--;
                maps[x][y] = p.color;
                if (dfs(x, y + 1, ps)) return true;
                p.cnt++;
            }
            return false;
        }
    }

    private boolean fit(int x, int y, int color) {
        if (x > 0 && maps[x - 1][y] == color) return false;
        if (y > 0 && maps[x][y - 1] == color) return false;
        return true;
    }

    class P implements Comparable<P> {
        int cnt;
        int color;

        P(int cnt, int color) {
            this.cnt = cnt;
            this.color = color;
        }

        @Override
        public int compareTo(P o) {
            return o.cnt - cnt;
        }
    }
}
