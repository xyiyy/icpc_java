package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;
    private int[][] dp;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        dp = new int[201][201];
        Algo.fill(dp, -1);
        while (in.hasNext()) solve();
    }

    private void solve() {
        int w = in.nextInt();
        int h = in.nextInt();
        if (grundy(w, h) == 0) out.println("LOSE");
        else out.println("WIN");
    }

    private int grundy(int w, int h) {
        if (dp[w][h] != -1) return dp[w][h];
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 2; w - i >= 2; i++) {
            set.add(grundy(i, h) ^ grundy(w - i, h));
        }
        for (int i = 2; h - i >= 2; i++) {
            set.add(grundy(w, i) ^ grundy(w, h - i));
        }
        int g = 0;
        while (set.contains(g)) g++;
        return dp[w][h] = g;
    }
}
