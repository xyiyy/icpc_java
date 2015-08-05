package main;

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
        run();
    }

    void run() {
        int n = in.nextInt();
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextInt(), in.nextInt());
        }
        Arrays.sort(ps);
        for (P p : ps) set.add(p);
        go(ps, 0, n);
        out.println(set.size());
        for (P p : set) {
            out.println(p.x + " " + p.y);
        }
    }

    private void go(P[] ps, int b, int e) {
        if (e - b <= 1) return ;
        int m = (b + e) / 2;
        int x = ps[m].x;
        for (int i = b; i < e; i++) {
            set.add(new P(x, ps[i].y));
        }
        go(ps, b, m);
        go(ps, m, e);
    }

    Set<P> set = new HashSet<P>();

    class P implements Comparable<P> {
        int x;
        int y;

        P(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(P o) {
            return x - o.x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            P p = (P) o;

            if (x != p.x) return false;
            if (y != p.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
