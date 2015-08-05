package com.shu_mj.ds;

/**
 * Created by Jun on 6/23/2014.
 */
public class LongMatSum {
    LongBIT[] bs;
    public LongMatSum(int row, int col) {
        bs = new LongBIT[row + 1];
        for (int i = 0; i < bs.length; i++) {
            bs[i] = new LongBIT(col);
        }
    }

    public void add(int x, int y, long val) {
        for (int i = x + 1; i < bs.length; i += i & -i) {
            bs[i].add(y, val);
        }
    }

    public long sum(int x0, int y0, int x1, int y1) {
        if (x0 != 0) return sum(0, y0, x1, y1) - sum(0, y0, x0, y1);
        long res = 0;
        for (int i = x1; i > 0; i -= i & -i) {
            res += bs[i].sum(y0, y1);
        }
        return res;
    }
}
