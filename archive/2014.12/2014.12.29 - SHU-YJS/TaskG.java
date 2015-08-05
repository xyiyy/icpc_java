package main;

import com.shu_mj.ds.BIT;
import com.shu_mj.tpl.PII;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0) solve();
    }

    private void solve() {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] is = in.nextIntArray(n);
        int q = in.nextInt();
        P[] ps = new P[q];
        for (int i = 0; i < q; i++) {
            ps[i] = new P(in.nextInt() - 1, in.nextInt(), i);
        }
        Arrays.sort(ps, new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                return o1.l - o2.l;
            }
        });
        for (int i = 0; i * B < q; i++) {
            Arrays.sort(ps, i * B, Math.min(i * B + B, q), new Comparator<P>() {
                @Override
                public int compare(P o1, P o2) {
                    return o1.r - o2.r;
                }
            });
        }
        int[] ans = new int[q];
        int[] ans2 = new int[q];
        int crt = 0;
        int[] cnt = new int[n + 1];
        int crtC = 0;
        int L = 0, R = 0;
        for (P p : ps) {
            while (L > p.l) {
                int v = is[--L];
                cnt[v]++;
                if (cnt[v] == k) {
                    crt += k;
                    crtC++;
                }
                else if (cnt[v] > k) crt++;
            }
            while (R < p.r) {
                int v = is[R++];
                cnt[v]++;
                if (cnt[v] == k) {
                    crt += k;
                    crtC++;
                }
                else if (cnt[v] > k) crt++;
            }
            while (L < p.l) {
                int v = is[L++];
                cnt[v]--;
                if (cnt[v] == k - 1) {
                    crt -= k;
                    crtC--;
                }
                else if (cnt[v] >= k) crt--;
            }
            while (R > p.r) {
                int v = is[--R];
                cnt[v]--;
                if (cnt[v] == k - 1) {
                    crt -= k;
                    crtC--;
                }
                else if (cnt[v] >= k) crt--;
            }
            ans[p.id] = crtC;
            ans2[p.id] = crt;
        }
        for (int i = 0; i < q; i++) {
            out.println(ans[i] + " " + ans2[i]);
        }
    }
    static final int B = 256;
    class P {
        int l;
        int r;
        int id;

        public P(int l, int r, int id) {
            this.l = l;
            this.r = r;
            this.id = id;
        }
    }
}
