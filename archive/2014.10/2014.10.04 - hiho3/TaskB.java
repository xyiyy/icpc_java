package main;

import com.shu_mj.geo.P;
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
        int n = in.nextInt();
        P[] ps = new P[n];
        Map<P, Integer> w = new HashMap<P, Integer>();
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextDouble(), in.nextDouble());
            w.put(ps[i], in.nextInt());
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, w.get(ps[i]) + calc(ps, w, ps[i]));
        }
        out.println(ans);
    }

    private int calc(P[] ps, Map<P, Integer> w, P c) {
        List<E> es = new ArrayList<E>();
        for (P p : ps) if (p != c) {
            P[] qs = P.isCC(c, 1, p, 1);
            if (qs.length == 0) continue;
            qs[0] = qs[0].sub(c);
            qs[1] = qs[1].sub(c);
            double a0 = Math.atan2(qs[0].y, qs[0].x);
            double a1 = Math.atan2(qs[1].y, qs[1].x);
            if (a0 < 0) a0 += 2 * Math.PI;
            if (a1 < 0) a1 += 2 * Math.PI;
            int v = w.get(p);
            if (a0 < a1 - P.EPS) {
                es.add(new E(a0, v));
                es.add(new E(a1, -v));
            } else {
                es.add(new E(a0, v));
                es.add(new E(2 * Math.PI, -v));
                es.add(new E(0, v));
                es.add(new E(a1, -v));
            }
        }
        Collections.sort(es);
        int crt = 0;
        int res = 0;
        for (E e : es) {
            crt += e.val;
            res = Math.max(res, crt);
        }
        return res;
    }
    class E implements Comparable<E> {
        double angle;
        int val;

        E(double angle, int val) {
            this.angle = angle;
            this.val = val;
        }

        @Override
        public int compareTo(E o) {
            if (angle != o.angle) return angle - o.angle < 0 ? -1 : 1;
            return o.val - val;
        }
    }
}
