package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1880 {
    Scanner in;
    PrintWriter out;
    private int[] pre;
    private int[] dep;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        try {
            run();
        } catch (Throwable e) {
            Algo.debug(pre);
            Algo.debug(dep);
        }
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        pre = new int[n];
        dep = new int[n];
        for (int i = 0; i < n; i++) {
            pre[i] = i;
        }
        while (m-- != 0) {
            int t = in.nextInt();
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            if (t == 1) join(x, y);
            else {
                if (find(x) == find(y)) out.println("Yes " + (dep[y] - dep[x]));
                else out.println("No");
            }
        }
    }

    private void join(int x, int y) {
        x = find(x);
        if (x != find(y)) {
            pre[x] = y;
            dep[x] = 1;
        }
    }

    private int find(int x) {
        if (pre[x] == x) return x;
        else {
            int root = find(pre[x]);
            dep[x] += dep[pre[x]];
            pre[x] = root;
            return root;
        }
    }
}
