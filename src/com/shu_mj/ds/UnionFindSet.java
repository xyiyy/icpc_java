package com.shu_mj.ds;

/**
 * Created by Jun on 9/3/2014.
 */
public class UnionFindSet {
    public int[] pre;

    public UnionFindSet(int n) {
        pre = new int[n];
        for (int i = 0; i < n; i++) pre[i] = i;
    }

    public int find(int x) {
        if (pre[x] != x) pre[x] = find(pre[x]);
        return pre[x];
    }

    public void union(int x, int y) {
        pre[find(x)] = find(y);
    }

    public boolean isSame(int x, int y) {
        return find(x) == find(y);
    }

    public boolean isRoot(int x) {
        return pre[x] == x;
    }
}
