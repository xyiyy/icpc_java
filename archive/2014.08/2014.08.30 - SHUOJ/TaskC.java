package main;

import com.shu_mj.math.Matrix;
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
        while (in.hasNext()) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            int d = in.nextInt();
            int e = in.nextInt();
            int f = in.nextInt();
            if (a == 0 && b == 0 && c == 0 && d == 0 && e == 0 && f == 0) break;
            solve(a, b, c, d, e, f);
        }
    }

    private void solve(int a, int b, int c, int d, int e, int f) {
        double[][] A = {
                { 1, 0, 0, 0, 1, 1 },
                { 0, 1, 0, 1, 0, 1 },
                { 0, 0, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0, 0 },
                { 1, 0, 1, 0, 1, 0 },
                { 1, 1, 0, 0, 0, 1 }
        };
        double[] B = { a, c, e, b, d, f };
        double[] x = Matrix.solutionSpace(A, B)[0];
        out.printf("Anna's won-loss record is %.0f-%.0f.%n", x[0] + x[1] + x[2], x[3] + x[4] + x[5]);
    }
}
