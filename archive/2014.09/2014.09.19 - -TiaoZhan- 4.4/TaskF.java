package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] is = in.nextIntArray(n);
        getMin(n, k, is, 1);
        for (int i = 0; i < n; i++) is[i] = -is[i];
        getMin(n, k, is, -1);
    }

    private void getMin(int n, int k, int[] is) {
        int[] deq = new int[n];
        int s = 0, t = 0;
        for (int i = 0; i < n; i++) {
            while (t - s > 0 && is[i] <= is[deq[t - 1]]) t--;
            deq[t++] = i;
            if (i >= k - 1) {
                if (i == k - 1) out.print(is[deq[s]]);
                else out.print(" " + is[deq[s]]);
                if (deq[s] == i - k + 1) s++;
            }
        }
        out.println();
    }

    private void getMax(int n, int k, int[] is) {
        int[] deq = new int[n];
        int s = 0, t = 0;
        for (int i = 0; i < n; i++) {
            while (t - s > 0 && is[i] >= is[deq[t - 1]]) t--;
            deq[t++] = i;
            if (i >= k - 1) {
                if (i == k - 1) out.print(is[deq[s]]);
                else out.print(" " + is[deq[s]]);
                if (deq[s] == i - k + 1) s++;
            }
        }
        out.println();
    }

    private void getMin(int n, int k, int[] is, int x) {
        int[] deq = new int[n];
        int s = 0, t = 0;
        for (int i = 0; i < n; i++) {
            while (t - s > 0 && is[i] < deq[t - 1]) t--;
            deq[t++] = is[i];
            if (i >= k - 1) {
                if (i == k - 1) out.print(deq[s] * x);
                else out.print(" " + deq[s] * x);
                if (deq[s] == is[i - k + 1]) s++;
            }
        }
        out.println();
    }
}
