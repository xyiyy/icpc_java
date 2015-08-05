package main;

import com.shu_mj.math.Matrix;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0) {
            solve();
        }
    }

    static final int M = 10007;

    private void solve() {
        int n = in.nextInt();
        int x = in.nextInt() % M;
        int y = in.nextInt() % M;
        int x2 = x * x % M;
        int y2 = y * y % M;
        int txy = x * y % M * 2 % M;
        int[][] mat = {
                {x, 1, 0, 0, 0, 0},
                {y, 0, 0, 0, 0, 0},
                {0, 0, x2, 1, x, 1},
                {0, 0, y2, 0, 0, 0},
                {0, 0, txy, 0, y, 0},
                {0, 0, 0, 0, 0, 1}
        };
        int[][] ini = new int[6][6];
        ini[0] = new int[]{1, 1, 1, 1, 1, 0};
        mat = Matrix.pow(mat, n, M);
        out.println(Matrix.mul(ini, mat, M)[0][5]);
    }
}
