package main;

import com.shu_mj.graph.Dijkstra;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: butter
     */
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
        int r = in.nextInt();
        int[] is = in.nextIntArray(n);
        int[][] iss = in.nextIntMatrix(r, 3);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            res = Math.min(res, calc(i, n, m, r, is, iss));
        }
        out.println(res);
    }

    private int calc(int b, int n, int m, int r, int[] is, int[][] iss) {
        Dijkstra.V[] vs = new Dijkstra.V[m];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new Dijkstra.V();
        }
        for (int[] e : iss) {
            vs[e[0] - 1].add(vs[e[1] - 1], e[2]);
            vs[e[1] - 1].add(vs[e[0] - 1], e[2]);
        }
        Dijkstra.dijkstra(vs[b]);
        int res = 0;
        for (int i : is) {
            if (vs[i - 1].min > 12341234) return 12341234;
            res += vs[i - 1].min;
        }
        return res;
    }
}
