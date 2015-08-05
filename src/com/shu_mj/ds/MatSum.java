package com.shu_mj.ds;

/**
 * Created by Jun on 6/23/2014.
 */
public class MatSum {
    BIT[] bs;
    public MatSum(int row, int col) {
        bs = new BIT[row + 1];
        for (int i = 0; i < bs.length; i++) {
            bs[i] = new BIT(col);
        }
    }

    public void add(int x, int y, int val) {
        for (int i = x + 1; i < bs.length; i += i & -i) {
            bs[i].add(y, val);
        }
    }

    public int sum(int x0, int y0, int x1, int y1) {
        if (x0 != 0) return sum(0, y0, x1, y1) - sum(0, y0, x0, y1);
        int res = 0;
        for (int i = x1; i > 0; i -= i & -i) {
            res += bs[i].sum(y0, y1);
        }
        return res;
    }
}
