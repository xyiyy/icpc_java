package main;

import com.shu_mj.ds.UnionFindSet;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: cowtour
     */
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] xs = new int[n];
        int[] ys = new int[n];
        for (int i = 0; i < n; i++) {
            xs[i] = in.nextInt();
            ys[i] = in.nextInt();
        }
        String[] con = new String[n];
        for (int i = 0; i < n; i++) {
            con[i] = in.next();
        }
        double[][] g = new double[n][n];
        final double INF = 1234123412341234.0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) if (i != j) {
                if (con[i].charAt(j) == '1') {
                    g[i][j] = dist(xs[i], ys[i], xs[j], ys[j]);
                } else {
                    g[i][j] = INF;
                }
            }
        }
        double[][] dis = new double[n][n];
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) dis[i][j] = g[i][j];
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dis[i][j] > dis[i][k] + dis[k][j]) {
                        dis[i][j] = dis[i][k] + dis[k][j];
                    }
                }
            }
        }
        double[] b = new double[n];
        UnionFindSet uf = new UnionFindSet(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dis[i][j] < INF) {
                    b[i] = Math.max(b[i], dis[i][j]);
                    uf.union(i, j);
                }
            }
        }
        double ans = INF;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) if (!uf.isSame(i, j)) {
                double max = b[i] + b[j] + dist(xs[i], ys[i], xs[j], ys[j]);
                for (int k = 0; k < n; k++) max = Math.max(max, b[k]);
                ans = Math.min(ans, max);
            }
        }
        out.printf("%.6f%n", ans);
    }

    private double dist(int x, int y, int x1, int y1) {
        x -= x1;
        y -= y1;
        return Math.sqrt(x * x + y * y);
    }
}
