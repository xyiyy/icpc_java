package main;

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
        while (in.hasNext()) solve();
    }

    private void solve() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i : is) pq.add(i);
        int sum = 0;
        while (pq.size() > 1) {
            int p = pq.poll();
            int q = pq.poll();
            sum += p + q;
            pq.add(p + q);
        }
        out.println(sum);
    }
}
