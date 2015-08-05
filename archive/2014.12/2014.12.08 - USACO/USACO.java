package main;

import com.shu_mj.tpl.PII;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: camelot
     */
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int r = in.nextInt();
        int c = in.nextInt();
        int y = in.next().charAt(0) - 'A';
        int x = in.nextInt() - 1;
        PII king = new PII(x, y);
        List<PII> ps = new ArrayList<PII>();
        while (in.hasNext()) {
            y = in.next().charAt(0) - 'A';
            x = in.nextInt() - 1;
            ps.add(new PII(x, y));
        }
        if (ps.size() == 0) {
            out.println(0);
            return ;
        }
        int[][] g = new int[r * c][];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                g[i * c + j] = getDis(r, c, i, j);
            }
        }
        int res = 12341234;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int d = 0;
                for (PII p : ps) {
                    d += g[i * c + j][p.x * c + p.y];
                }
                for (int ki = -2; ki <= 2; ki++) {
                    int kx = king.x + ki;
                    if (kx < 0 || kx >= r) continue;
                    for (int kj = -2; kj <= 2; kj++) {
                        int ky = king.y + kj;
                        if (ky < 0 || ky >= c) continue;
                        for (PII p : ps) {
                            res = Math.min(res, d - g[i * c + j][p.x * c + p.y]
                                    + g[i * c + j][kx * c + ky]
                                    + g[kx * c + ky][p.x * c + p.y]
                                    + Math.max(Math.abs(ki), Math.abs(kj)));
                        }
                    }
                }
            }
        }
        out.println(res);
    }


    private int[] getDis(int r, int c, int x, int y) {
        int[] g = new int[r * c];
        Arrays.fill(g, 12341234);
        g[x * c + y] = 0;
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(x * c + y);
        while (!que.isEmpty()) {
            int crt = que.poll();
            x = crt / c;
            y = crt % c;
            for (int i = 0; i < 8; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];
                int t = tx * c + ty;
                if (tx >= 0 && ty >= 0 && tx < r && ty < c && g[t] > g[crt] + 1) {
                    g[t] = g[crt] + 1;
                    que.add(t);
                }
            }
        }
        return g;
    }
    int[] dx = { -2, -2, -1, -1, 1, 1, 2, 2 };
    int[] dy = { 1, -1, 2, -2, 2, -2, 1, -1 };
}
