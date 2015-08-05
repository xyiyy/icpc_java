package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
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
        Seg seg = new Seg(n);
        while (m-- != 0) {
            if (in.next().charAt(0) == 'P') {
                int s = in.nextInt() - 1;
                int t = in.nextInt();
                long c = 1L << in.nextInt();
                seg.update(s, t, c);
            } else {
                int s = in.nextInt() - 1;
                int t = in.nextInt();
                long a = seg.query(s, t);
                boolean f = true;
                for (int i = 0; i < 40; i++) {
                    if ((a & (1L << i)) != 0) {
                        if (f) f = false; else out.print(' ');
                        out.print(i);
                    }
                }
                out.println();
            }
        }
    }

    class Seg {
        public int N;
        public long[] infos;
        public long[] deltas;
        public static final long DEFAULT_INFO = 4;
        public static final long DEFAULT_DELTA = 0;

        public Seg(int n) {
            N = Integer.highestOneBit(n) << 1;
            infos = new long[N * 2];
            deltas = new long[N * 2];
            Arrays.fill(deltas, DEFAULT_DELTA);
            for (int i = N; i < N * 2; i++) {
                infos[i] = DEFAULT_INFO;
            }
            for (int i = N - 1; i > 0; i--) {
                pushUp(i);
            }
        }

        public long mergeInfo(long a, long b) {
            return a | b;
        }

        public void pushUp(int o) {
            infos[o] = mergeInfo(infos[o * 2], infos[o * 2 + 1]);
        }

        public void push(int o, int l, int r, long d) {
            infos[o] = d;
            deltas[o] = d;
        }

        public long query(int s, int t) {
            return query(1, 0, N, s, t);
        }

        long query(int o, int l, int r, int s, int t) {
            if (s <= l && r <= t) {
                // 如果 [l, r) 和 [s, t) 不同，需要修改
                return infos[o];
            } else {
                pushDown(o, l, r);
                int m = (l + r) / 2;
                if (t <= m) return query(o * 2, l, m, s, t);
                if (s >= m) return query(o * 2 + 1, m, r, s, t);
                return mergeInfo(query(o * 2, l, m, s, m), query(o * 2 + 1, m, r, m, t));
            }
        }

        public void update(int s, int t, long d) {
            update(1, 0, N, s, t, d);
        }

        void update(int o, int l, int r, int s, int t, long d) {
            if (s <= l && r <= t) {
                // 如果 [l, r) 和 [s, t) 不同，需要修改
                push(o, l, r, d);
            } else {
                pushDown(o, l, r);
                int m = (l + r) / 2;
                if (s < m) update(o * 2, l, m, s, Math.min(m, t), d);
                if (t > m) update(o * 2 + 1, m, r, Math.max(s, m), t, d);
                pushUp(o);
            }
        }

        public void pushDown(int o, int l, int r) {
            if (deltas[o] != DEFAULT_DELTA) {
                int m = (l + r) / 2;
                push(o * 2, l, m, deltas[o]);
                push(o * 2 + 1, m, r, deltas[o]);
                deltas[o] = DEFAULT_DELTA;
            }
        }

    }

}
