package com.shu_mj.ds;

import java.util.Arrays;

/**
 * Created by Jun on 7/14/2014.
 */
public abstract class Seg {
    public int N;
    public long[] is;
    public long[] ds;
    public static final long I = 1;
    public static final long D = 1;

    public Seg(int n) {
        N = Integer.highestOneBit(n) << 1;
        is = new long[N * 2];
        ds = new long[N * 2];
        Arrays.fill(ds, D);
        for (int i = N; i < N * 2; i++) {
            is[i] = I;
        }
        for (int i = N - 1; i > 0; i--) {
            pushUp(i);
        }
    }

    public abstract long mergeInfo(long a, long b);

    public void pushUp(int o) {
        is[o] = mergeInfo(is[o * 2], is[o * 2 + 1]);
    }

    public abstract void push(int o, int l, int r, long d);

    public long query(int s, int t) {
        return query(1, 0, N, s, t);
    }

    long query(int o, int l, int r, int s, int t) {
        if (s <= l && r <= t) {
            // 如果 [l, r) 和 [s, t) 不同，需要修改
            return is[o];
        } else {
            pushDown(o, l, r);
            int m = (l + r) / 2;
            if (t <= m) return query(o * 2, l, m, s, t);
            if (s >= m) return query(o * 2 + 1, m, r, s, t);
            return mergeInfo(query(o * 2, l, m, s, m), query(o * 2 + 1, m, r, m, t));
        }
    }

    public void update(int s, int t, long d) {
        update(1, 0, N, s, t, d);
    }

    void update(int o, int l, int r, int s, int t, long d) {
        if (s <= l && r <= t) {
            // 如果 [l, r) 和 [s, t) 不同，需要修改
            push(o, l, r, d);
        } else {
            pushDown(o, l, r);
            int m = (l + r) / 2;
            if (s < m) update(o * 2, l, m, s, Math.min(m, t), d);
            if (t > m) update(o * 2 + 1, m, r, Math.max(s, m), t, d);
            pushUp(o);
        }
    }

    public void pushDown(int o, int l, int r) {
        if (ds[o] != D) {
            int m = (l + r) / 2;
            push(o * 2, l, m, ds[o]);
            push(o * 2 + 1, m, r, ds[o]);
            ds[o] = D;
        }
    }

}
