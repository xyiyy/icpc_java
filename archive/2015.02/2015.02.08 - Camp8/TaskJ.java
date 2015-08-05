package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;
import sun.awt.geom.AreaOp;

public class TaskJ {
    Scanner in;
    PrintWriter out;
    private int N;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] L = new int[n];
        int[] R = new int[n];
        int[] W = new int[n];
        in.nextIntArray(n, L, R, W);
        compress(n, L, R);
        for (int i = 0; i < n; i++) {
            L[i] *= 2;
            R[i] *= 2;
        }
        N = Math.max(Algo.max(L), Algo.max(R));
        V[] vs = new V[N + 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new V();
            if (i > 0) vs[i].add(vs[i - 1], 0);
        }
        int[] fail = new int[N + 2];
        for (int i = 0; i < n; i++) {
            vs[R[i] + 1].add(vs[L[i]], -W[i]);
            vs[L[i]].add(vs[R[i] + 1], W[i]);
            fail[L[i]]++;
            fail[R[i] + 1]--;
        }
        boolean[] vis = new boolean[N + 2];
        for (int i = 0, crt = 0; i < N + 2; i++) {
            crt += fail[i];
            if (crt > 0) vis[i] = true;
        }
        for (int i = 0; i < N + 1; i++) {
            if (!vis[i]) {
                vs[i].add(vs[i + 1], 0);
            }
        }
        long min, max;
        if (spfa(vs[0])) max = vs[N + 1].min;
        else max = -1;
        vs = new V[N + 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new V();
            if (i > 0) vs[i].add(vs[i - 1], 0);
        }
//        Algo.debug(L);
//        Algo.debug(R);
        for (int i = 0; i < n; i++) {
            vs[R[i] + 1].add(vs[L[i]], -W[i]);
            vs[L[i]].add(vs[R[i] + 1], W[i]);
//            Algo.debug(R[i] + 1, L[i], -W[i]);
//            Algo.debug(L[i], R[i] + 1, W[i]);
        }
        if (spfa(vs[N + 1])) min = -vs[0].min;
        else min = -1;
        if (min >= INF || min < 0) min = -1;
        if (max >= INF || max < 0) max = -1;
        if (max < min) max = -1;
        if (min == -1) max = -1;
        if (max == -1) min = -1;
        out.println(min + " " + max);
    }

    public boolean spfa(V s) {
        LinkedList<V> que = new LinkedList<V>();
        s.min = 0;
        que.offer(s);
        while (!que.isEmpty()) {
            V crt = que.pollFirst();
            crt.inQue = false;
            for (E e : crt.es) {
                if (crt.min + e.cost < e.to.min) {
                    e.to.min = crt.min + e.cost;
                    if (!e.to.inQue) {
                        e.to.inQue = true;
                        e.to.times++;
                        if (e.to.times > N) {
                            return false;
                        }
                        if (!que.isEmpty() && e.to.min < que.peekFirst().min) que.addFirst(e.to);
                        else que.offerLast(e.to);
                    }
                }
            }
        }
        return true;
    }

    public static final long INF = (long) (1e13);

    public static class V {
        public ArrayList<E> es = new ArrayList<E>();
        public long min = INF;
        public boolean inQue = false;
        public int times = 0;

        public void add(V to, long cost) {
            es.add(new E(to, cost));
        }
    }

    public static class E {
        public V to;
        public long cost;

        public E(V to, long cost) {
            this.to = to;
            this.cost = cost;
        }

    }
    private void compress(int n, int[] l, int[] r) {
        int[] x = new int[n * 2];
        for (int i = 0; i < n * 2; i++) {
            x[i] = i < n ? l[i] : r[i - n];
        }
        Arrays.sort(x);
        x = Algo.unique(x);
        for (int i = 0; i < n; i++) {
            l[i] = Algo.lowerBound(x, l[i]);
            r[i] = Algo.lowerBound(x, r[i]);
        }
    }
}
