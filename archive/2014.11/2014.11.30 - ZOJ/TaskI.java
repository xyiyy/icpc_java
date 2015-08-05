package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskI {
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
        char[][] maps = new char[n][];
        for (int i = 0; i < n; i++) {
            maps[i] = in.next().toCharArray();
        }
        TreeMap<Character, Integer>[][] cnt = new TreeMap[n][n];
        for (int i = 0; i <= n / 2; i++) {
            for (int j = 0; j <= i; j++) {
                cnt[i][j] = new TreeMap<Character, Integer>();
            }
        }
        for (int i = 0; i <= n / 2; i++) {
            for (int j = 0; j < i; j++) {
                cnt[j][i] = cnt[i][j];
            }
        }
        for (int i = 0; i <= n / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                cnt[i][n - j - 1] = cnt[i][j];
            }
        }
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                cnt[n - i - 1][j] = cnt[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inc(cnt[i][j], maps[i][j]);
            }
        }
        int ans = 0;
        for (int i = 0; i <= n / 2; i++) {
            for (int j = 0; j <= i; j++) {
                int max = 0;
                int sum = 0;
                for (int c : cnt[i][j].values()) {
                    max = Math.max(max, c);
                    sum += c;
                }
                ans += sum - max;
            }
        }
        out.println(ans);
    }

    private void inc(TreeMap<Character, Integer> cnt, char c) {
        if (!cnt.containsKey(c)) cnt.put(c, 1);
        else cnt.put(c, cnt.get(c) + 1);
    }
}
