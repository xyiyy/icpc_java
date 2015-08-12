package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import com.shu_mj.geo.P;
public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n;
        n = in.nextInt();
        P [] p = new P[n];
        for(int i=0;i<n;i++)p[i] = new P(in.nextInt(),in.nextInt());
        double ans = P.convexDiameter(P.convexHull(p));
        out.printf("%.0f%n",ans*ans);
    }
}
