package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1881 {
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
        pre = new int[n + m];
        for (int i = 0; i < n + m; i++) {
            pre[i] = i;
        }
        int[] id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
        int top = n;
        for (int i = 0; i < m; i++) {
            int t = in.nextInt();
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            if (t == 1) {
                pre[find(id[x])] = find(id[y]);
            } else if (t == 2) {
                out.println(find(id[x]) == find(id[y]) ? "Yes" : "No");
            } else {
                id[x] = top++;
                pre[id[x]] = id[y];
            }
        }
    }
    int find(int x) {
        return pre[x] == x ? x : (pre[x] = find(pre[x]));
    }
}
