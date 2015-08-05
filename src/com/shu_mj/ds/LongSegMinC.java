package com.shu_mj.ds;

import java.util.Arrays;

/**
 * Created by Jun on 6/23/2014.
 */
public class LongSegMinC {
    public long[] is;
    public int N;

    public LongSegMinC(int n) {
        N = Integer.highestOneBit(n) << 1;
        is = new long[N * 2];
        Arrays.fill(is, Long.MAX_VALUE);
    }

    public long update(int k, long m, long a) {
        k += N;
        long val = is[k] = is[k] * m + a;
        for (k >>= 1; k > 0; k >>= 1) {
            is[k] = Math.min(is[k * 2], is[k * 2 + 1]);
        }
        return val;
    }

    public long query(int s, int t) {
        long res = Long.MAX_VALUE;
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
