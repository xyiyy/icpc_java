package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;
import sun.swing.BakedArrayList;

public class Task5046 {
    Scanner in;
    PrintWriter out;
    private int minAns;
    private List<BitSet> bs;
    private int n;
    private int[] last;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            solve();
        }
    }

    private void solve() {
        n = in.nextInt();
        int k = in.nextInt();
        int[] xs = new int[n];
        int[] ys = new int[n];
        for (int i = 0; i < n; i++) {
            xs[i] = in.nextInt();
            ys[i] = in.nextInt();
        }
        long[] ds = new long[n * (n - 1) / 2 + 1];
        for (int i = 0, c = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ds[c++] = dis(xs, ys, i, j);
            }
        }
        Arrays.sort(ds);
        ds = Algo.unique(ds);
        int l = 0, r = ds.length;
        while (l < r) {
            int m = (l + r) / 2;
            if (fit(n, k, xs, ys, ds[m])) r = m;
            else l = m + 1;
        }
        out.println(ds[l]);
        /*long l = 0, r = 10000000000L;
        while (l < r) {
            long m = (l + r) / 2;
            if (fit(n, k, xs, ys, m)) r = m;
            else l = m + 1;
        }
        out.println(l);*/
    }

    private boolean fit(int n, int k, int[] xs, int[] ys, long d) {
        boolean[][] maps = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maps[i][j] = dis(xs, ys, i, j) <= d;
            }
        }
        bs = new ArrayList<BitSet>();
        boolean[] eq = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Arrays.equals(maps[i], maps[j])) { eq[i] = true; break; }
            }
        }
        for (int i = 0; i < n; i++) if (!eq[i]) {
            if (!cover(maps, eq, i)) {
                BitSet b = new BitSet(n);
                for (int j = 0; j < n; j++) {
                    if (maps[i][j]) b.set(j);
                }
                bs.add(b);
            }
        }
        Collections.sort(bs, new Comparator<BitSet>() {
            @Override
            public int compare(BitSet o1, BitSet o2) {
                return count(o2) - count(o1);
            }
        });
        last = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < bs.size(); j++) {
                if (bs.get(j).get(i)) last[i] = j;
            }
        }
        minAns = k;
        return dfs(0, 0, new BitSet(n));
    }

    private int count(BitSet b) {
        int c = 0;
        for (int i = b.nextSetBit(0); i >= 0; i = b.nextSetBit(i + 1)) {
            c++;
        }
        return c;
    }

    private boolean dfs(int crt, int ans, BitSet state) {
        if (ans + hStar(crt, state) > minAns) return false;
        if (crt == bs.size()) return true;
        boolean use = false, notUse = true;
        for (int i = 0; i < n; i++) {
            if (!state.get(i) && last[i] == crt) use = true;
            if (!state.get(i) && bs.get(crt).get(i)) notUse = false;
        }
        if (!use && dfs(crt + 1, ans, (BitSet) state.clone())) return true;
        state.or(bs.get(crt));
        if (!notUse && dfs(crt + 1, ans + 1, state)) return true;
        return false;
    }

    private int hStar(int crt, BitSet state) {
        boolean[] used = new boolean[n - crt];
        int res = 0;
        for (int i = 0; i < n; i++) if (!state.get(i)) {
            boolean ok = true;
            for (int j = crt; j < bs.size(); j++) if (used[j - crt] && bs.get(j).get(i)) { ok = false; break; }
            if (ok) {
                res++;
                for (int j = crt; j < bs.size(); j++) if (bs.get(j).get(i)) used[j - crt] = true;
            }
        }
        return res;
    }

    class P implements Comparable<P> {
        int x;
        int y;

        P(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(P o) {
            return x - o.x;
        }
    }

    private boolean cover(boolean[][] maps, boolean[] eq, int i) {
        for (int j = 0; j < maps.length; j++) if (j != i && !eq[j]) {
            boolean ok = true;
            for (int k = 0; k < maps[i].length; k++) {
                if (maps[i][k] && !maps[j][k]) ok = false;
            }
            if (ok) return true;
        }
        return false;
    }

    private long dis(int[] xs, int[] ys, int i, int j) {
        long xi = xs[i];
        long yi = ys[i];
        long xj = xs[j];
        long yj = ys[j];
        return Math.abs(xi - xj) + Math.abs(yi - yj);
    }
}
