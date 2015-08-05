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
        int[] is = in.nextIntArray(n);
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(is[i], i);
        }
        Arrays.sort(ps, new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                return o1.x - o2.x;
            }
        });
        int[][] iss = new int[3][n];
        int f = 0;
        for (int i = 0; i < n; i++) {
            iss[0][i] = iss[1][i] = iss[2][i] = ps[i].y;
            if (i != 0) {
                if (ps[i].x == ps[i - 1].x) {
                    if (f == 0) {
                        iss[1][i - 1] = ps[i].y;
                        iss[1][i] = ps[i - 1].y;
                        iss[2][i - 1] = ps[i].y;
                        iss[2][i] = ps[i - 1].y;
                        f = 1;
                    } else if (f == 1) {
                        iss[2][i] = iss[2][i - 1];
                        iss[2][i - 1] = ps[i].y;
                        f = 2;
                    }
                }
            }
        }

        if (f == 2) {
            out.println("YES");
            for (int[] a : iss) {
                for (int i : a) out.print((i + 1) + " ");
                out.println();
            }
        } else {
            out.println("NO");
        }
    }
    class P {
        int x;
        int y;

        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
