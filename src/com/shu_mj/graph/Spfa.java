package com.shu_mj.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Jun on 6/7/2014.
 */

public class Spfa {
    public static void spfa(V s) {
        Queue<V> que = new LinkedList<V>();
        s.min = 0;
        que.offer(s);
        while (!que.isEmpty()) {
            V crt = que.poll();
            crt.inQue = false;
            for (E e : crt.es) {
                if (crt.min + e.cost < e.to.min) {
                    e.to.min = crt.min + e.cost;
                    if (!e.to.inQue) {
                        e.to.inQue = true;
                        que.offer(e.to);
                    }
                }
            }
        }
    }

    public static final int INF = 1 << 29;

    public static class V {
        public ArrayList<E> es = new ArrayList<E>();
        public int min = INF;
        public boolean inQue = false;

        public void add(V to, int cost) {
            es.add(new E(to, cost));
        }
    }

    public static class E {
        public V to;
        public int cost;

        public E(V to, int cost) {
            this.to = to;
            this.cost = cost;
        }

    }
}