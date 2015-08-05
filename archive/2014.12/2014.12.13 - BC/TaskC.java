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
        int t = in.nextInt();
        while (t-- != 0) solve();
    }

    private void solve() {
        int h = in.nextInt();
        int v = in.nextInt();
        double l = 0, r = 90;
        for (int i = 0; i < 100; i++) {
            double ml = l + (r - l) / 3;
            double mr = r - (r - l) / 3;
            double dl = calc(h, v, ml);
            double dr = calc(h, v, mr);
            if (dl < dr) l = ml;
            else r = mr;
        }
        out.printf("%.2f%n", calc(h, v, l));
    }

    double g = 9.8;
    private double calc(int h, int v, double m) {
        double a = g / 2;
        double b = -v * Math.sin(m);
        double c = -h;
        double delta = b * b - 4 * a * c;
        if (delta < 0) return 0;
        double x = (-b + Math.sqrt(delta)) / 2 / a;
        return v * Math.cos(m) * x;
    }
}
