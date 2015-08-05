package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: frameup
     */
    Scanner in;
    PrintWriter out;
    private int[] p;
    private boolean[][] G;
    private int n;
    private char[] cs;
    private int[] d;
    private List<int[]> res;
    private boolean[] vis;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int h = in.nextInt();
        int w = in.nextInt();
        char[][] g = new char[h][];
        Set<Character> set = new TreeSet<Character>();
        for (int i = 0; i < h; i++) {
            g[i] = in.next().toCharArray();
            for (char c : g[i]) if (c != '.') set.add(c);
        }
        cs = Algo.unBox(set.toArray(new Character[0]));
        int[] index = new int[128];
        n = cs.length;
        for (int i = 0; i < n; i++) {
            index[cs[i]] = i;
        }
        int[] x0 = new int[n];
        int[] y0 = new int[n];
        int[] x1 = new int[n];
        int[] y1 = new int[n];
        Arrays.fill(x0, h);
        Arrays.fill(y0, w);
        Arrays.fill(x1, -1);
        Arrays.fill(y1, -1);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) if (g[i][j] != '.') {
                int id = index[g[i][j]];
                x0[id] = Math.min(x0[id], i);
                y0[id] = Math.min(y0[id], j);
                x1[id] = Math.max(x1[id], i);
                y1[id] = Math.max(y1[id], j);
            }
        }
        G = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = x0[i]; j <= x1[i]; j++) {
                if (g[j][y0[i]] != '.') G[index[g[j][y0[i]]]][i] = true;
                if (g[j][y1[i]] != '.') G[index[g[j][y1[i]]]][i] = true;
            }
            for (int j = y0[i]; j <= y1[i]; j++) {
                if (g[x0[i]][j] != '.') G[index[g[x0[i]][j]]][i] = true;
                if (g[x1[i]][j] != '.') G[index[g[x1[i]][j]]][i] = true;
            }
        }
        for (int i = 0; i < n; i++) G[i][i] = false;
        p = new int[n];
        d = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (G[j][i]) d[i]++;
            }
        }
        res = new ArrayList<int[]>();
        vis = new boolean[n];
        dfs(0);
        Collections.sort(res, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                for (int i = o1.length - 1; i >= 0; i--) {
                    if (o1[i] != o2[i]) return o1[i] - o2[i];
                }
                return 0;
            }
        });
        for (int[] is : res) {
            for (int i = n - 1; i >= 0; i--) {
                out.print(cs[is[i]]);
            }
            out.println();
        }
    }

    private void dfs(int c) {
        if (c == n) {
            res.add(p.clone());
        } else {
            for (int i = 0; i < n; i++) {
                if (d[i] == 0 && !vis[i]) {
                    for (int j = 0; j < n; j++) if (G[i][j]) d[j]--;
                    p[c] = i;
                    vis[i] = true;
                    dfs(c + 1);
                    vis[i] = false;
                    for (int j = 0; j < n; j++) if (G[i][j]) d[j]++;
                }
            }
        }
    }
}
