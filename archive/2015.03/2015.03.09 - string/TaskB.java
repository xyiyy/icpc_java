package main;

import com.shu_mj.ds.Hash2;
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
        int nc = in.nextInt();
        char[] cs = in.next().toCharArray();
        int m = cs.length;
        Hash2 h = new Hash2(m);
        long[] hs = h.build(cs);
        Set<Long> set = new HashSet<Long>();
        for (int i = 0; i + n <= m; i++) {
            set.add(h.get(hs, i, i + n));
        }
        out.println(set.size());
    }
}
