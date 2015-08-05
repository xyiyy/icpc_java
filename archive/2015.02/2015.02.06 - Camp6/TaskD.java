package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int h = in.nextInt();
        int w = in.nextInt();
        int n = in.nextInt();
        Deque<Integer> d;
        ArrayDeque<Integer> as = new ArrayDeque<Integer>(w / 2);
        ArrayDeque<Integer> bs = new ArrayDeque<Integer>(w / 2);
        for (int i = 0; i < w; i++) {
            if (i % 2 == 0) as.add(i + 1);
            else bs.add(i + 1);
        }
        List<Integer>[] ls = new List[h];
        for (int i = 0; i < h; i++) {
            ls[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            ls[x].add(y);
        }
        for (int i = 0; i < h; i++) {
            ArrayDeque<Integer> tmp = as;
            as = bs;
            bs = tmp;
            if (i % 2 == 1) {
                as.addFirst(bs.pollFirst());
                bs.addLast(as.pollLast());
            }
            for (int s : ls[i]) {
                int t = s + 1;

            }
        }
        for (int i = 0; i < w; i++) {
        }
    }
}
