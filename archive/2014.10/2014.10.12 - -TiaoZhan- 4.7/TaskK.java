package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskK {
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
        char[] cs = in.next().toCharArray();
        int s = 0, d = 0;
        for (char c : cs) if (c == '*') s++; else d++;
        int ans = 0;
        if (s >= d - 1) {
            int k = s - (d - 1);
            char[] css = new char[k + cs.length];
            for (int i = 0; i < k; i++) {
                css[i] = '1';
            }
            for (int i = 0; i < cs.length; i++) {
                css[i + k] = cs[i];
            }
            cs = css;
            ans += k;
        }
        int n = cs.length;
        Queue<Integer> que = new LinkedList<Integer>();
        for (int i = n - 1; i >= 0; i--) {
            if (cs[i] != '*') que.add(i);
        }
        s = 0;
        d = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '*') s++;
            else d++;
            if (s >= d) {
                Algo.swap(cs, i, que.poll());
                s--;
                d++;
                ans++;
            }
        }
        if (Algo.count(cs, '*') != 0) {
            if (cs[n - 1] != '*') {
                ans++;
            }
        }
        out.println(ans);
    }
}
