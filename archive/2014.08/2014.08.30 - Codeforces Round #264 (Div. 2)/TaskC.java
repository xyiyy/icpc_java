package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[][] mat = in.nextIntMatrix(n, n);
        long[][] s = new long[n][n];
        long[][] t = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    s[i][j] = mat[i][j];
                } else {
                    s[i][j] = s[i - 1][j - 1] + mat[i][j];
                }
                if (i == 0 || j == n - 1) {
                    t[i][j] = mat[i][j];
                } else {
                    t[i][j] = t[i - 1][j + 1] + mat[i][j];
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == n - 1 || j == n - 1) {

                } else {
                    s[i][j] = s[i + 1][j + 1];
                }
                if (i == n - 1 || j == 0) {

                } else {
                    t[i][j] = t[i + 1][j - 1];
                }
            }
        }
        long w = -1, b = -1;
        int wi = 0, wj = 0, bi = 0, bj = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                long sum = s[i][j] + t[i][j] - mat[i][j];
                if ((i + j) % 2 != 0) { // white
                    if (sum > w) {
                        w = sum;
                        wi = i;
                        wj = j;
                    }
                } else { // black
                    if (sum > b) {
                        b = sum;
                        bi = i;
                        bj = j;
                    }
                }
            }
        }
        out.println(w + b);
        out.println((wi + 1) + " " + (wj + 1) + " " + (bi + 1) + " " + (bj + 1));
    }
}
