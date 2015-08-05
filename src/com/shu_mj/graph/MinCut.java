package com.shu_mj.graph;

import com.shu_mj.tpl.Algo;

import java.util.Arrays;

/**
 * Created by Jun on 8/22/2014.
 */
public class MinCut {
    public static final int INF = Integer.MAX_VALUE / 4;

    // O(n^3)
    public static int minCut(int[][] c) {
        int n = c.length, cut = INF;
        int[] id = new int[n], b = new int[n];
        for (int i = 0; i < n; i++) id[i] = i;
        for (; n > 1; n--) {
            Arrays.fill(b, 0);
            for (int i = 0; i + 1 < n; i++) {
                int p = i + 1;
                for (int j = i + 1; j < n; j++) {
                    b[id[j]] += c[id[i]][id[j]];
                    if (b[id[p]] < b[id[j]]) p = j;
                }
                Algo.swap(id, i + 1, p);
            }
            cut = Math.min(cut, b[id[n - 1]]);
            for (int i = 0; i < n - 2; i++) {
                c[id[i]][id[n - 2]] += c[id[i]][id[n - 1]];
                c[id[n - 2]][id[i]] += c[id[n - 1]][id[i]];
            }
        }
        return cut;
    }
}
