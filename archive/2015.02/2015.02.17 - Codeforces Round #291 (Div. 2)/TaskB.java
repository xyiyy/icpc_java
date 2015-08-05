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
        P p0 = new P(in.nextDouble(), in.nextDouble());
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextDouble(), in.nextDouble());
        }
        boolean[] del = new boolean[n];
        int res = 0;
        for (int i = 0; i < n; i++) if (!del[i]) {
            res++;
            for (int j = i; j < n; j++) if (!del[j]) {
                if (Math.abs(ps[j].sub(p0).det(ps[i].sub(p0))) < P.EPS) {
                    del[j] = true;
                }
            }
        }
        out.println(res);
    }
}
