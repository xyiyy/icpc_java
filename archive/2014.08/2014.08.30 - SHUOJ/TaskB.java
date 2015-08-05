package main;

import com.shu_mj.geo.P;
import com.shu_mj.math.Matrix;
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
        while (in.hasNext()) solve();
    }

    void solve() {
        double[] is = in.nextDoubleArray(4);
        double[][] A = {
                { -1, 2, 0, 2 },
                { 2, -1, 2, 0 },
                { 0, 2, -1, 2 },
                { 2, 0, 2, -1 }
        };
        is[0] -= 1;
        double[] x = Matrix.solutionSpace(A, is)[0];
        for (double d : x) {
            if (!fit(d)) {
                out.println("NO!");
                return ;
            }
        }
        out.println("YES!");
    }

    private boolean fit(double d) {
        int i = Integer.parseInt(String.format("%.0f", d));
        return i >= 0 && P.add(d, -i) == 0;
    }

    void solve3() {
        int[] g = in.nextIntArray(4);
        int s = -7 * g[0] + 2 * g[1] + 8 * g[2] + 2 * g[3] + 7;
        if (s >= 0 && s % 15 == 0) out.println("YES!");
        else out.println("NO!");
    }

    private void solve2() {
        int[] g = in.nextIntArray(4);
        Queue<int[]> que = new LinkedList<int[]>();
        que.add(new int[] { 1, 0, 0, 0 });

        while (!que.isEmpty()) {
            int[] crt = que.poll();
            Algo.debug(crt, g, Arrays.equals(crt, g));
            if (Arrays.equals(crt, g)) {
                out.println("YES!");
                out.flush();
                return ;
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j <= crt[i]; j++) {
                    int[] ns = crt.clone();
                    ns[i] -= j;
                    ns[(i + 3) % 4] += j * 2;
                    ns[(i + 1) % 4] += j * 2;
                    if (!fit(ns, g)) continue;
                    que.add(ns);
                }
            }
        }
        out.println("NO!");
    }

    private boolean fit(int[] ns, int[] g) {
        return Algo.sum(ns) <= Algo.sum(g);
    }

}
