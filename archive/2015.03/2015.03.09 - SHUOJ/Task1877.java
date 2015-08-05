package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1877 {
    Scanner in;
    PrintWriter out;
    private int[] pre;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        pre = new int[n];
        for (int i = 0; i < n; i++) pre[i] = i;
        while (m-- != 0) {
            int t = in.nextInt();
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            if (t == 1) pre[find(x)] = find(y);
            else out.println(find(x) == find(y) ? "Yes" : "No");
        }
    }
    int find(int x) {
        return x == pre[x] ? x : (pre[x] = find(pre[x]));
    }
}
