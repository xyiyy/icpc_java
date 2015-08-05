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
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == -1) break;
            solve(n);
        }
    }

    private void solve(int n) {
        int[] w = new int[n];
        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = in.nextInt();
            h[i] = in.nextInt();
        }
        int[] left = get(w, h);
        Algo.reverse(w);
        Algo.reverse(h);
        int[] right = get(w, h);
        Algo.reverse(left);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, (right[i] + left[i] - w[i]) * h[i]);
        }
        out.println(ans);
    }

    private int[] get(int[] w, int[] h) {
        int n = w.length;
        int[] left = new int[n];
        int[] stack = new int[n];
        int[] length = new int[n];
        int top = 0;
        for (int i = 0; i < n; i++) {
            int len = w[i];
            while (top > 0 && h[i] <= stack[top - 1]) {
                len += length[top - 1];
                top--;
            }
            length[top] = len;
            stack[top++] = h[i];
            left[i] = len;
        }
        return left;
    }
}
