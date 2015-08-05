package main;

import com.shu_mj.geo.P;
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
        while (n-- != 0) solve();
    }

    private void solve() {
        P[][] pss = new P[4][2];
        for (int i = 0; i < 4; i++) {
            pss[i][0] = new P(in.nextInt(), in.nextInt());
            pss[i][1] = new P(in.nextInt(), in.nextInt());
        }
        int res = 1234;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        P[] ps = new P[4];
                        ps[0] = turn(pss[0], i);
                        ps[1] = turn(pss[1], j);
                        ps[2] = turn(pss[2], k);
                        ps[3] = turn(pss[3], l);
                        ps = P.convexHull(ps);
                        if (ps.length < 4 || P.area(ps) < P.EPS) continue;
                        if (ps[0].sub(ps[1]).equals(ps[3].sub(ps[2])) && ps[0].sub(ps[1]).rot90().equals(ps[1].sub(ps[2]))) {
                            res = Math.min(res, i + j + k + l);
                        }
                    }
                }
            }
        }
        if (res == 1234) out.println(-1);
        else out.println(res);
    }

    private P turn(P[] ps, int k) {
        return ps[0].sub(ps[1]).rot(Math.PI / 2 * k).add(ps[1]);
    }
}
