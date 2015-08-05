package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
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

    private void solve() {
        int n = in.nextInt();
        int t1 = in.nextInt();
        int t2 = in.nextInt();
        if (n == 1) out.println(0);
        else out.println(-t1 + dfs(n, t1, t2));
    }

    private int dfs(int n, int t1, int t2) {
        if (n == 0) return 0;
        if (n == 1) return 0;
        return (n + 1) / 2 * t1 + t2 + dfs(n / 2, t1, t2);
    }
}
