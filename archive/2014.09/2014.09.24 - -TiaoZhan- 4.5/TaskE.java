package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskE {
    Scanner in;
    PrintWriter out;
    private boolean[][][] maps;
    private int S;
    private int M;
    private BitSet[] m;
    private int[] mMax;
    private int minAns;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        maps = new boolean[6][][];
        for (int n = 1; n <= 5; n++) {
            int all = 0;
            for (int i = 1; i <= n; i++) all += i * i;
            maps[n] = new boolean[2 * n * (n + 1)][all];
            all = 0;
            for (int m = 1; m <= n; m++) {
                for (int i = 0; i < n - m + 1; i++) {
                    int bg = (n + n + 1) * i;
                    for (int j = 0; j < n - m + 1; j++) {
                        int top = bg + j;
                        int left = top + n;
                        int right = left + m;
                        int bottom = top + m * (n + n + 1);
                        for (int k = 0; k < m; k++) {
                            maps[n][top + k][all] = true;
                            maps[n][left + k * (n + n + 1)][all] = true;
                            maps[n][right + k * (n + n + 1)][all] = true;
                            maps[n][bottom + k][all] = true;
                        }
                        all++;
                    }
                }
            }
        }
        int t = in.nextInt();
        while (t-- != 0) solve();
    }

    private void solve() {
        int n = in.nextInt();
        int k = in.nextInt();
        int all = 2 * n * (n + 1);
        boolean[] removed = new boolean[all];
        for (int i = 0; i < k; i++) {
            removed[in.nextInt() - 1] = true;
        }
        boolean[] consider = new boolean[all];
        int[] sid = new int[maps[n][0].length];
        Arrays.fill(sid, -1);
        S = 0;
        for (int i = 0; i < sid.length; i++) {
            boolean full = true;
            for (int j = 0; j < all; j++) {
                if (maps[n][j][i] && removed[j]) full = false;
            }
            if (full) {
                sid[i] = S++;
                for (int j = 0; j < all; j++) {
                    if (maps[n][j][i]) consider[j] = true;
                }
            }
        }
        int[] mid = new int[all];
        M = 0;
        for (int i = 0; i < all; i++) if (consider[i]) mid[i] = M++;
        m = new BitSet[M];
        mMax = new int[S];
        for (int i = 0; i < all; i++) if (consider[i]) {
            m[mid[i]] = new BitSet();
            for (int j = 0; j < sid.length; j++) if (sid[j] != -1) {
                if (maps[n][i][j]) {
                    m[mid[i]].set(sid[j]);
                    mMax[sid[j]] = mid[i];
                }
            }
        }
        BitSet state = new BitSet(S);
        state.set(0, S);
        minAns = 12341234;
        out.println(dfs(0, 0, state));
    }

    int dfs(int p, int num, BitSet state) {
        if (p == M) return minAns = num;
        if (num + hStar(p, state) >= minAns) return 12341234;
        boolean use = false, notUse = true;
        for (int i = 0; i < S; i++) {
            if (state.get(i) && m[p].get(i)) notUse = false;
            if (state.get(i) && mMax[i] == p) use = true;
        }
        int res = 12341234;
        if (!use) res = Math.min(res, dfs(p + 1, num, (BitSet) state.clone()));
        state.andNot(m[p]);
        if (!notUse) res = Math.min(res, dfs(p + 1, num + 1, state));
        return res;
    }

    int hStar(int p, BitSet state) {
        List<P> ps = new ArrayList<P>();
        for (int i = 0; i < S; i++) {
            if (state.get(i)) {
                int num = 0;
                for (int j = p; j < M; j++) {
                    if (m[j].get(i)) num++;
                }
                ps.add(new P(num, i));
            }
        }
        Collections.sort(ps);
        int res = 0;
        BitSet used = new BitSet();
        for (P q : ps) {
            int id = q.y;
            boolean ok = true;
            for (int j = p; j < M; j++) {
                if (m[j].get(id) && used.get(j)) ok = false;
            }
            if (ok) {
                res++;
                for (int j = p; j < M; j++) {
                    if (m[j].get(id)) used.set(j);
                }
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
}
