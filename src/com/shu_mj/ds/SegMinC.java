package com.shu_mj.ds;

import java.util.Arrays;

/**
 * Created by Jun on 6/23/2014.
 */
public class SegMinC {
    public int[] is;
    public int N;

    public SegMinC(int n) {
        N = Integer.highestOneBit(n) << 1;
        is = new int[N * 2];
        Arrays.fill(is, Integer.MAX_VALUE);
    }

    public int update(int k, int m, int a) {
        k += N;
        int val = is[k] = is[k] * m + a;
        for (k >>= 1; k > 0; k >>= 1) {
            is[k] = Math.min(is[k * 2], is[k * 2 + 1]);
        }
        return val;
    }

    public int query(int s, int t) {
        int res = Integer.MAX_VALUE;
        while (0 < s && s + (s & -s) <= t) {
            int i = (N + s) / (s & -s);
            res = Math.min(res, is[i]);
            s += s & -s;
        }
        while (s < t) {
            int i = (N + t) / (t & -t) - 1;
            res = Math.min(res, is[i]);
            t -= t & -t;
        }
        return res;
    }
}
