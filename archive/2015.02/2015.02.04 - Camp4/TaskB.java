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
        int q = in.nextInt();
        char[] col = in.next().toCharArray();
        char[] row = in.next().toCharArray();
        int R = Math.min(n, 50);
        int C = Math.min(m, 50);
        boolean[][] matR = calc(row, col, R, m);
        boolean[][] matC = calc(row, col, n, C);
        while (q-- != 0) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            if (x < R) {
                out.println(matR[x][y] ? "Yes" : "No");
            } else if (y < C) {
                out.println(matC[x][y] ? "Yes" : "No");
            } else {
                if (x >= y) {
                    int id = x - y - 1 + R;
                    out.println(matC[id][C - 1] ? "Yes" : "No");
                } else {
                    int id = y - x - 1 + C;
                    out.println(matR[R - 1][id] ? "Yes" : "No");
                }
            }
        }
    }

    private boolean[][] calc(char[] row, char[] col, int n, int m) {
        boolean[][] mat = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    mat[i][j] = row[0] == '0' || col[0] == '0';
                } else if (i == 0) {
                    mat[i][j] = !mat[i][j - 1] || row[j] == '0';
                } else if (j == 0) {
                    mat[i][j] = !mat[i - 1][j] || col[i] == '0';
                } else {
                    mat[i][j] = !mat[i - 1][j] || !mat[i][j - 1];
                }
            }
        }
        return mat;
    }
}
