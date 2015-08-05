package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1011 {
    Scanner in;
    PrintWriter out;
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) solve();
    }

    // a x^2 + b y^2 + c z^2 + d y z + e x z + f x y = 1
    private void solve() {
        a = in.nextDouble();
        b = in.nextDouble();
        c = in.nextDouble();
        d = in.nextDouble();
        e = in.nextDouble();
        f = in.nextDouble();
        
        double l = -1e9, r = 1e9;
        for (int i = 0; i < 100; i++) {
            Algo.debug(l, r);
            double ll = l + (r - l) / 3;
            double rr = r - (r - l) / 3;
            if (dis(ll) > dis(rr)) {
                l = ll;
            } else {
                r = rr;
            }
        }
        out.printf("%.7f%n", Math.sqrt(dis(l)));
    }

    private double dis(double z) {
        double l = -1e9, r = 1e9;
        for (int i = 0; i < 100; i++) {
            double ll = l + (r - l) / 3;
            double rr = r - (r - l) / 3;
            if (dis(ll, z) > dis(rr, z)) {
                l = ll;
            } else {
                r = rr;
            }
        }
        return dis(l, z);
    }

    private double dis(double y, double z) {
        double A = a;
        double B = e * z + f * y;
        double C = b * y * y + c * z * z + d * y * z - 1;
        double delta = B * B - 4 * A * C;
        if (delta < -1e9) return 1e20;
        if (delta < 1e9) delta = 0;
        delta = Math.sqrt(delta);
        double x0 = (-B + delta) / 2 / A;
        double x1 = (-B - delta) / 2 / A;
        return Math.min(dis(x0, y, z), dis(x1, y, z));
    }

    private double dis(double x, double y, double z) {
        return x * x + y * y + z * z;
    }
}
