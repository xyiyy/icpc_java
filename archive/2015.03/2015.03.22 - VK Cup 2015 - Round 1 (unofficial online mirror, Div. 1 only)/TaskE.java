package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] is = in.nextIntArray(n);
        Set<Integer> set = new HashSet<Integer>();
        for (int i : is) set.add(i);
        int q = in.nextInt();
        while (q-- != 0) {
            int x = in.nextInt();
            int res = k + 1;
            for (int i = 0; i < n; i++) {
                if (x % is[i] == 0) res = Math.min(res, x / is[i]);
                for (int a = 0; a <= k; a++) {
                    for (int b = 1; b <= k; b++) {
                        if (a + b > k) break;
                        int z = x - a * is[i];
                        if (z >= 0 && z % b == 0 && set.contains(z / b)) {
                            res = Math.min(res, a + b);
                        }
                    }
                }
            }
            out.println(res > k ? -1 : res);
        }
    }
}
