package main;

import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;

public class TaskC {
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
        long[] ds = new long[n * 2];
        long[] hs = new long[n * 2];
        for (int i = 0; i < n * 2; i++) {
            ds[i] = (i < n ? in.nextInt() : ds[i - n]);
        }
        for (int i = 0; i < n * 2; i++) {
            hs[i] = (i < n ? 2 * in.nextInt() : hs[i - n]);
        }
        Seg seg = new Seg(n * 2, ds, hs);
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            if (u <= v) u += n;
            v++;
            out.println(seg.query(v, u));
        }
    }

    class Seg {
        int N;
        int[][] iss; // [left, right]
        private final long[] ds;
        private final long[] hs;
        private final long[] ss;

        Seg(int n, long[] ds2, long[] hs2) {
            N = Integer.highestOneBit(n) << 1;
            iss = new int[N * 2][3];
            ds = new long[N];
            hs = new long[N];
            System.arraycopy(ds2, 0, ds, 0, n);
            System.arraycopy(hs2, 0, hs, 0, n);
            ss = new long[N];
            for (int i = 0; i < N; i++) {
                ss[i] = (i == 0 ? 0 : ss[i - 1]) + ds[i];
            }
            for (int i = 0; i < N; i++) {
                iss[i + N][0] = iss[i + N][1] = i;
            }
            for (int i = N - 1; i > 0; i--) {
                iss[i] = merge(iss[i * 2], iss[i * 2 + 1]);
            }
        }

        int[] merge(int[] l, int[] r) {
            int ll = l[0];
            int lr = l[1];
            int rl = r[0];
            int rr = r[1];
            int L = ll;
            int R = rr;
            if (hs[L] + sum(L, N - 1) < hs[rl] + sum(rl, N - 1)) L = rl;
            if (hs[R] + sum(0, R - 1) < hs[lr] + sum(0, lr - 1)) R = lr;
//            Algo.debug(ll, lr, rl, rr, L, R);
            return new int[] { L, R };
        }

        long query(int l, int r) {
            int[] res = query(1, 0, N, l, r);
            int L = res[0], R = res[1];
//            Algo.debug(l, r, L, R);
            if (L < R) return hs[L] + hs[R] + sum(L, R - 1);
            r--;
            long lres = 0;
            long rres = 0;
            if (R > l) {
                res = query(1, 0, N, l, R);
                lres = hs[res[0]] + hs[R] + sum(res[0], R - 1);
            }
            if (L < r) {
                res = query(1, 0, N, L + 1, r + 1);
                rres = hs[L] + hs[res[1]] + sum(L, res[1] - 1);
            }
            return Math.max(hs[l] + hs[r] + sum(l, r - 1), Math.max(lres, rres));
        }
        int[] query(int o, int L, int R, int l, int r) {
            if (l <= L && R <= r) {
                return iss[o];
            } else {
                int M = (L + R) / 2;
                if (r <= M) return query(o * 2, L, M, l, r);
                if (l >= M) return query(o * 2 + 1, M, R, l, r);
                return merge(query(o * 2, L, M, l, r), query(o * 2 + 1, M, R, l, r));
            }
        }
        long sum(int l, int r) {
            return (r >= 0 ? ss[r] : 0) - (l > 0 ? ss[l - 1] : 0);
        }
    }

}
