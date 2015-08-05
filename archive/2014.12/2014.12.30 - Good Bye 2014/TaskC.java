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
        int n = in.nextInt();
        int m = in.nextInt();
        int[] ws = in.nextIntArray(n);
        int[] bs = in.nextIntArray(m);
        for (int i = 0; i < m; i++) bs[i]--;
        long res = 0;
        for (int i = 0; i < m; ) {
            int j = i + 1;
            while (j < m && bs[j] == bs[i]) j++;
            boolean[] vis = new boolean[n];
            while (j < m && bs[j] != bs[i]) {
                if (!vis[bs[j]]) res += ws[bs[i]];
                vis[bs[j]] = true;
                j++;
            }
            while (i + 1 < m && bs[i + 1] == bs[i]) i++;
            i++;
        }
        out.println(res);
    }
}
