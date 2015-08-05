package main;

import com.shu_mj.math.Matrix;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        long n = in.nextLong();
        int N = 25;
        int[] val = { 1, 5, 10, 25 };
        long[][] dp = new long[N][4];
        Arrays.fill(dp[0], 1);
        for (int j = 0; j < 4; j++) {
            for (int i = 1; i < N; i++) {
                if (i - val[j] >= 0) {
                    dp[i][j] += dp[i - val[j]][j];
                }
                if (j - 1 >= 0) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }
        long M = (long) (1e9 + 7);
        long[][] ini = new long[100][100];
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 4; j++) {
                ini[0][i * 4 + j] = dp[i][j];
            }
        }
        long[][] mat = new long[100][100];
        for (int i = 0; i + 4 < 100; i++) {
            mat[i + 4][i] = 1;
        }
        mat[96][96] = 1;
        mat[96][97] = 1; mat[81][97] = 1;
        mat[96][98] = 1; mat[81][98] = 1; mat[62][98] = 1;
        mat[96][99] = 1; mat[81][99] = 1; mat[62][99] = 1; mat[3][99] = 1;
        out.println(Matrix.mul(ini, Matrix.pow(mat, n, M), M)[0][3]);
//        Algo.debugTable(ini);
//        Algo.debugTable(mat);
//        Algo.debugTable(Matrix.pow(mat, n, M));
//        Algo.debugTable(Matrix.mul(ini, Matrix.pow(mat, n, M)));
    }
}
