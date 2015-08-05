package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] maps = new char[n][];
        for (int i = 0; i < n; i++) {
            maps[i] = in.next().toCharArray();
        }
        int cnt = 0;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < m; i++) {
            if (!fit(maps, vis, i)) cnt++;
        }
        out.println(cnt);
    }

    private boolean fit(char[][] maps, boolean[] vis, int c) {
        boolean res = true;
        int n = maps.length;
        boolean[] nVis = new boolean[n];
        for (int i = 0; i + 1 < maps.length; i++) {
            if (!vis[i] && maps[i + 1][c] < maps[i][c]) {
                res = false;
            } else if (maps[i + 1][c] > maps[i][c]) {
                nVis[i] = true;
            }
        }
        if (res) for (int i = 0; i < n; i++) vis[i] |= nVis[i];
        return res;
    }
}
