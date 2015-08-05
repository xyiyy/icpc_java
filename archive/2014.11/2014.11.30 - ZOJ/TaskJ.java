package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskJ {
    Scanner in;
    PrintWriter out;
    private char[][] maps;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n < 8) break;
            solve(n);
        }
    }

    private void solve(int n) {
        maps = new char[n][n];
        Algo.fill(maps, ' ');
        dfs(0, 0, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                out.print(maps[i][j]);
            }
            out.println();
        }
        out.println();
    }

    final char star = '*';
    private void dfs(int x, int y, int n) {
        drawSq(x, y, n, n);
        if (n <= 4) return ;
        drawSq(x + n / 8, y + n / 8, n / 4 + 1, n / 4);
        drawSq(x + n / 8, y + n / 8 * 5, n / 4 + 1, n / 4);
        dfs2(x + n / 2, y + n / 4, n / 2);
    }

    private void dfs2(int x, int y, int n) {
        drawSq(x, y, n, n);
        if (n <= 4) return ;
        drawSq(x + n - n / 8 - n / 4 - 1, y + n / 8, n / 4 + 1, n / 4);
        drawSq(x + n - n / 8 - n / 4 - 1, y + n - n / 8 - n / 4, n / 4 + 1, n / 4);
        dfs(x, y + n / 4, n / 2);
    }

    private void drawSq(int x, int y, int n, int m) {
        for (int i = 0; i < n; i++) {
            maps[x + i][y] = maps[x + i][y + m - 1] = star;
        }
        for (int j = 0; j < m; j++) {
            maps[x][y + j] = maps[x + n - 1][y + j] = star;
        }
    }
}
