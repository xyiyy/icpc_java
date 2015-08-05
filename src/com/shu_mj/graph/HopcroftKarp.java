package com.shu_mj.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Jun on 6/27/2014.
 */
public class HopcroftKarp {
    public static int hopcroftKarp(V[] vs) {
        for (int match = 0; ; ) {
            Queue<V> que = new LinkedList<V>();
            for (V v : vs) v.level = -1;
            for (V v : vs)
                if (v.pair == null) {
                    v.level = 0;
                    que.offer(v);
                }
            while (!que.isEmpty()) {
                V v = que.poll();
                for (V u : v.vs) {
                    V w = u.pair;
                    if (w != null && w.level < 0) {
                        w.level = v.level + 1;
                        que.offer(w);
                    }
                }
            }
            for (V v : vs) v.used = false;
            int d = 0;
            for (V v : vs) if (v.pair == null && dfs(v)) d++;
            if (d == 0) return match;
            match += d;
        }
    }

    public static boolean dfs(V v) {
        v.used = true;
        for (V u : v.vs) {
            V w = u.pair;
            if (w == null || !w.used && v.level < w.level && dfs(w)) {
                v.pair = u;
                u.pair = v;
                return true;
            }
        }
        return false;
    }

    public static class V {
        public List<V> vs = new ArrayList<V>();
        public V pair;
        public boolean used;
        public int level;
        public void connect(V v) {
            vs.add(v);
            v.vs.add(this);
        }
    }
}
