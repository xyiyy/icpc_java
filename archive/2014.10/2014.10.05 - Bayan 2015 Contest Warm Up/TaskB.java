package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
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
        boolean[][] g = new boolean[n * m][n * m];
        char[] rs = in.next().toCharArray();
        for (int i = 0; i < n; i++) {
            if (rs[i] == '>') {
                for (int j = 0; j < m - 1; j++) {
                    g[i * m + j][i * m + j + 1] = true;
                }
            } else {
                for (int j = m - 1; j > 0; j--) {
                    g[i * m + j][i * m + j - 1] = true;
                }
            }
        }
        char[] cs = in.next().toCharArray();
        for (int j = 0; j < m; j++) {
            if (cs[j] == 'v') {
                for (int i = 0; i < n - 1; i++) {
                    g[i * m + j][(i + 1) * m + j] = true;
                }
            } else {
                for (int i = n - 1; i > 0; i--) {
                    g[i * m + j][(i - 1) * m + j] = true;
                }
            }
        }
        for (int i = 0; i < n * m; i++) {
            g[i][i] = true;
        }
        for (int k = 0; k < n * m; k++) {
            for (int i = 0; i < n * m; i++) {
                for (int j = 0; j < n * m; j++) {
                    g[i][j] |= g[i][k] && g[k][j];
                }
            }
        }
        for (int i = 0; i < n * m; i++) {
            for (int j = 0; j < n * m; j++) {
                if (!g[i][j]) {
                    out.println("NO");
                    return ;
                }
            }
        }
        out.println("YES");
    }
}
