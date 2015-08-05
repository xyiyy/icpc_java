package main;

import com.shu_mj.ds.UnionFindSet;
import com.shu_mj.math.Matrix;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskJ {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0) solve();
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt() - 1;
        int t = in.nextInt() - 1;
        UnionFindSet uf = new UnionFindSet(n);
        UnionFindSet uf0 = new UnionFindSet(n);
        int[] us = new int[m];
        int[] vs = new int[m];
        int[] cs = new int[m];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int c =  in.nextInt();
            if (c == 0) uf0.union(u, v);
            uf.union(u, v);
            us[i] = u;
            vs[i] = v;
            cs[i] = c;
        }
        if (!uf.isSame(s, t)) out.println("inf");
        else if (uf0.isSame(s, t)) out.printf("%.6f%n", 0.0);
        else {
            int[] id = new int[n];
            Arrays.fill(id, -1);
            int cnt = 0;
            for (int i = 0; i < n; i++) if (uf0.pre[i] == i && uf.isSame(i, s) && id[i] == -1) {
                cnt++;
            }
            id[uf0.find(s)] = 0;
            id[uf0.find(t)] = cnt - 1;
            cnt = 1;
            for (int i = 0; i < n; i++) if (uf0.pre[i] == i && uf.isSame(i, s) && id[i] == -1) {
                id[i] = cnt++;
            }
            for (int i = 0; i < n; i++) if (uf.isSame(i, s)) {
                id[i] = id[uf0.find(i)];
            }
            cnt++;
            double[][] R = new double[cnt][cnt];
            for (int i = 0; i < m; i++) {
                if (cs[i] != 0) {
                    if (id[us[i]] != -1 && id[vs[i]] != -1) {
                        R[id[us[i]]][id[vs[i]]]++;
                        R[id[vs[i]]][id[us[i]]]++;
                    }
                }
            }
            double[][] A = new double[cnt][cnt];
            double[] b = new double[cnt];
            b[0] = 1;
            for (int i = 0; i < cnt; i++) {
                if (i != 0 && i != cnt - 1) {
                    for (int j = 0; j < cnt; j++) if (R[i][j] > 0) {
                        A[i][j] += R[i][j];
                        A[i][i] -= R[i][j];
                    }
                } else {
                    A[i][i] = 1;
                }
            }
            double[][] x = Matrix.solutionSpace(A, b);
            double ans = 0;
            for (int i = 0; i < cnt; i++) if (R[0][i] > 0) {
                ans += (1 - x[0][i]) * R[0][i];
            }
            out.printf("%.6f%n", 1 / ans);
        }
    }
}
