package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG1 {
    Scanner in;
    PrintWriter out;
    private int cnt;
    private int inv;
    private int k;
    private int n;
    private int[] is;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        n = in.nextInt();
        k = in.nextInt();
        is = in.nextIntArray(n);
        cnt = 0;
        inv = 0;
        dfs(0);
        out.printf("%.15f%n", inv * 1.0 / cnt);
    }

    private void dfs(int c) {
        if (c == k) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (is[i] < is[j]) inv++;
                }
            }
            cnt++;
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    Algo.reverse(is, i, j + 1);
                    dfs(c + 1);
                    Algo.reverse(is, i, j + 1);
                }
            }
        }
    }
}
