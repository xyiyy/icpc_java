package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] ls = in.nextIntArray(n);
        int[] cs = in.nextIntArray(n);
        int g = ls[0];
        for (int i : ls) g = Num.gcd(g, i);
        if (g != 1) {
            out.println(-1);
            return ;
        }
        Map<Integer, Integer> cost = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> nCost = new HashMap<Integer, Integer>();
            nCost.put(ls[i], cs[i]);
            for (Map.Entry<Integer, Integer> e : cost.entrySet()) {
                int ng = Num.gcd(e.getKey(), ls[i]);
                int nc = e.getValue() + cs[i];
                if (!nCost.containsKey(ng) || nCost.get(ng) > nc) {
                    nCost.put(ng, nc);
                }
                ng = e.getKey();
                nc = e.getValue();
                if (!nCost.containsKey(ng) || nCost.get(ng) > nc) {
                    nCost.put(ng, nc);
                }
            }
            cost = nCost;
        }
        out.println(cost.get(1));
    }
}
