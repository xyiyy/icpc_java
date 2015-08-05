package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;
    private int[] phi;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        phi = Num.phiTable(65536);
        while (in.hasNext()) solve();
    }

    private void solve() {
        int p = in.nextInt();
        out.println(phi[p - 1]);
    }
}
