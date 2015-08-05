package main;

import com.shu_mj.math.Matrix;
import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskH {
    Scanner in;
    PrintWriter out;
    private double[][] c;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        c = Num.doubleCombinationTable(100);
        while (in.hasNext()) {
            int a = in.nextInt();
            if (a <= 0) break;
            int m = in.nextInt();
            int b = in.nextInt();
            int n = in.nextInt();
            solve(a, m, b, n);
        }
    }

    private void solve(int a, int n, int b, int m) {
        double[][] A = new double[n * m + 1][n * m + 1];
        double[] B = new double[n * m + 1];
        A[0][0] = 1; B[0] = 1;
        for (int d = 0; d <= n * m; d++) {
            for (int k = 0; k <= d; k++) {
                double v = c[d][k];
                v *= Math.pow(a, k / n) * Math.pow(b, (d - k) / m);
                A[k % n * m + (d - k) % m + 1][d < n * m ? d + 1 : 0] += v;
            }
        }
        double[][] x = Matrix.solutionSpace(A, B);
        Algo.reverse(x[0], 1, x[0].length);
        for (int i = 0; i <= n * m; i++) {
            out.printf("%d%s", Math.round(x[0][i]), i == n * m ? System.lineSeparator() : " ");
        }
    }

    private void solve2(int a, int m, int b, int n) {
        double[][] A = new double[m * n + 1][m * n + 1];
        double[] B = new double[m * n + 1];
        for (int d = 0; d <= m * n; d++) {
            for (int k = 0; k <= d; k++) {
                double v = c[d][k];
                v *= Math.pow(a, k / m) * Math.pow(b, (d - k) / n);
                A[(k % m) * n + (d - k) % n][d] += v;
            }
        }
        A[m * n][m * n] = 1; B[m * n] = 1;
        double[][] x = Matrix.solutionSpace(A, B);
        for (int i = m * n; i >= 0; i--) {
            out.printf("%d%s", Math.round(x[0][i]), i == 0 ? System.lineSeparator() : " ");
        }
    }
}
