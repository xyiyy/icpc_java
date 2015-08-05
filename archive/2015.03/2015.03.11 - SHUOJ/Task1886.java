package main;

import com.shu_mj.ds.LongSegMax;
import com.shu_mj.ds.LongSegMin;
import com.shu_mj.ds.LongSegSum;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1886 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        LongSegSum sum = new LongSegSum(n);
        LongSegMin min = new LongSegMin(n);
        LongSegMax max = new LongSegMax(n);
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            sum.update(i, i + 1, 0, x);
            min.update(i, i + 1, 0, x);
            max.update(i, i + 1, 0, x);
        }
        while (m-- != 0) {
            int t = in.nextInt();
            int l = in.nextInt() - 1;
            int r = in.nextInt();
            if (t == 1) {
                sum.update(l, l + 1, 1, r);
                min.update(l, l + 1, 1, r);
                max.update(l, l + 1, 1, r);
            } else {
                out.println(max.query(l, r) + " " + sum.query(l, r) + " " + min.query(l, r));
            }
        }
    }
}
