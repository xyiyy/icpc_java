package com.shu_mj.ds;

/**
 * Created by Jun on 6/7/2014.
 */
public class LongSegSumC {
    LongBIT dif, pre;

    public LongSegSumC(int n) {
        dif = new LongBIT(n);
        pre = new LongBIT(n);
    }

    public void add(int s, int t, long v) {
        dif.add(s, v);
        dif.add(t, -v);
        pre.add(s, v * s);
        pre.add(t, -v * t);
    }

    public long sum(int s, int t) {
        if (s > 0) return sum(0, t) - sum(0, s);
        return dif.sum(0, t) * t - pre.sum(0, t);
    }
}
