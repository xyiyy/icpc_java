package com.shu_mj.ds;

import java.util.Random;

/**
 * Created by Jun on 9/27/2014.
 */
public class Hash2 {
    public final long BL;
    public final long BR;
    public final long ML;
    public final long MR;
    public final long[] psl;
    public final long[] psr;

    public Hash2(int n) {
        Random r = new Random(System.nanoTime());
        BL = (long) (1e9 + r.nextInt((int) 1e9));
        BR = (long) (1e9 + r.nextInt((int) 1e9));
        ML = (long) (1e9 + r.nextInt((int) 1e9));
        MR = (long) (1e9 + r.nextInt((int) 1e9));
        psl = new long[n + 1];
        psr = new long[n + 1];
        for (int i = 0; i <= n; i++) psl[i] = (i == 0 ? 1 : psl[i - 1] * BL) % ML;
        for (int i = 0; i <= n; i++) psr[i] = (i == 0 ? 1 : psr[i - 1] * BR) % MR;
    }

    public long[] build(char[] cs) {
        int n = cs.length;
        long[] hs = new long[n + 1];
        long l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            l = (l * BL + cs[i]) % ML;
            r = (r * BR + cs[i]) % MR;
            if (l < 0) l += ML;
            if (r < 0) r += MR;
            hs[i + 1] = (l << 32) | r;
        }
        return hs;
    }

    public long[] build(int[] is) {
        int n = is.length;
        long[] hs = new long[n + 1];
        long l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            l = (l * BL + is[i]) % ML;
            r = (r * BR + is[i]) % MR;
            if (l < 0) l += ML;
            if (r < 0) r += MR;
            hs[i + 1] = (l << 32) | r;
        }
        return hs;
    }
    public long getHash(char[] cs) {
        return getHash(cs, 0, cs.length);
    }
    public long getHash(char[] cs, int b, int e) {
        long l = 0, r = 0;
        for (int i = b; i < e; i++) {
            l = (l * BL + cs[i]) % ML;
            r = (r * BR + cs[i]) % MR;
        }
        if (l < 0) l += ML;
        if (r < 0) r += MR;
        return (l << 32) | r;
    }
    public long getHash(int[] is) {
        return getHash(is, 0, is.length);
    }
    public long getHash(int[] is, int b, int e) {
        long l = 0, r = 0;
        for (int i = b; i < e; i++) {
            l = (l * BL + is[i]) % ML;
            r = (r * BR + is[i]) % MR;
        }
        if (l < 0) l += ML;
        if (r < 0) r += MR;
        return (l << 32) | r;
    }
    public long get(long[] hs, int b, int e) {
        long el = hs[e] >>> 32;
        long er = hs[e] & 0xffffffffL;
        long bl = hs[b] >>> 32;
        long br = hs[b] & 0xffffffffL;
        long l = el - bl * psl[e - b] % ML;
        long r = er - br * psr[e - b] % MR;
        if (l < 0) l += ML;
        if (r < 0) r += MR;
        return (l << 32) | r;
    }
}
