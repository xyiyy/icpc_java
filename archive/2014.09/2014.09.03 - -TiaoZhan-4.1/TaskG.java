package main;

import com.shu_mj.ds.UnionFindSet;
import com.shu_mj.math.Matrix;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
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
        UnionFindSet uf = new UnionFindSet(n);
        double[][] R = new double[n][n];
        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            double r = in.nextDouble();
            uf.union(x, y);
            R[x][y] += 1 / r;
            R[y][x] += 1 / r;
        }
        double[][] A = new double[n][n];
        double[] b = new double[n];
        b[0] = 1;
        for (int i = 0; i < n; i++) {
            if (i != 0 && i != n - 1 && uf.same(0, i)) {
                for (int j = 0; j < n; j++) if (R[i][j] > 0) {
                    A[i][j] += R[i][j];
                    A[i][i] -= R[i][j];
                }
            } else {
                A[i][i] = 1;
            }
        }
        double[][] x = Matrix.solutionSpace(A, b);
        double ans = 0;
        for (int i = 0; i < n; i++) if (R[0][i] > 0) {
            ans += (1 - x[0][i]) * R[0][i];
        }
        out.printf("%.2f%n", 1 / ans);
    }
}
