package com.shu_mj.ds;

import java.util.Arrays;

/**
 * Created by Jun on 6/7/2014.
 */
public class LongSegMin {
    public int N;
    public long[] ls, mul, add;

    public LongSegMin(int n) {
        N = Integer.highestOneBit(n) << 1;
        ls = new long[N * 2];
        Arrays.fill(ls, Long.MAX_VALUE);
        mul = new long[N * 2];
        add = new long[N * 2];
        Arrays.fill(mul, 1);
    }

    int s, t;
    long m, a;

    public void update(int s, int t, long m, long a) {
        this.s = s;
        this.t = t;
        this.m = m;
        this.a = a;
        update(1, 0, N, 1, 0);
    }

    public void update(int o, int L, int R, long m, long a) {
        if (s <= L && R <= t) {
            // push this.m, this.a to m, a
            m = this.m * m;
            a = this.m * a + this.a;
        }
        // push m, a to L[o], R[o]
        mul[o] = m * mul[o];
        add[o] = m * add[o] + a;
        if (t <= L || R <= s || s <= L && R <= t) {
            // maintain is[o] for m, a
            ls[o] = m * ls[o] + a; // need change
        } else {
            int M = (L + R) / 2;
            update(o * 2, L, M, mul[o], add[o]);
            update(o * 2 + 1, M, R, mul[o], add[o]);
            // init L[o], R[o]
            mul[o] = 1;
            add[o] = 0;
            ls[o] = Math.min(ls[o * 2], ls[o * 2 + 1]);
        }
    }

    public long query(int s, int t) {
        update(s, t, 1, 0);
        long res = Long.MAX_VALUE; // need change
        while (0 < s && s + (s & -s) <= t) {
            int i = (N + s) / (s & -s);
            res = Math.min(res, ls[i]);
            s += s & -s;
        }
        while (s < t) {
            int i = (N + t) / (t & -t) - 1;
            res = Math.min(res, ls[i]);
            t -= t & -t;
        }
        return res;
    }

}
