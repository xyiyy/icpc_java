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
        long n = in.nextLong();
        long max = 1000000;
        while (max > 0) {
            if ((n + max) % 3 == 0) {
                long k = (n + max) / 3;
                if (max * (max + 1) / 2 <= k) break;
            }
            max--;
        }
        long min = 1;
        while (min < 1000000) {
            if ((n + min) % 3 == 0) {
                long k = (n + min) / 3;
                if (min * (min + 1) / 2 <= k) break;
            }
            min++;
        }
//        Algo.debug(min, max);
        out.println(Math.max(0, (max - min) / 3 + 1));
    }
}
