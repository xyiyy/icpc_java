package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: ttwo
     */
    Scanner in;
    PrintWriter out;
    private int n;
    private char[][] maps;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, 1, 0, -1 };

    void run() {
        n = 10;
        maps = new char[n][];
        for (int i = 0; i < n; i++) {
            maps[i] = in.next().toCharArray();
        }
        boolean[] vis = new boolean[n * n * n * n * 4 * 4];
        int fx = 0, fy = 0, fd = 0;
        int cx = 0, cy = 0, cd = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (maps[i][j] == 'F') {
                    fx = i;
                    fy = j;
                }
                if (maps[i][j] == 'C') {
                    cx = i;
                    cy = j;
                }
            }
        }
        for (int i = 0; ; i++) {
            int fs = fd * 100 + fx * 10 + fy;
            int cs = cd * 100 + cx * 10 + cy;
            int st = fs * 400 + cs;
            if (vis[st]) {
                out.println(0);
                return ;
            }
            vis[st] = true;
            if (fx == cx && fy == cy) {
                out.println(i);
                return ;
            }
            int ftx = fx + dx[fd];
            int fty = fy + dy[fd];
            if (shouldTurn(ftx, fty)) fd = (fd + 1) % 4;
            else {
                fx = ftx;
                fy = fty;
            }
            int ctx = cx + dx[cd];
            int cty = cy + dy[cd];
            if (shouldTurn(ctx, cty)) cd = (cd + 1) % 4;
            else {
                cx = ctx;
                cy = cty;
            }
        }
    }

    private boolean shouldTurn(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n || maps[x][y] == '*';
    }
}
