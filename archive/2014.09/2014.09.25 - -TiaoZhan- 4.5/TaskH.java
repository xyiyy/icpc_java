package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskH {
    Scanner in;
    PrintWriter out;
    private int n;
    private int[] stack;
    private boolean[] vis;
    private int maxDepth;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        stack = new int[1 << 11];
        vis = new boolean[1 << 11];
        while (in.hasNext()) {
            n = in.nextInt();
            if (n == 0) break;
            solve();
        }
    }

    private void solve() {
        Arrays.fill(vis, false);
        stack[0] = 1;
        vis[1] = true;
        for (int i = 0; ; i++) {
            maxDepth = i;
            if (dfs(1)) {
                out.println(i);
                return ;
            }
        }
    }

    private boolean dfs(int top) {
        if (stack[top - 1] == n) return true;
        int max = 0;
        for (int i = 0; i < top; i++) max = Math.max(max, stack[i]);
        if ((max << (maxDepth - top + 1)) < n) return false;
        if (top > maxDepth) return false;
        for (int i = 0; i < top; i++) {
            int crt = stack[top - 1] + stack[i];
            if (crt < n * 2 && !vis[crt]) {
                vis[crt] = true;
                stack[top] = crt;
                if (dfs(top + 1)) return true;
                vis[crt] = false;
            }
            crt = Math.abs(stack[top - 1] - stack[i]);
            if (crt > 0 && !vis[crt]) {
                vis[crt] = true;
                stack[top] = crt;
                if (dfs(top + 1)) return true;
                vis[crt] = false;
            }
        }
        return false;
    }

}
