package main;

import com.shu_mj.ds.BIT;
import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0) {
            solve();
        }
    }
    final long M = 1000000007;
    final int C = 30000 + 1;
    final int B = 128;

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] is = in.nextIntArray(n);
        P[] ps = new P[m];
        for (int i = 0; i < m; i++) {
            int l = in.nextInt() - 1;
            int r = in.nextInt();
            ps[i] = new P(l, r, i);
        }
        Arrays.sort(ps);
        for (int i = 0; i * B < m; i++) {
            Arrays.sort(ps, i * B, Math.min(i * B + B, m), new Comparator<P>() {
                @Override
                public int compare(P o1, P o2) {
                    return o1.r - o2.r;
                }
            });
        }
        long[] res = new long[m];
        long ans = 1;
        int l = 0;
        int r = 0;
        int[] cnt = new int[C];
        int num = 0;
        int[] inv = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            inv[i] = (int) Num.inv(i, M);
        }
        for (P p : ps) {
            while (l > p.l) {
                try {
                    ans *= num + 1;
                    ans %= M;
                    ans *= inv[cnt[is[l - 1]] + 1];
                    ans %= M;
                    cnt[is[l - 1]]++;
                    num++;
                    l--;
                } catch (ArrayIndexOutOfBoundsException e) {
                    for (;;) ;
                }
            }
            while (r < p.r) {
                ans *= num + 1;
                ans %= M;
                ans *= inv[cnt[is[r]] + 1];
                ans %= M;
                cnt[is[r]]++;
                num++;
                r++;
            }
            while (l < p.l) {
                ans *= inv[num];
                ans %= M;
                ans *= cnt[is[l]];
                ans %= M;
                cnt[is[l]]--;
                num--;
                l++;
            }
            while (r > p.r) {
                ans *= inv[num];
                ans %= M;
                ans *= cnt[is[r - 1]];
                ans %= M;
                cnt[is[r - 1]]--;
                num--;
                r--;
            }
            res[p.id] = ans;
        }
        for (long i : res) out.println(i);
    }
    class P implements Comparable<P> {
        int l;
        int r;
        int id;

        public P(int l, int r, int id) {
            this.l = l;
            this.r = r;
            this.id = id;
        }

        @Override
        public int compareTo(P o) {
            return l - o.l;
        }
    }
}
