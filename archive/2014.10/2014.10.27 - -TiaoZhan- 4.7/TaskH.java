package main;



import com.shu_mj.ds.SuffixArray;
import com.shu_mj.ds.UnionFindSet;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskH {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int k = in.nextInt();
            if (k == 0) break;
            solve(k);
        }
    }

    private void solve(int k) {
        String A = in.next();
        String B = in.next();
        out.println(solve(A + "#" + B, k) - solve(A, k) - solve(B, k));
    }

    private long solve(String s, int k) {
        SuffixArray sa = new SuffixArray(s.toCharArray());
        sa.buildHs();
        int n = s.length();
        UnionFindSet uf = new UnionFindSet(n);
        int[] cnt = new int[n];
        Arrays.fill(cnt, 1);
        List<P>[] ps = new List[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new LinkedList<P>();
        }
        for (int i = 0; i < n; i++) {
            ps[sa.hs[i]].add(new P(sa.si[i], sa.si[i + 1]));
        }
        long res = 0;
        long crt = 0;
        for (int i = n - 1; i >= k; i--) {
            for (P p : ps[i]) {
                int l = uf.find(p.x);
                int r = uf.find(p.y);
                crt -= (long) cnt[l] * (cnt[l] + 1) / 2;
                crt -= (long) cnt[r] * (cnt[r] + 1) / 2;
                uf.pre[l] = r;
                cnt[r] += cnt[l];
                crt += (long) cnt[r] * (cnt[r] + 1) / 2;
            }
            res += crt;
        }
        return res;
    }

    private void solve2(int k) {
        String A = in.next();
        String B = in.next();
        String AnB = A + "#" + B;
        int n = AnB.length();
        SuffixArray sa = new SuffixArray(AnB.toCharArray());
        sa.buildHs();
        int[][] cnt = new int[2][n];
        Arrays.fill(cnt[0], 0, A.length(), 1);
        Arrays.fill(cnt[1], A.length() + 1, A.length() + 1 + B.length(), 1);
        UnionFindSet uf = new UnionFindSet(n);
        List<P>[] ps = new List[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new LinkedList<P>();
        }
        for (int i = 0; i < n; i++) {
            ps[sa.hs[i]].add(new P(sa.si[i], sa.si[i + 1]));
        }
        long ans = 0;
        long crt = 0;
        for (int i = n - 1; i >= k; i--) {
            for (P p : ps[i]) {
                int l = uf.find(p.x);
                int r = uf.find(p.y);
                crt -= (long) cnt[0][l] * cnt[1][l];
                crt -= (long) cnt[0][r] * cnt[1][r];
                uf.pre[l] = r;
                cnt[0][r] += cnt[0][l];
                cnt[1][r] += cnt[1][l];
                crt += (long) cnt[0][r] * cnt[1][r];
            }
            ans += crt;
        }
        out.println(ans);
    }
    class P {
        int x;
        int y;

        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
