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
        int r = in.nextInt();
        int avg = in.nextInt();
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            ps[i] = new P(b, a);
        }
        Algo.sort(ps);
        long s = (long) avg * n;
        for (P p : ps) s -= p.y;
        if (s <= 0) {
            out.println(0);
            return ;
        }
        long cnt = 0;
        for (P p : ps) {
            long c = Math.min(r - p.y, s);
            s -= c;
            cnt += c * p.x;
//            Algo.debug(s, cnt, c, p.x, p.y);
            if (s <= 0) {
                out.println(cnt);
                return ;
            }
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
            if (x != o.x) return x - o.x;
            return y - o.y;
        }
    }
}
