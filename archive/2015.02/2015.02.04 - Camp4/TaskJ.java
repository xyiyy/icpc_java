package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskJ {
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
        int[] is = in.nextIntArray(n);
        int res = 0;
        boolean[] bit = new boolean[n - 1];
        for (int i = 31; i >= 0; i--) {
            boolean[] bs = new boolean[n];
            for (int j = 0; j < n; j++) {
                bs[j] = (is[j] & (1 << i)) != 0;
            }
            if (Algo.count(bs, true) % 2 == 1) {
                res |= 1 << i;
                continue;
            }
            boolean[] nBit = bit.clone();
            boolean crt = false;
            for (int j = 0; j < n; j++) {
                crt ^= bs[j];
                if (crt) nBit[j] = true;
            }
            if (Algo.count(nBit, false) < m - 1) {
                res |= 1 << i;
                continue;
            }
            bit = nBit;
        }
        out.println(res);
    }
}
