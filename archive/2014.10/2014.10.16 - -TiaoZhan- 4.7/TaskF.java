package main;

import com.shu_mj.ds.Hash2;
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
        char[] a = in.next().toCharArray();
        char[] b = in.next().toCharArray();
        char[] c = in.next().toCharArray();
        int an = a.length;
        int bn = b.length;
        int cn = c.length;
        Hash2 H = new Hash2(Math.max(an, Math.max(bn, cn)));
        long[] ah = H.build(a);
        long[] bh = H.build(b);
        long[] ch = H.build(c);
        if (contains(ah, bh, ch, an, bn, cn, H)) return ;
        if (contains(bh, ah, ch, bn, an, cn, H)) return ;
        if (contains(ah, ch, bh, an, cn, bn, H)) return ;
        if (contains(ch, ah, bh, cn, an, bn, H)) return ;
        if (contains(bh, ch, ah, bn, cn, an, H)) return ;
        if (contains(ch, bh, ah, cn, bn, an, H)) return ;
        int ans = an + bn + cn;
        ans = Math.min(ans, solve(ah, bh, ch, an, bn, cn, H));
        ans = Math.min(ans, solve(ah, ch, bh, an, cn, bn, H));
        ans = Math.min(ans, solve(bh, ah, ch, bn, an, cn, H));
        ans = Math.min(ans, solve(bh, ch, ah, bn, cn, an, H));
        ans = Math.min(ans, solve(ch, ah, bh, cn, an, bn, H));
        ans = Math.min(ans, solve(ch, bh, ah, cn, bn, an, H));
        out.println(ans);
    }

    private boolean contains(long[] ah, long[] bh, long[] ch, int an, int bn, int cn, Hash2 H) {
        boolean res = false;
        for (int i = 0; i + an <= bn; i++) {
            if (H.get(ah, 0, an) == H.get(bh, i, i + an)) res = true;
        }
        if (!res) return false;
        int ans = bn + cn;
        for (int i = 0; i < cn; i++) {
            int l = Math.min(i + 1, bn);
            if (H.get(bh, bn - l, bn) == H.get(ch, i + 1 - l, i + 1)) {
                ans = Math.min(ans, bn + cn - l);
            }
        }
        for (int i = 0; i < bn; i++) {
            int l = Math.min(i + 1, cn);
            if (H.get(ch, cn - l, cn) == H.get(bh, i + 1 - l, i + 1)) {
                ans = Math.min(ans, bn + cn - l);
            }
        }
        out.println(ans);
        return true;
    }

    private int solve(long[] ah, long[] bh, long[] ch, int an, int bn, int cn, Hash2 H) {
        int l = -1;
        for (int i = 0; i <= Math.min(an, bn); i++) {
            if (H.get(ah, an - i, an) == H.get(bh, 0, i)) l = i;
        }
        int r = -1;
        for (int i = 0; i <= Math.min(bn, cn); i++) {
            if (H.get(bh, bn - i, bn) == H.get(ch, 0, i)) r = i;
        }
        return an + bn + cn - l - r;
    }

}
