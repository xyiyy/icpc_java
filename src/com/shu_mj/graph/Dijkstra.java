package com.shu_mj.graph;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Jun on 6/7/2014.
 */

public class Dijkstra {
    public static void dijkstra(V s) {
        PriorityQueue<E> que = new PriorityQueue<E>();
        s.min = 0;
        que.offer(new E(s, 0));
        while (!que.isEmpty()) {
            E crt = que.poll();
            if (crt.cost > crt.to.min) continue;
            for (E e : crt.to.es) {
                if (crt.cost + e.cost < e.to.min) {
                    e.to.min = crt.cost + e.cost;
                    que.offer(new E(e.to, e.to.min));
                }
            }
        }
    }

    public static final int INF = 1 << 29;

    public static class V {
        public ArrayList<E> es = new ArrayList<E>();
        public int min = INF;

        public void add(V to, int cost) {
            es.add(new E(to, cost));
        }
    }

    public static class E implements Comparable<E> {
        public V to;
        public int cost;

        public E(V to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(E o) {
            return cost - o.cost;
        }
    }
}