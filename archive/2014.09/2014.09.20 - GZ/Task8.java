package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task8 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            if (n == 0 && m == 0) break;
            solve(n, m);
        }
    }

    private void solve(int n, int m) {
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) vs[i] = new V();
        for (int i = 1; i < n; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vs[u].add(vs[v]);
            vs[v].add(vs[u]);
        }
        z = 0;
        dfs(vs[0]);
        build(vs[0], vs[0]);
        P[] ps = new P[m];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int c = in.nextInt();
            ps[i] = new P(u, v, c);
        }
        Arrays.sort(ps, new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                return o1.z - o2.z;
            }
        });
        seg = new Seg(n);
        for (P p : ps) {
            update(vs[p.x], vs[p.y], p.z);
        }
        for (int i = 1; i < seg.N; i++) {
            seg.pushDown(i);
        }
        for (int i = 1; i < seg.N; i++) {
            if (seg.col0[i] != 0) {
                seg.push2(i * 2, seg.col0[i], seg.cnt0[i]);
                seg.push2(i * 2 + 1, seg.col0[i], seg.cnt0[i]);
            }
        }
        seg.clear();
        for (int i = 0; i < n; i++) {
            out.println(seg.col0[vs[i].w + seg.N]);
        }
    }
    class P {
        int x;
        int y;
        int z;

        P(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

    }

    class V extends ArrayList<V> {
        int siz;
        int dep;
        V top;
        V fa;
        V son;
        int w;
    }

    void dfs(V v) {
        v.siz = 1;
        for (V u : v) if (u != v.fa) {
            u.fa = v;
            u.dep = v.dep + 1;
            dfs(u);
            if (v.son == null || u.siz > v.son.siz) v.son = u;
            v.siz += u.siz;
        }
    }
    int z;
    void build(V v, V top) {
        v.w = z++;
        v.top = top;
        if (v.son != null) build(v.son, v.top);
        for (V u : v) if (u != v.fa && u != v.son) {
            build(u, u);
        }
    }

    void update(V a, V b, int c) {
        V ta = a.top;
        V tb = b.top;
        while (ta != tb) {
            if (ta.dep < tb.dep) {
                { V v = ta; ta = tb; tb = v; }
                { V v = a; a = b; b = v; }
            }
            seg.update(ta.w, a.w + 1, c);
            a = a.fa;
            ta = a.top;
        }
        if (a.dep > b.dep) { V v = a; a = b; b = v; }
        seg.update(a.w, b.w + 1, c);
    }
    Seg seg;
    class Seg {
        int N;
        int[] col0;
        int[] cnt0;
        int[] col1;
        int[] cnt1;
        Seg(int n) {
            N = Integer.highestOneBit(n) << 1;
            col0 = new int[N * 2];
            cnt0 = new int[N * 2];
            col1 = new int[N * 2];
            cnt1 = new int[N * 2];
        }
        void update(int s, int t, int c) {
            update(1, 0, N, s, t, c);
        }

        private void update(int o, int L, int R, int s, int t, int c) {
            if (s <= L && R <= t) {
                push(o, c, 1);
            } else {
                pushDown(o);
                int M = (L + R) / 2;
                if (s < M) update(o * 2, L, M, s, t, c);
                if (t > M) update(o * 2 + 1, M, R, s, t, c);
            }
        }

        void push(int o, int col, int cnt) {
            if (col == col1[o]) {
                cnt1[o] += cnt;
            } else {
                if (o >= N) {
                    if (cnt0[o] < cnt1[o] || cnt0[o] == cnt1[o] && col0[o] > col1[o]) {
                        cnt0[o] = cnt1[o];
                        col0[o] = col1[o];
                    }
                } else {
                    if (col1[o * 2] != col1[o] && col1[o * 2 + 1] != col1[o]) {
                        if (cnt0[o] < cnt1[o] || cnt0[o] == cnt1[o] && col0[o] > col1[o]) {
                            cnt0[o] = cnt1[o];
                            col0[o] = col1[o];
                        }
                    } else {
                        pushDown(o);
                    }
                }
                col1[o] = col;
                cnt1[o] = cnt;
            }
        }

        private void pushDown(int o) {
            if (o >= N) return ;
            if (cnt1[o] != 0) {
                push(o * 2, col1[o], cnt1[o]);
                push(o * 2 + 1, col1[o], cnt1[o]);
                cnt1[o] = 0;
                col1[o] = 0;
            }
        }

        public void clear() {
            for (int i = N; i < N * 2; i++) {
                if (cnt0[i] < cnt1[i] || cnt0[i] == cnt1[i] && col0[i] > col1[i]) {
                    col0[i] = col1[i];
                    cnt0[i] = cnt1[i];
                }
            }
        }

        public void push2(int i, int col, int cnt) {
            if (cnt0[i] < cnt || cnt0[i] == cnt && col0[i] > col) {
                col0[i] = col;
                cnt0[i] = cnt;
            }
        }
    }
}
