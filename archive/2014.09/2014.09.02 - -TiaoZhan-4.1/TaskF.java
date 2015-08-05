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
        int n = in.nextInt();
        boolean[][] A = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            while (x != -1) {
                A[x - 1][i] = true;
                x = in.nextInt();
            }
        }
        boolean[] b = new boolean[n];
        Arrays.fill(b, true);
        boolean[][] x = Matrix.solutionSpace(A, b);
        if (x == null) out.println("No solution");
        else {
            boolean f = true;
            for (int i = 0; i < n; i++) {
                if (x[0][i]) {
                    if (f) f = false; else out.print(' ');
                    out.print((i + 1));
                }
            }
            out.println();
        }
    }
}
