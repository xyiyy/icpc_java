package main;

import com.shu_mj.ds.SuffixArray;
import com.shu_mj.ds.UnionFindSet;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskJ {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        String s = in.next();
        SuffixArray sa = new SuffixArray(s.toCharArray());
        sa.buildHs();
        int n = s.length();
        UnionFindSet uf = new UnionFindSet(n + 1);
        List<P>[] ps = new List[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new LinkedList<P>();
        }
        for (int i = 0; i < n; i++) {
            ps[sa.hs[i]].add(new P(sa.si[i], sa.si[i + 1]));
        }
        int[] cnt = new int[n + 1];
        Arrays.fill(cnt, 1);
        cnt[n] = 0;
        long res = 0;
        long crt = 1;
        for (int i = n - 1; i >= 0; i--) {
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
        out.println(res);
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
