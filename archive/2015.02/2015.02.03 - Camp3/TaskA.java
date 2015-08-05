package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
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
        Seg seg = new Seg(n);
        while (m-- != 0) {
            int t = in.nextInt();
            int l = in.nextInt() - 1;
            int r = in.nextInt();
            if (t == 4) {
                seg.query(l, r);
                out.println(seg.Min + " " + seg.Max);
            } else {
                int c = in.nextInt();
                seg.update(t, l, r, c);
            }
//            Algo.debug("min", seg.min);
//            Algo.debug("max", seg.max);
//            Algo.debug("add", seg.add);
        }
    }
    final int INF = Integer.MAX_VALUE;
    class Seg {
        int N;
        int[] min;
        int[] max;
        int[] add;
        Seg(int n) {
            N = Integer.highestOneBit(n) << 1;
            min = new int[N * 2];
            max = new int[N * 2];
            add = new int[N * 2];
            for (int i = 0; i < n; i++) {
                min[i + N] = max[i + N] = in.nextInt();
            }
            for (int i = N - 1; i > 0; i--) {
                min[i] = Math.min(min[i * 2], min[i * 2 + 1]);
                max[i] = Math.max(max[i * 2], max[i * 2 + 1]);
            }
        }
        int Min;
        int Max;
        void query(int l, int r) {
            Min = INF;
            Max = -INF;
            query(1, 0, N, l, r);
        }
        void query(int o, int L, int R, int l, int r) {
            if (l <= L && R <= r) {
                Min = min[o];
                Max = max[o];
            } else {
                pushDown(o);
                int M = (L + R) / 2;
                int cMin = INF;
                int cMax = -INF;
                if (l < M) {
                    query(o * 2, L, M, l, r);
                    cMin = Math.min(cMin, Min + add[o]);
                    cMax = Math.max(cMax, Max + add[o]);
                }
                if (r > M) {
                    query(o * 2 + 1, M, R, l, r);
                    cMin = Math.min(cMin, Min + add[o]);
                    cMax = Math.max(cMax, Max + add[o]);
                }
                Min = cMin;
                Max = cMax;
            }
        }
        void update(int t, int l, int r, int c) {
            update(1, 0, N, l, r, t, c);
        }

        void pushDown(int o) {
            int lo = min[o] - add[o];
            int hi = max[o] - add[o];
            push(o * 2, lo, hi);
            push(o * 2 + 1, lo, hi);
        }

        private void push(int o, int lo, int hi) {
            min[o] = Math.max(min[o], lo);
            max[o] = Math.max(max[o], lo);
            max[o] = Math.min(max[o], hi);
            min[o] = Math.min(min[o], hi);
        }

        void update(int o, int L, int R, int l, int r, int t, int c) {
            if (l <= L && R <= r) {
                if (t == 1) {
                    min[o] += c;
                    max[o] += c;
                    add[o] += c;
                } else if (t == 3) {
                    min[o] = Math.max(min[o], c);
                    max[o] = Math.max(max[o], c);
                } else if (t == 2) {
                    max[o] = Math.min(max[o], c);
                    min[o] = Math.min(min[o], c);
                }
            } else {
                pushDown(o);
                if (t != 1) c -= add[o];
                int M = (L + R) / 2;
                if (l < M) update(o * 2, L, M, l, r, t, c);
                if (r > M) update(o * 2 + 1, M, R, l, r, t, c);
                min[o] = Math.min(min[o * 2], min[o * 2 + 1]) + add[o];
                max[o] = Math.max(max[o * 2], max[o * 2 + 1]) + add[o];
            }
        }
    }
}
