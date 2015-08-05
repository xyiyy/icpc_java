package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            solve(n);
        }
    }

    private void solve(int n) {
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextDouble(), in.nextDouble());
        }
        Arrays.sort(ps);
        double dis = go(ps, 0, n);
        if (dis >= 10000) out.println("INFINITY");
        else out.printf("%.4f%n", dis);
    }

    P[] tmp = new P[100000];
    private double go(P[] ps, int b, int e) {
        if (e - b <= 1) return 12341234;
        int m = (b + e) / 2;
        double x = ps[m].x;
        double d = Math.min(go(ps, b, m), go(ps, m, e));
        for (int i = b, j = m, k = b; k < e; ) {
            if (j >= e || i < m && ps[i].y < ps[j].y) tmp[k++] = ps[i++];
            else tmp[k++] = ps[j++];
        }
        for (int i = b; i < e; i++) ps[i] = tmp[i];
        LinkedList<P> ls = new LinkedList<P>();
        for (int i = b; i < e; i++) {
            if (Math.abs(ps[i].x - x) >= d) continue;
            for (P p : ls) {
                double dx = p.x - ps[i].x;
                double dy = p.y - ps[i].y;
                if (dy >= d) break;
                d = Math.min(d, Math.sqrt(dx * dx + dy * dy));
            }
            ls.addFirst(ps[i]);
        }
        return d;
    }

    class P implements Comparable<P> {
        double x;
        double y;

        P(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(P o) {
            if (x != o.x) return Double.compare(x, o.x);
            return Double.compare(y, o.y);
        }
    }
}
