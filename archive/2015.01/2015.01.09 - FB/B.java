package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class B {
    Scanner in;
    PrintWriter out;
    private int a;
    private int b;
    private int c;
    private int[][] mat;
    private int n;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        for (int i = 1; i <= n; i++) {
            out.printf("Case #%d: ", i);
            solve();
        }
    }

    private void solve() {
        a = in.nextInt();
        b = in.nextInt();
        c = in.nextInt();
        n = in.nextInt();
        mat = in.nextIntMatrix(n, 3);
        out.println(dfs(0, 0, 0, 0) ? "yes" : "no");
    }

    private boolean dfs(int crt, int x, int y, int z) {
        if (x == a && y == b && z == c) return true;
        if (crt == n) return false;
        if (x > a || y > b || z > c) return false;
        return dfs(crt + 1, x + mat[crt][0], y + mat[crt][1], z + mat[crt][2]) ||
                dfs(crt + 1, x, y, z);
    }
}
