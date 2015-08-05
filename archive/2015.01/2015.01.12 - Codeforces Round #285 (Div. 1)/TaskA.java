package main;

import com.shu_mj.tpl.PII;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] cnt = new int[n];
        int[] val = new int[n];
        in.nextIntArray(n, cnt, val);
        Queue<Integer> que = new LinkedList<Integer>();
        List<PII> res = new ArrayList<PII>();
        for (int i = 0; i < n; i++) {
            if (cnt[i] == 1) {
                que.add(i);
            }
        }
        while (!que.isEmpty()) {
            int crt = que.poll();
            if (cnt[crt] == 0) continue;
            int u = val[crt];
            res.add(new PII(crt, u));
            cnt[crt]--;
            cnt[u]--;
            val[u] ^= crt;
            if (cnt[u] == 1) {
                que.add(u);
            }
        }
        out.println(res.size());
        for (PII p : res) {
            out.println(p.x + " " + p.y);
        }
    }
}
