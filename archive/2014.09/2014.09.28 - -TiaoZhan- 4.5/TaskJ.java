package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;
import sun.swing.BakedArrayList;

public class TaskJ {
    Scanner in;
    PrintWriter out;
    private int w;
    private int h;
    private int minAns;
    private int[] last;
    private int c;
    private List<BitSet> bs;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int w = in.nextInt();
            int h = in.nextInt();
            if (w == 0 && h == 0) break;
            solve(w, h);
        }
    }

    private void solve(int w, int h) {
        this.w = w;
        this.h = h;
        int[][] maps = in.nextIntMatrix(h, w);
        int[][] area = new int[h][w];
        int[][] id = new int[h][w];
        c = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i == 0 || j == 0) {
                    area[i][j] = maps[i][j];
                } else {
                    if (maps[i][j] == 1) area[i][j] = Math.min(area[i - 1][j - 1], Math.min(area[i - 1][j], area[i][j - 1])) + 1;
                }
                if (maps[i][j] == 1) id[i][j] = c++;
            }
        }
        bs = new ArrayList<BitSet>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (maps[i][j] == 1) {
                    if (!cover(area, i, j)) {
                        BitSet b = new BitSet(c);
                        for (int u = 0; u < area[i][j]; u++) {
                            for (int v = 0; v < area[i][j]; v++) {
                                b.set(id[i - u][j - v]);
                            }
                        }
                        bs.add(b);
                    }
                }
            }
        }
        Collections.sort(bs, new Comparator<BitSet>() {
            @Override
            public int compare(BitSet o1, BitSet o2) {
                return count(o2) - count(o1);
            }
        });
        last = new int[c];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) if (maps[i][j] == 1) {
                for (int k = 0; k < bs.size(); k++) {
                    if (bs.get(k).get(id[i][j])) {
                        last[id[i][j]] = k;
                    }
                }
            }
        }
        /*for (int i = 0; ; i++) {
            minAns = i;
            if (dfs(0, 0, new BitSet(c))) {
                out.println(i);
                return ;
            }
        }*/
        minAns = 12341234;
        out.println(dfs(0, 0, new BitSet(c)));
    }

    private int dfs(int crt, int ans, BitSet state) {
        if (ans + hStar(crt, state) > minAns) return 12341234;
        if (crt == bs.size()) return minAns = ans;
        boolean use = false, notUse = true;
        for (int i = 0; i < c; i++) {
            if (!state.get(i) && last[i] == crt) use = true;
            if (!state.get(i) && bs.get(crt).get(i)) notUse = false;
        }
        int res = 12341234;
        if (!use) res = Math.min(res, dfs(crt + 1, ans, (BitSet) state.clone()));
        state.or(bs.get(crt));
        if (!notUse) res = Math.min(res, dfs(crt + 1, ans + 1, state));
        return res;
    }
/*
    private boolean dfs(int crt, int ans, BitSet state) {
        if (ans + hStar(crt, state) > minAns) return false;
        if (crt == bs.size()) return true;
        boolean use = false, notUse = true;
        for (int i = 0; i < c; i++) {
            if (!state.get(i) && last[i] == crt) use = true;
            if (!state.get(i) && bs.get(crt).get(i)) notUse = false;
        }
        if (!use && dfs(crt + 1, ans, (BitSet) state.clone())) return true;
        state.or(bs.get(crt));
        if (!notUse && dfs(crt + 1, ans + 1, state)) return true;
        return false;
    }
*/

    private int hStar(int crt, BitSet state) {
        int res = 0;
        List<P> ps = new ArrayList<P>();
        for (int i = 0; i < c; i++) {
            if (!state.get(i)) {
                int num = 0;
                for (int j = crt; j < bs.size(); j++) {
                    if (bs.get(j).get(i)) num++;
                }
                ps.add(new P(num, i));
            }
        }
        Collections.sort(ps);
        boolean[] used = new boolean[bs.size() - crt];
        for (P p : ps) {
            int id = p.y;
            boolean ok = true;
            for (int i = crt; i < bs.size(); i++) {
                if (used[i - crt] && bs.get(i).get(id)) ok = false;
            }
            if (ok) {
                res++;
                for (int i = crt; i < bs.size(); i++) {
                    if (bs.get(i).get(id)) used[i - crt] = true;
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

    private int count(BitSet b) {
        int c = 0;
        for (int i = 0; (i = b.nextSetBit(i)) != -1; i++) {
            c++;
        }
        return c;
    }

    private boolean cover(int[][] area, int x, int y) {
        for (int i = x; i < h; i++) {
            for (int j = y; j < w; j++) {
                if (i == x && j == y) continue;
                if (area[i][j] >= area[x][y] + Math.max(i - x, j - y)) return true;
            }
        }
        return false;
    }
}
