package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task5009 {
    Scanner in;
    PrintWriter out;
    private int[] pre;

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
        int[] is = new int[n + 1];
        Map<Integer, Integer> id = new HashMap<Integer, Integer>();
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            int x = in.nextInt();
            if (!id.containsKey(x)) id.put(x, cnt++);
            is[i] = id.get(x);
        }
        int[] last = new int[cnt];
        Arrays.fill(last, -1);
        pre = new int[n + 1];
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pre[i] = i;
            if (last[is[i]] != -1) union(last[is[i]] - 1, last[is[i]]);
            last[is[i]] = i;
            dp[i] = i;
            int num = 0;
            for (int j = i; j > 0; j = find(j - 1)) {
                num++;
                if (num * num >= dp[i]) break;
                int p = find(j - 1);
                dp[i] = Math.min(dp[i], dp[p] + num * num);
            }
        }
        out.println(dp[n]);
    }

    private void union(int u, int v) {
        u = find(u);
        v = find(v);
        pre[v] = u;
    }

    private int find(int x) {
        if (pre[x] != x) pre[x] = find(pre[x]);
        return pre[x];
    }
}
