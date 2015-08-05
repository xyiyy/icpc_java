package main;

import com.shu_mj.ds.BIT;
import com.shu_mj.tpl.PII;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] ps = new int[n];
        int[] ls = new int[n];
        in.nextIntArray(n, ps, ls);
        int[] cs = new int[n];
        for (int i = 0; i < n - 1; i++) {
            cs[i] = Math.max(0, ps[i + 1] - ps[i] - ls[i]);
        }
        int q = in.nextInt();
        List<PII>[] qs = new List[n];
        for (int i = 0; i < n; i++) {
            qs[i] = new ArrayList<PII>();
        }
        for (int i = 0; i < q; i++) {
            int l = in.nextInt() - 1;
            int r = in.nextInt();
            qs[l].add(new PII(r, i));
        }
        int[] res = new int[q];
        BIT bit = new BIT(n);
        int[] st = new int[n];
        int[] as = new int[n];
        int top = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (top > 0 && ls[st[top - 1]] + ps[st[top - 1]] <= ls[i] + ps[i]) {
                top--;
                bit.add(as[st[top]], -cs[st[top]]);
            }
            int cost = 0;
            if (top > 0) cost = Math.max(0, ps[st[top - 1]] - ls[i] - ps[i]);
            int ad = i;
            if (top > 0) ad = st[top - 1] - 1;
            cs[i] = cost;
            as[i] = ad;
            bit.add(ad, cs[i]);
//            Algo.debug(i, top, cost);
            st[top++] = i;
            for (PII p : qs[i]) {
                res[p.y] = bit.sum(i, p.x - 1);
            }
        }
        for (int i = 0; i < q; i++) {
            out.println(res[i]);
        }
    }

}
