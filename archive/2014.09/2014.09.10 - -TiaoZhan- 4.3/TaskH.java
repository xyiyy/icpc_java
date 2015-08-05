package main;

import com.shu_mj.graph.SCC;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskH {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int[] s1 = in.nextIntArray(2);
        int[] s2 = in.nextIntArray(2);
        int[] ds1 = new int[n];
        int[] ds2 = new int[n];
        for (int i = 0; i < n; i++) {
            int[] is = in.nextIntArray(2);
            ds1[i] = dist(s1, is);
            ds2[i] = dist(s2, is);
        }
        int dss = dist(s1, s2);
        int[][] enemies = new int[a][2];
        int[][] friends = new int[b][2];
        for (int i = 0; i < a; i++) {
            enemies[i][0] = in.nextInt() - 1;
            enemies[i][1] = in.nextInt() - 1;
        }
        for (int i = 0; i < b; i++) {
            friends[i][0] = in.nextInt() - 1;
            friends[i][1] = in.nextInt() - 1;
        }
        int l = 0, r = (int) (1e7);
        while (l < r) {
            int m = (l + r) / 2;
            if (fit(n, ds1, ds2, dss, a, b, friends, enemies, m)) r = m;
            else l = m + 1;
        }
        if (l == (int) (1e7)) out.println(-1);
        else out.println(l);
    }

    private boolean fit(int n, int[] ds1, int[] ds2, int dss, int a, int b, int[][] friends, int[][] enemies, int m) {
        SCC.V[] vs = new SCC.V[n * 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new SCC.V();
        }
        for (int[] is : friends) {
            int x = is[0];
            int y = is[1];
            vs[x].add(vs[y]);
            vs[x + n].add(vs[y + n]);
            vs[y].add(vs[x]);
            vs[y + n].add(vs[x + n]);
        }
        for (int[] is : enemies) {
            int x = is[0];
            int y = is[1];
            vs[x].add(vs[y + n]);
            vs[y + n].add(vs[x]);
            vs[y].add(vs[x + n]);
            vs[x + n].add(vs[y]);
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (ds1[i] + ds1[j] > m) {
                    vs[i].add(vs[j + n]);
                    vs[j].add(vs[i + n]);
                }
                if (ds2[i] + ds2[j] > m) {
                    vs[i + n].add(vs[j]);
                    vs[j + n].add(vs[i]);
                }
                if (ds1[i] + ds2[j] + dss > m) {
                    vs[i].add(vs[j]);
                    vs[j + n].add(vs[i + n]);
                }
                if (ds2[i] + ds1[j] + dss > m) {
                    vs[i + n].add(vs[j + n]);
                    vs[j].add(vs[i]);
                }
            }
        }
        SCC.scc(vs);
        for (int i = 0; i < n; i++) {
            if (vs[i].comp == vs[i + n].comp) return false;
        }
        return true;
    }

    private int dist(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
