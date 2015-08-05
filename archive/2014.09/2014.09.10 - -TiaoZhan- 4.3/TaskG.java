package main;

import com.shu_mj.graph.SCC;
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
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            if (n == 0 && m == 0) break;
            solve(n, m);
        }
    }

    void solve(int n, int m) {
        int[][] mat = in.nextIntMatrix(n, 2);
        int[][] doors = in.nextIntMatrix(m, 2);
        int[] rev = new int[n * 2];
        for (int[] is : mat) {
            rev[is[0]] = is[1];
            rev[is[1]] = is[0];
        }
        int ll = 1, rr = m + 1;
        while (ll < rr) {
            int mid = (ll + rr) / 2;
            if (fit(n, rev, mat, doors, mid)) ll = mid + 1;
            else rr = mid;
        }
        int l = -1, r = m;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (fit(n, rev, mat, doors, mid)) l = mid;
            else r = mid - 1;
        }
        while (ll - 1 != r) ;
        out.println(r);
    }

    private boolean fit3(int n, int[] rev, int[][] mat, int[][] doors, int mid) {
        SCC.V[] vs = new SCC.V[4 * n];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new SCC.V();
        }
        for (int[] is : mat) {
            vs[is[0] + n * 2].add(vs[is[1]]);
            vs[is[1] + n * 2].add(vs[is[0]]);
            vs[is[0]].add(vs[is[1] + n * 2]);
            vs[is[1]].add(vs[is[0] + n * 2]);
        }
        for (int i = 0; i < mid; i++) {
            int[] is = doors[i];
            vs[is[0] + n * 2].add(vs[is[1]]);
            vs[is[1] + n * 2].add(vs[is[0]]);
        }
        SCC.scc(vs);
        for (int i = 0; i < n * 2; i++) {
            if (vs[i].comp == vs[i + n * 2].comp) return false;
        }
        return true;
    }

    private boolean fit(int n, int[] rev, int[][] mat, int[][] doors, int m) {
        SCC.V[] vs = new SCC.V[n * 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new SCC.V();
        }
        for (int i = 0; i < m && i < doors.length; i++) {
            int u = doors[i][0];
            int v = doors[i][1];
            vs[rev[u]].add(vs[v]);
            vs[rev[v]].add(vs[u]);
        }
        SCC.scc(vs);
        for (int[] is : mat) {
            if (vs[is[0]].comp == vs[is[1]].comp) return false;
        }
        return true;
    }
}
