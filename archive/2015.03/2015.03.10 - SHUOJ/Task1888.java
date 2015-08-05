package main;

import com.shu_mj.ds.LongSegSum;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1888 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        try {
            run();
        } catch (Throwable e) {

        }
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        LongSegSum seg = new LongSegSum(n);
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            seg.update(i, i + 1, 0, x);
        }
        while (m-- != 0) {
            int t = in.nextInt();
            int l = in.nextInt() - 1;
            int r = in.nextInt();
            if (t == 1) {
                int k = in.nextInt();
                int b = in.nextInt();
                seg.update(l, r, k, b);
            } else {
                out.println(seg.query(l, r));
            }
        }
    }
}
