package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: snail
     */
    Scanner in;
    PrintWriter out;
    private int res;
    private char[][] maps;
    private int n;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        n = in.nextInt();
        int b = in.nextInt();
        maps = new char[n][n];
        Algo.fill(maps, '.');
        for (int i = 0; i < b; i++) {
            String s = in.next();
            maps[Integer.parseInt(s.substring(1)) - 1][s.charAt(0) - 'A'] = '#';
        }
        res = 0;
        dfs(0, 0, 0, 0);
        dfs(0, 0, 1, 0);
        out.println(res);
    }
    int[] dx = { 0, 1, 0, -1 };
    int[] dy = { 1, 0, -1, 0 };
    void dfs(int x, int y, int d, int s) {
//        Algo.debugTable(maps);
        int len = go(x, y, d);
//        Algo.debug(x, y, dx[d], dy[d], len, s, res);
        if (len <= 1) res = Math.max(res, s + Math.abs(len));
        else {
            fill(x, y, d, len - 1, '*');
            dfs(x + dx[d] * (len - 1), y + dy[d] * (len - 1), (d + 1) % 4, s + len - 1);
            dfs(x + dx[d] * (len - 1), y + dy[d] * (len - 1), (d + 3) % 4, s + len - 1);
            fill(x, y, d, len - 1, '.');
        }
    }

    private int go(int x, int y, int d) {
        for (int i = 0; ; i++, x += dx[d], y += dy[d]) {
            if (x < 0 || y < 0 || x >= n || y >= n || maps[x][y] == '#') return i;
            if (maps[x][y] == '*') return -i;
        }
    }

    private void fill(int x, int y, int d, int len, char c) {
        for (int i = 0; i < len; i++) {
            maps[x + dx[d] * i][y + dy[d] * i] = c;
        }
    }
}
