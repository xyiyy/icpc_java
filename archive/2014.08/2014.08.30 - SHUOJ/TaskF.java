package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }
    void run() {
        while (in.hasNext()) solve();
    }

    void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] is = in.nextIntArray(m);
        long sum = 0;
        for (int i = 1; i < (1 << m); i++) {
            int lcm = 1;
            int cnt = 0;
            for (int j = 0; j < m; j++) {
                if ((i & (1 << j)) != 0) {
                    lcm = (int) Num.lcm(is[j], lcm);
                    cnt++;
                }
            }
            if (cnt % 2 == 0) sum -= n / lcm;
            else sum += n / lcm;
        }
        out.println(sum);
    }
}
