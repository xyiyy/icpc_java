package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;

public class TaskA {
    Scanner in;
    PrintWriter out;
    private boolean[][][] vis;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n1 = in.nextInt();
        int n2 = in.nextInt();
        int k1 = in.nextInt();
        int k2 = in.nextInt();
        int N = Math.max(n1, n2) + 1;
        boolean[][] first = new boolean[N][N];
        boolean[][] second = new boolean[N][N];
        vis = new boolean[2][N][N];
        out.println(dfs(0, n1, n2, k1, k2, first, second) ? "First" : "Second");
    }

    private boolean dfs(int c, int n1, int n2, int k1, int k2, boolean[][] first, boolean[][] second) {
        if (vis[c][n1][n2]) return first[n1][n2];
        vis[c][n1][n2] = true;
        if (n1 == 0) return first[n1][n2] = false;
        for (int i = 1; i <= k1 && i <= n1; i++) {
            if (!dfs(c ^ 1, n2, n1 - i, k2, k1, second, first)) {
                return first[n1][n2] = true;
            }
        }
        return first[n1][n2] = false;
    }
}
