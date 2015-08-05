package main;

import com.shu_mj.ds.MaxRect;
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
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            solve(n);
        }
    }

    private void solve(int n) {
        out.println(MaxRect.maxRect(in.nextIntArray(n)));
    }

    private void solve2(int n) {
        int[] is = in.nextIntArray(n);
        int[] left = get(is);
        Algo.reverse(is);
        int[] right = get(is);
        Algo.reverse(is);
        Algo.reverse(right);
        for (int i = 0; i < n; i++) right[i] = n - 1 - right[i];
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, (right[i] - left[i] + 1L) * is[i]);
        }
        out.println(ans);
    }

    private int[] get(int[] is) {
        int n = is.length;
        int[] l = new int[n];
        int[] stack = new int[n];
        int top = 0;
        for (int i = 0; i < n; i++) {
            while (top > 0 && is[stack[top - 1]] >= is[i]) top--;
            l[i] = top == 0 ? 0 : stack[top - 1] + 1;
            stack[top++] = i;
        }
        return l;
    }
}
