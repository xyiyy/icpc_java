package com.shu_mj.ds;

/**
 * Created by Jun on 6/23/2014.
 */
public class MatMax {
    public SegMaxC[] ss;
    public int N;
    public int M;
    public MatMax(int row, int col) {
        N = Integer.highestOneBit(row) << 1;
        M = Integer.highestOneBit(col) << 1;
        ss = new SegMaxC[N * 2];
        for (int i = 0; i < N * 2; i++) {
            ss[i] = new SegMaxC(col);
        }
    }

    public int update(int x, int y, int m, int a) {
        x += N;
        int val = ss[x].update(y, m, a);
        for (x >>= 1; x > 0; x >>= 1) {
            ss[x].update(y, 0, Math.max(ss[x * 2].is[M + y], ss[x * 2 + 1].is[M + y]));
        }
        return val;
    }

    public int query(int x0, int y0, int x1, int y1) {
        int res = Integer.MIN_VALUE;
        while (0 < x0 && x0 + (x0 & -x0) <= x1) {
            int i = (N + x0) / (x0 & -x0);
            res = Math.max(res, ss[i].query(y0, y1));
            x0 += x0 & -x0;
        }
        while (x0 < x1) {
            int i = (N + x1) / (x1 & -x1) - 1;
            res = Math.max(res, ss[i].query(y0, y1));
            x1 -= x1 & -x1;
        }
        return res;
    }
}
