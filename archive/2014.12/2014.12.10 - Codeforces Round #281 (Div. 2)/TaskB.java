package main;

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
        int[] is = in.nextIntArray(n);
        long f = 0;
        long s = 0;
        for (int i : is) if (i < 0) f += i; else s += i;
        if (f + s > 0) out.println("first");
        else if (f + s < 0) out.println("second");
        else {
            List<Integer> fs = new ArrayList<Integer>();
            List<Integer> ss = new ArrayList<Integer>();
            for (int i : is) if (i > 0) fs.add(i); else ss.add(i);

            for (int i = 0; i < Math.min(fs.size(), ss.size()); i++) {
                int r = fs.get(i) + ss.get(i);
                if (r > 0) {
                    out.println("first");
                    return ;
                } else if (r < 0) {
                    out.println("second");
                    return ;
                }
            }

            if (fs.size() > ss.size() || fs.size() == ss.size() && is[n - 1] > 0) out.println("first");
            else out.println("second");
        }
    }
}
