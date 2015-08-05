package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

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
        while (n-- != 0) solve();
    }

    private void solve() {
        char[] cs = in.next().toCharArray();
        int[] cnt = new int[26];
        for (char c : cs) cnt[c - 'a']++;
        int num = 0;
        for (int i : cnt) if (i % 2 == 1) num++;
        if (num > 1) {
            out.println("Impossible");
            return ;
        }
        int ans = 0;
        int n = cs.length;
        int[] cnt2 = new int[26];
        char[] target = new char[n / 2];
        for (int i = 0, p = 0; i < n; i++) {
            int c = cs[i] - 'a';
            if (cnt2[c] < cnt[c] / 2) {
                ans += i - p;
                target[p++] = cs[i];
                cs[i] = '*';
                cnt2[c]++;
            }
        }
        if (n % 2 == 1) {
            for (int i = 0, left = 0; i < n; i++) {
                if (cs[i] != '*' && cnt[cs[i] - 'a'] % 2 == 1) {
                    ans += left;
                    cs[i] = '*';
                    break;
                }
                if (cs[i] != '*') left++;
            }
        }
        Algo.reverse(target);
        char[] work = new char[n / 2];
        for (int i = 0, p = 0; i < n; i++) {
            if (cs[i] != '*') {
                work[p++] = cs[i];
            }
        }
        ans += go(target, work, 0, n / 2);
        out.println(ans);
    }

    private int go(char[] target, char[] cs, int b, int e) {
        if (e - b <= 1) return 0;
        int m = (b + e) / 2;
        int[] cnt = new int[26];
        for (int i = b; i < m; i++) cnt[target[i] - 'a']++;
        int[] cnt2 = new int[26];
        int res = 0;
        char[] buf = new char[e];
        for (int i = b, p = b; i < e; i++) {
            int c = cs[i] - 'a';
            if (cnt2[c] < cnt[c]) {
                res += i - p;
                buf[p++] = cs[i];
                cs[i] = '*';
                cnt2[c]++;
            }
        }
        for (int i = b, p = m; i < e; i++) {
            if (cs[i] != '*') {
                buf[p++] = cs[i];
            }
        }
        for (int i = b; i < e; i++) {
            cs[i] = buf[i];
        }
        res += go(target, cs, b, m);
        res += go(target, cs, m, e);
        return res;
    }
}
