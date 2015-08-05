package com.shu_mj.ds;

import java.util.Stack;

/**
 * Created by Jun on 9/17/2014.
 */
public class MaxRect {
    public static long maxRect(int[] _hs) {
        int n = _hs.length;
        long res = 0;
        int[] hs = new int[n + 1];//n 番に番兵0 を置く
        System.arraycopy(_hs, 0, hs, 0, n);
        Stack<Integer> sx = new Stack<Integer>();
        Stack<Integer> sh = new Stack<Integer>();
        sh.push(0);
        for (int i = 0; i <= n; i++) {
            int x = i;
            while (sh.peek() > hs[i]) {
                res = Math.max(res, 1L * sh.pop() * (i - (x = sx.pop())));
            }
            sx.push(x);
            sh.push(hs[i]);
        }
        return res;
    }
}
