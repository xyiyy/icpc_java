package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task2449 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) vs[i] = new V();
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int c = in.nextInt();
            vs[u].add(vs[v], c);
        }
        int s = in.nextInt() - 1;
        int t = in.nextInt() - 1;
        int k = in.nextInt();
        dijkstra(vs[t]);
        PriorityQueue<E> que = new PriorityQueue<E>();
        que.add(new E(vs[s], vs[s].min));
        if (s == t) k++;
        while (!que.isEmpty()) {
            E ce = que.poll();
            V crt = ce.to;
            int cost = ce.cost - crt.min;
            crt.times++;
            if (crt == vs[t] && crt.times == k) {
                out.println(cost);
                return ;
            }
            if (crt.times > k) continue;
            for (E e : crt.es) {
                que.add(new E(e.to, cost + e.cost + e.to.min));
            }
        }
        out.println(-1);
    }
    public static void dijkstra(V s) {
        PriorityQueue<E> que = new PriorityQueue<E>();
        s.min = 0;
        que.offer(new E(s, 0));
        while (!que.isEmpty()) {
            E crt = que.poll();
            if (crt.cost > crt.to.min) continue;
            for (E e : crt.to.rs) {
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
        public ArrayList<E> rs = new ArrayList<E>();
        public int min = INF;
        public int times = 0;
        public void add(V to, int cost) {
            es.add(new E(to, cost));
            to.rs.add(new E(this, cost));
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
