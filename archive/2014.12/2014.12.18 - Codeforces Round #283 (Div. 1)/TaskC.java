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
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextInt(), in.nextInt(), 0, i);
        }
        int m = in.nextInt();
        P[] pe = new P[m];
        for (int i = 0; i < m; i++) {
            pe[i] = new P(in.nextInt(), in.nextInt(), in.nextInt(), i);
        }
        Algo.sort(ps, new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                if (o1.x != o2.x) return o1.x - o2.x;
                return o1.y - o2.y;
            }
        });
        TreeSet<P> set = new TreeSet<P>(new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                if (o1.y != o2.y) return o1.y - o2.y;
                return o1.id - o2.id;
            }
        });
        Algo.sort(pe, new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                return o1.x - o2.x;
            }
        });
        int e = 0;
        for (P p : ps) {
            while (e < m && pe[e].x <= p.x) {
                set.add(pe[e]);
                e++;
            }
            P pp = new P(p.x, p.y, -1, 0);
            P s = set.ceiling(pp);
            if (s == null) {
                out.println("NO");
                return ;
            }
            p.c = s.id;
            s.c--;
            if (s.c == 0) set.remove(s);
        }

        Algo.sort(ps, new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                return o1.id - o2.id;
            }
        });
        out.println("YES");
        for (P p : ps) out.print((p.c + 1) + " ");
        out.println();
    }
    class P {
        int x;
        int y;
        int c;
        int id;

        public P(int x, int y, int c, int id) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.id = id;
        }
    }
}
