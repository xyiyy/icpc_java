package main;

import com.shu_mj.ds.BIT;
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
        int k = in.nextInt();
        int n = in.nextInt();
        int[] is = in.nextIntArray(n * 2);
        int min = Algo.min(is);
        for (int i = 0; i < n * 2; i++) is[i] -= min;
        int max = Algo.max(is);
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            int x = is[i * 2];
            int y = is[i * 2 + 1];
            ps[i] = new P(Math.min(x, y), Math.max(x, y));
        }
        Algo.sort(ps);
        BIT bit = new BIT(max + 1);
        boolean[] vis = new boolean[max + 1];
        for (P p : ps) {
            int filled = bit.sum(p.x, p.y + 1);
            if (filled >= k || filled == p.y - p.x + 1) {
                continue;
            }
            for (int i = p.y; i >= p.x; i--) {
                if (!vis[i]) {
                    vis[i] = true;
                    bit.add(i, 1);
                    filled++;
                    if (filled == k || filled == p.y - p.x + 1) break;
                }
            }
        }
        out.println(bit.sum(0, max + 1));
        for (int i = 0; i <= max; i++) if (vis[i]) {
            out.println(i + min);
        }
    }
    class P implements Comparable<P> {
        int x;
        int y;

        P(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(P o) {
            return y - o.y;
        }
    }
}
