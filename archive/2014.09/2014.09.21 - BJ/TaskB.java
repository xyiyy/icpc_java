package main;

import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class TaskB {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d:%n", i);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int[] xs = new int[n];
        int[] hs = new int[n];
        for (int i = 0; i < n; i++) {
            xs[i] = in.nextInt();
            hs[i] = in.nextInt();
        }
        int q = in.nextInt();
        int[] qs = in.nextIntArray(q);
        P[] ps = new P[n + q];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(xs[i], hs[i], false);
        }
        for (int i = 0; i < q; i++) {
            ps[i + n] = new P(qs[i], i, true);
        }
        Arrays.sort(ps, new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                return o1.x - o2.x;
            }
        });
        P[] stack = new P[n];
        int top = 0;
        P[] left = new P[q];
        for (P p : ps) {
            if (p.query) {
                P xp = new P(p.x, 0, false);
                while (top >= 2 && check(stack[top - 2], stack[top - 1], xp)) top--;
                left[p.y] = stack[top - 1];
            } else {
                while (top >= 2 && check(stack[top - 2], stack[top - 1], p)) top--;
                stack[top++] = p;
            }
        }
        top = 0;
        P[] right = new P[q];
        Algo.reverse(ps);
        for (P p : ps) {
            if (p.query) {
                P xp = new P(p.x, 0, false);
                while (top >= 2 && check(xp, stack[top - 1], stack[top - 2])) top--;
                right[p.y] = stack[top - 1];
            } else {
                while (top >= 2 && check(p, stack[top - 1], stack[top - 2])) top--;
                stack[top++] = p;
            }
        }
        for (int i = 0; i < q; i++) {
            double l = Math.atan2(left[i].y, qs[i] - left[i].x);
            double r = Math.atan2(right[i].y, right[i].x - qs[i]);
            l = Math.toDegrees(l);
            r = Math.toDegrees(r);
            out.printf("%.10f%n", 180 - l - r);
        }
    }

    private boolean check(P a, P b, P c) {
        long xca = a.x - c.x;
        long yca = a.y - c.y;
        long xba = b.x - c.x;
        long yba = b.y - c.y;
        return xca * yba - yca * xba >= 0;
    }

    class P{
        int x;
        int y;
        boolean query;

        P(int x, int y, boolean query) {
            this.x = x;
            this.y = y;
            this.query = query;
        }

        @Override
        public String toString() {
            return "P{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
