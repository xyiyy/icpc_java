package main;

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
        int n = in.nextInt();
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new V();
        }
        int[][] es = new int[n - 1][];
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int c = in.nextInt();
            es[i] = new int[] {u, v, c};
            vs[u].add(v);
            vs[v].add(u);
        }
        int[] cnt = new int[n];
        dfs(vs, cnt, 0, -1);
        double res = 0;
        double[] ps = new double[n];
        for (int i = 0; i < n - 1; i++) {
            if (cnt[es[i][0]] < cnt[es[i][1]]) {
                Algo.swap(es[i], 0, 1);
            }
            int num = cnt[es[i][1]];
            double p = 1;
            if (num >= 3) p -= C3(num) / C3(n);
            if (n - num >= 3) p -= C3(n - num) / C3(n);
            ps[i] = p;
            res += p * es[i][2];
        }
        int q = in.nextInt();
//        Algo.debug(ps);
        while (q-- != 0) {
            int r = in.nextInt() - 1;
            int nc = in.nextInt();
            res -= ps[r] * es[r][2];
            res += ps[r] * nc;
            es[r][2] = nc;
            out.printf("%.10f%n", res * 2);
        }
    }

    private double C3(int n) {
        return 1.0 * n * (n - 1) * (n - 2) / 3 / 2;
    }

    private void dfs(V[] vs, int[] cnt, int u, int fa) {
        cnt[u] = 1;
        for (int v : vs[u]) if (v != fa) {
            dfs(vs, cnt, v, u);
            cnt[u] += cnt[v];
        }
    }

    class V extends ArrayList<Integer> {

    }
}
