package main;

import com.shu_mj.ds.BIT;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;
    private int[] pre;
    private int[] num;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        pre = new int[n];
        num = new int[n];
        BIT bit = new BIT(n + 1);
        for (int i = 0; i < n; i++) {
            pre[i] = i;
            num[i] = 1;
        }
        bit.add(1, n);
        while (m-- != 0) {
            if (in.nextInt() == 0) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                u = find(u);
                v = find(v);
                if (u == v) continue;
                bit.add(num[u], -1);
                bit.add(num[v], -1);
                pre[u] = v;
                num[v] += num[u];
                bit.add(num[v], 1);
            } else {
                int k = in.nextInt();
                int all = bit.sum(0, n + 1);
                out.println(bit.get(all - k));
            }
        }
    }
    int find(int x) {
        if (pre[x] != x) {
            pre[x] = find(pre[x]);
        }
        return pre[x];
    }

}
