package main;



import com.shu_mj.ds.Hash;
import com.shu_mj.ds.SuffixArray;
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
        while (in.hasNext()) run();
    }

    Hash h = new Hash(100000);

    void run() {
        String a = in.next();
        String b = in.next();
        int n = a.length(), m = b.length();
        /*long[] ah = h.build(a.toCharArray());
        long[] bh = h.build(b.toCharArray());
        int l = 0, r = Math.min(n, m) + 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (fit(ah, bh, n, m, mid)) l = mid + 1;
            else r = mid;
        }
        out.println(l - 1);*/
        int[] fail = KMP(b.toCharArray());
        int[] dp = new int[n];
        dp[0] = a.charAt(0) == b.charAt(0) ? 1 : 0;
        for (int i = 1; i < n; i++) {
            int l = dp[i - 1];
            while (l >= 0 && a.charAt(i) != b.charAt(l)) {
                l = fail[l];
            }
            dp[i] = l + 1;
            if (dp[i] == m) break;
        }
        out.println(Algo.max(dp));
    }

    int[] KMP(char[] p) {
        int m = p.length;
        int[] fail = new int[m + 1];
        int crt = fail[0] = -1;
        for (int i = 1; i <= m; i++) {
            while (crt >= 0 && p[crt] != p[i - 1]) crt = fail[crt];
            fail[i] = ++crt;
        }
        return fail;
    }

    private boolean fit(long[] ah, long[] bh, int n, int m, int mid) {
        for (int i = 0; i + mid <= n; i++) {
            if (h.get(ah, i, i + mid) == h.get(bh, 0, mid)) return true;
        }
        return false;
    }
}
