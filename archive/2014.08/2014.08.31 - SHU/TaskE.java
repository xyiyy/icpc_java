package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) solve();
    }

    private void solve() {
        int n = in.nextInt();
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextInt(), in.nextInt());
        }
        Arrays.sort(ps, new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                return (o1.w - o2.s) - (o2.w - o1.s);
            }
        });
        long sum = 0;
        long ans = -Long.MAX_VALUE;
        for (P p : ps) {
            ans = Math.max(ans, sum - p.s);
            sum += p.w;
        }
        out.println(ans);
    }
    class P {
        int w;
        int s;

        P(int w, int s) {
            this.w = w;
            this.s = s;
        }
    }
}
