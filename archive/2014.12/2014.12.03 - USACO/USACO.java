package main;

import com.shu_mj.tpl.PII;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: humble
     */
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int k = in.nextInt();
        int n = in.nextInt() + 1;
        int[] is = in.nextIntArray(k);
        TreeSet<Integer> set = new TreeSet<Integer>();
        set.add(1);
        int[] ds = new int[n];
        Arrays.fill(ds, -1);
        int crt = 0;
        while (n-- > 0) {
            crt = set.higher(crt);
            if (n == 0) {
                out.println(crt);
                return ;
            }
            for (int i = 0; i < k; i++) {
                int v = is[i];
                if (ds[i] == crt / v) continue;
                ds[i] = crt / v;
                set.add(v * set.higher(crt / v));
            }
        }
    }
}
