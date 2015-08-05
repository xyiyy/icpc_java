package main;
import java.util.*;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Algo;

public class CandyCupRunningCompetition {
    public int findMaximum(int N, int[] A, int[] B) {
        V[] vs = new V[N];
        for (int i = 0; i < vs.length; i++) vs[i] = new V();
        for (int i = 0; i < A.length; i++) {
            vs[A[i]].add(vs[B[i]], i + 1);
            vs[B[i]].add(vs[A[i]], i + 1);
        }
        return dinic(vs[0], vs[N - 1]);
    }

    public static final int INF = Integer.MAX_VALUE / 4;
    public static int p = 0;

    public static int dinic(V s, V t) {
        int flow = 0;
        for (p++; ; p++) {
            Queue<V> que = new LinkedList<V>();
            s.level = 0;
            s.p = p;
            que.offer(s);
            while (!que.isEmpty()) {
                V v = que.poll();
                v.iter = v.es.size() - 1;
                for (E e : v.es)
                    if (e.to.p < p && e.cap > 0) {
                        e.to.level = v.level + 1;
                        e.to.p = p;
                        que.offer(e.to);
                    }
            }
            if (t.p < p) return flow;
            for (int f; (f = dfs(s, t, INF)) > 0; ) {
                flow += Num.pow(3, f - 1, (long) (1e9 + 7));
                flow %= (long) (1e9 + 7);
            }
        }
    }

    public static int dfs(V v, V t, int f) {
        if (v == t) return f;
        for (; v.iter >= 0; v.iter--) {
            E e = v.es.get(v.iter);
            if (v.level < e.to.level && e.cap > 0) {
                int d = dfs(e.to, t, Math.min(f, e.cap));
                if (d > 0) {
                    if (d == e.cap) e.cap -= d;
                    e.rev.cap = Math.max(e.rev.cap, d);
                    return d;
                }
            }
        }
        return 0;
    }

    public static class V {
        public ArrayList<E> es = new ArrayList<E>();
        public int level;
        public int p;
        public int iter;
        public void add(V to, int cap) {
            E e = new E(to, cap), rev = new E(this, 0);
            e.rev = rev;
            rev.rev = e;
            es.add(e);
            to.es.add(rev);
        }
    }

    public static class E {
        public V to;
        public E rev;
        public int cap;

        public E(V to, int cap) {
            this.to = to;
            this.cap = cap;
        }
    }

}
