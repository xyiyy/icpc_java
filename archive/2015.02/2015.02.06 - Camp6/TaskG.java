package main;

import com.shu_mj.geo.P;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
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
            ps[i] = new P(in.nextDouble(), in.nextDouble());
        }
        for (int i = 1; i + 1 < n; i++) {
            P[] up = Arrays.copyOfRange(ps, i, n);
            P[] dn = Arrays.copyOfRange(ps, 0, i + 1);
            P crt = ps[i];
            up = P.convexHull(up);
            dn = P.convexHull(dn);
            int upid = -1;
            int dnid = -1;
            for (int j = 0; j < up.length; j++) {
                if (up[j] == crt) upid = j;
            }
            for (int j = 0; j < dn.length; j++) {
                if (dn[j] == crt) dnid = j;
            }
            if (upid == -1 || dnid == -1) {
                out.println("Impossible");
                return ;
            }
            P ul = up[(upid + up.length - 1) % up.length].sub(crt);
            P ur = up[(upid + 1) % up.length].sub(crt);
            P dl = dn[(dnid + 1) % dn.length].sub(crt);
            P dr = dn[(dnid + dn.length - 1) % dn.length].sub(crt);
            if (!check(ul, ur, dl) || !check(ul, ur, dr) || !check(dr, dl, ul) || !check(dr, dl, ur)) {
                out.println("Impossible");
                return ;
            }
        }
//        Algo.reverse(ps);
//        for (int i = 1; i + 1 < n; i++) {
//            P[] up = Arrays.copyOfRange(ps, i, n);
//            P[] dn = Arrays.copyOfRange(ps, 0, i + 1);
//            P crt = ps[i];
//            up = P.convexHull(up);
//            dn = P.convexHull(dn);
//            int upid = -1;
//            int dnid = -1;
//            for (int j = 0; j < up.length; j++) {
//                if (up[j] == crt) upid = j;
//            }
//            for (int j = 0; j < dn.length; j++) {
//                if (dn[j] == crt) dnid = j;
//            }
//            if (upid == -1 || dnid == -1) {
//                out.println("Impossible");
//                return ;
//            }
//            P ul = up[(upid + up.length - 1) % up.length].sub(crt);
//            P ur = up[(upid + 1) % up.length].sub(crt);
//            P dl = dn[(dnid + 1) % dn.length].sub(crt);
//            P dr = dn[(dnid + dn.length - 1) % dn.length].sub(crt);
//            if (!check(ul, ur, dl) || !check(ul, ur, dr)) {
//                out.println("Impossible");
//                return ;
//            }
//        }
        out.println("Possible");
    }

    private boolean check(P l, P r, P p) {
        return P.rad2(r, p) + P.rad2(p, l) > 1.5 * Math.PI;
    }
}
