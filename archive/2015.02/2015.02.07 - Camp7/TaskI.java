package main;

import com.shu_mj.geo.P;
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
        int n = in.nextInt();
        int h = in.nextInt();
        P[] ps = new P[n];
        boolean[] contains = new boolean[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextDouble(), in.nextDouble());
            contains[i] = in.nextInt() == 1;
        }
        double[] L = new double[n];
        int[] st = new int[n];
        int top = 0;
        long INF = (long) (1e15);
        P l0 = new P(0, h);
        P l1 = new P(1, h);
        for (int i = 0; i < n; i++) {
            while (top > 1 && !fit(ps[st[top - 2]], ps[st[top - 1]], ps[i])) top--;
            if (top == 0 || ps[st[top - 1]].y < ps[i].y + P.EPS) L[i] = -INF;
            else {
                int l = st[top - 1];
                int r = i;
                P s = P.isLL(l0, l1, ps[l], ps[r]);
                L[i] = s.x;
            }
            st[top++] = i;
        }
        double[] R = new double[n];
        top = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (top > 1 && !fit(ps[i], ps[st[top - 1]], ps[st[top - 2]])) top--;
            if (top == 0 || ps[st[top - 1]].y < ps[i].y + P.EPS) R[i] = INF;
            else {
                int l = st[top - 1];
                int r = i;
                P s = P.isLL(l0, l1, ps[l], ps[r]);
                R[i] = s.x;
            }
            st[top++] = i;
        }
//        Algo.debug(L);
//        Algo.debug(R);
        int cnt = Algo.count(contains, true);
        P[] ss = new P[cnt];
        for (int i = 0, c = 0; i < n; i++) {
            if (contains[i]) ss[c++] = new P(L[i], R[i]);
        }
        Arrays.sort(ss, new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                return Double.compare(o1.y, o2.y);
            }
        });
        int res = 0;
        for (int i = 0; i < cnt; ) {
            res++;
            double y = ss[i].y;
            while (i < cnt && ss[i].x <= y) i++;
        }
        out.println(res);
    }

    private boolean fit(P p, P p1, P p2) {
        return p1.sub(p2).det(p.sub(p1)) > 0;
    }
}
