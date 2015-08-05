package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;
    private int[] cnt;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        for (int n = 1; n < 8; n++) {
            int[] is = new int[n];
            for (int i = 0; i < n; i++) is[i] = i;
            cnt = new int[n + 1];
            do {
                print(is);
            } while (Algo.nextPermutation(is));
            long sum = 0;
            for (int i = 0; i <= n; i++) sum += cnt[i] * i;
            long all = 1;
            for (int i = 1; i <= n; i++) all *= i;
            Algo.debug(n, cnt);
            out.println(n + ": " + sum * 1.0 / all + " " + sum + " " + all + " " + sum / all + " " + sum % all);
        }
    }
    void print(int[] is) {
        int n = is.length;
        boolean[] vis = new boolean[n];
        int c = 0;
        for (int i = 0; i < n; i++) if (!vis[i]) {
//            out.print("(" + (i + 1));
            c++;
            vis[i] = true;
            int x = is[i];
            while (x != i) {
//                out.print(" " + (x + 1));
                vis[x] = true;
                x = is[x];
            }
//            out.print(")");
        }
//        out.println();
        cnt[c]++;
    }
}
