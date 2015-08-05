package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class C {
    Scanner in;
    PrintWriter out;
    private int n;
    private int m;
    private char[][] mat;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        for (int i = 1; i <= n; i++) {
            out.printf("Case #%d: ", i);
            solve();
        }
    }

    private void solve() {
        n = in.nextInt();
        m = in.nextInt();
        mat = new char[n][];
        for (int i = 0; i < n; i++) {
            mat[i] = in.next().toCharArray();
        }
        int bx = 0, by = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 'S') {
                    bx = i;
                    by = j;
                    mat[i][j] = '.';
                }
            }
        }
        int[][][] vis = new int[n][m][4];
        Algo.fill(vis, INF);
        vis[bx][by][0] = 0;
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(en(bx, by, 0));
        while (!que.isEmpty()) {
            int crt = que.poll();
            int c = crt % 4; crt /= 4;
            int x = crt / m;
            int y = crt % m;
//            Algo.debug(x, y, c, crt);
            int cost = vis[x][y][c];
            if (mat[x][y] == 'G') {
                out.println(cost);
                return ;
            }
            for (int i = 0; i < 4; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];
                if (in(tx, ty) && dir[mat[tx][ty]] == -1 && mat[tx][ty] != '#') {
                    if (vis[tx][ty][(c + 1) % 4] == INF && fit(tx, ty, cost + 1)) {
                        vis[tx][ty][(c + 1) % 4] = cost + 1;
                        que.add(en(tx, ty, (c + 1) % 4));
                    }
                }
            }
        }
        out.println("impossible");
    }

    boolean in(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    int[] dir = new int[128];
    {
        Arrays.fill(dir, -1);
        dir['>'] = 0;
        dir['v'] = 1;
        dir['<'] = 2;
        dir['^'] = 3;
    }

    private boolean fit(int x, int y, int c) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int d = dir[mat[i][j]];
                if (d != -1) {
                    d = (d + c) % 4;
                    for (int k = 1; ; k++) {
                        int u = i + dx[d] * k;
                        int v = j + dy[d] * k;
                        if (!in(u, v)) {
                            break;
                        }
                        if (mat[u][v] == '#' || dir[mat[u][v]] != -1) {
                            break;
                        }
                        if (u == x && v == y) return false;
                    }
                }
            }
        }
        return true;
    }

    int[] dx = { 0, 1, 0, -1 };
    int[] dy = { 1, 0, -1, 0 };

    private int en(int x, int y, int c) {
//        Algo.debug("en", x, y, c, x * 4 * m + y * 4 + c);
        return x * 4 * m + y * 4 + c;
    }

    final int INF = 12341234;
}
