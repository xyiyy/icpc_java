package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1091 {
    Scanner in;
    PrintWriter out;
    private int n;
    private int m;
    private int k;
    private BitSet maps;
    private BitSet vis;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        int t = in.nextInt();
        maps = new BitSet(k * n * m);
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < m; l++) {
                    if (in.nextInt() == 1) maps.set(i * n * m + j * m + l);
                }
            }
        }
        vis = new BitSet(n * m * k);
        int ans = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < m; l++) {
                    if (!vis.get(i * n * m + j * m + l) && maps.get(i * n * m + j * m + l)) {
                        int c = dfs(i, j, l);
                        if (c >= t) ans += c;
                    }
                }
            }
        }
        out.println(ans);
    }

    int[] dx = { 0, 0, 0, 0, 1, -1 };
    int[] dy = { 0, 1, 0, -1, 0, 0 };
    int[] dz = { 1, 0, -1, 0, 0, 0 };
    private int dfs(int x, int y, int z) {
        vis.set(x * n * m + y * m + z);
        int c = 1;
        for (int i = 0; i < 6; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            int tz = z + dz[i];
            if (in(tx, ty, tz) && maps.get(tx * n * m + ty * m + tz) && !vis.get(tx * n * m + ty * m + tz)) {
                c += dfs(tx, ty, tz);
            }
        }
        return c;
    }

    private boolean in(int x, int y, int z) {
        return x >= 0 && y >= 0 && z >= 0 &&
                x < k && y < n && z < m;
    }
}
