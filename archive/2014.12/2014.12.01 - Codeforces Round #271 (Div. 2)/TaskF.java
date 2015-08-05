package main;



import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        Seg seg = new Seg(n, is);
        int t = in.nextInt();
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) ps[i] = new P(is[i], i);
        Algo.sort(ps);
        while (t-- != 0) {
            int l = in.nextInt() - 1;
            int r = in.nextInt();
            int g = seg.query(l, r);
            int ll = Algo.lowerBound(ps, new P(g, l));
            int rr = Algo.lowerBound(ps, new P(g, r));
            out.println(r - l - (rr - ll));
        }
    }

    class P implements Comparable<P> {
        int x;
        int y;

        P(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(P o) {
            if (x != o.x) return x - o.x;
            return y - o.y;
        }

        @Override
        public String toString() {
            return "P{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    class Seg {
        int N;
        int[] is;

        Seg(int n, int[] js) {
            N = Integer.highestOneBit(n) << 1;
            is = new int[N * 2];
            for (int i = 0; i < N; i++) {
                if (i < n) is[i + N] = js[i];
                else is[i + N] = 1;
            }
            for (int i = N - 1; i > 0; i--) {
                is[i] = Num.gcd(is[i * 2], is[i * 2 + 1]);
            }
        }

        int query(int s, int t) {
            return query(1, 0, N, s, t);
        }

        private int query(int o, int l, int r, int s, int t) {
            if (s <= l && r <= t) return is[o];
            else {
                int m = (l + r) / 2;
                if (m <= s) return query(o * 2 + 1, m, r, s, t);
                if (m >= t) return query(o * 2, l, m, s, t);
                return Num.gcd(query(o * 2, l, m, s, t), query(o * 2 + 1, m, r, s, t));
            }
        }
    }
}
