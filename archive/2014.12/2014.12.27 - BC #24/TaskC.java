package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        while (in.hasNext()) run();
    }

    final long M = 2333333L * 61020;

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        Seg seg = new Seg(n);
        for (int i = 0; i < m; i++) {
            int type = in.nextInt();
            if (type == 1) {
                int l = in.nextInt() - 1;
                int r = in.nextInt();
                out.println(seg.query(l, r) % 2333333);
            } else if (type == 2) {
                int x = in.nextInt() - 1;
//                is[x] = 1 << is[x];
                seg.update1(x);
            } else {
                int l = in.nextInt() - 1;
                int r = in.nextInt();
                int x = in.nextInt();
                seg.update2(l, r, x);
            }
        }
    }

    class Seg {
        int N;
        long[] is;
        long[] ls;
        int[] size;
        Seg(int n) {
            N = Integer.highestOneBit(n) << 1;
            is = new long[N * 2];
            ls = new long[N * 2];
            for (int i = 0; i < n; i++) {
                is[i + N] = in.nextInt();
            }
            for (int i = N - 1; i > 0; i--) {
                is[i] = (is[i * 2] + is[i * 2 + 1]) % M;
            }
            size = new int[N * 2];
            for (int i = 0; i < N; i++) {
                size[i + N] = 1;
            }
            for (int i = N - 1; i > 0; i--) {
                size[i] = size[i * 2] * 2;
            }
        }

        void update2(int l, int r, long v) {
            update2(1, 0, N, l, r, v);
        }

        private void update2(int o, int L, int R, int l, int r, long v) {
            if (l <= L && R <= r) {
                push(o, v);
            } else {
                pushDown(o);
                int m = (L + R) / 2;
                if (l < m) update2(o * 2, L, m, l, r, v);
                if (r > m) update2(o * 2 + 1, m, R, l, r, v);
                is[o] = (is[o * 2] + is[o * 2 + 1]) % M;
            }
        }

        long query(int l, int r) {
            return query(1, 0, N, l, r);
        }

        private long query(int o, int L, int R, int l, int r) {
            if (l <= L && R <= r) {
                return is[o];
            } else {
                int m = (L + R) / 2;
                long res = 0;
                if (l < m) res += query(o * 2, L, m, l, r);
                if (r > m) res += query(o * 2 + 1, m, R, l, r);
                res %= M;
                return res;
            }
        }

        void pushDown(int o) {
            if (ls[o] != 0) {
                push(o * 2, ls[o]);
                push(o * 2 + 1, ls[o]);
                ls[o] = 1;
            }
        }

        int size(int o) {
            return N / Integer.highestOneBit(o);
        }

        void push(int o, long v) {
            is[o] += v * size[o] % M;
            is[o] %= M;
            ls[o] += v;
            ls[o] %= M;
        }

        void update1(int i) {
            pushAllDown((i + N) / 2);
            is[i + N] = Num.pow(2, is[i + N], M);
            for (i = (i + N) / 2; i > 0; i >>= 1) {
                is[i] = (is[i * 2] + is[i * 2 + 1]) % M;
            }
        }

        void pushAllDown(int i) {
            if (i > 0) {
                pushAllDown(i / 2);
                pushDown(i);
            }
        }
    }
}
