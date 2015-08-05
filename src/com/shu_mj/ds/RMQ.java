package com.shu_mj.ds;

import com.shu_mj.tpl.Algo;

/**
 * Created by Jun on 6/6/2014.
 */
public class RMQ {
    public int[] vs;
    int[][] min;

    public RMQ(int[] vs) {
        int n = vs.length, m = Algo.log2(n) + 1;
        this.vs = vs;
        min = new int[m][n];
        for (int i = 0; i < n; i++)
            min[0][i] = i;
        for (int i = 1, k = 1; i < m; i++, k <<= 1) {
            for (int j = 0; j + k < n; j++) {
                min[i][j] = vs[min[i - 1][j]] <= vs[min[i - 1][j + k]] ? min[i - 1][j]
                        : min[i - 1][j + k];
            }
        }
    }

    // return index
    public int query(int from, int to) {
        int k = Algo.log2(to - from);
        return vs[min[k][from]] <= vs[min[k][to - (1 << k)]] ? min[k][from]
                : min[k][to - (1 << k)];
    }

    public int queryValue(int from, int to) {
        return vs[query(from, to)];
    }

}
