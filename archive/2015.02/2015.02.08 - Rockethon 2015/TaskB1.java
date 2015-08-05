package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB1 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        long m = in.nextLong() - 1;
        int l = 0, r = n - 1;
        int[] is = new int[n];
        for (int i = 0; i < n - 1; i++) {
            if ((m & (1l << (n - i - 2))) != 0) {
                is[r--] = i + 1;
            } else {
                is[l++] = i + 1;
            }
        }
        for (int i = 0; i < n; i++) if (is[i] == 0) is[i] = n;
        for (int i : is) {
            out.print(i + " ");
        }
        out.println();
    }
    void run2() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = i + 1;
        }
        int val = 0;
        do {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    int min = n;
                    for (int k = i; k <= j; k++) {
                        min = Math.min(min, is[k]);
                    }
                    sum += min;
                }
            }
            if (sum > val) val = sum;
//            Algo.debug(sum, is);
//            Algo.debug(sum);
        } while (Algo.nextPermutation(is));
        int times = 0;
        do {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    int min = n;
                    for (int k = i; k <= j; k++) {
                        min = Math.min(min, is[k]);
                    }
                    sum += min;
                }
            }
            if (sum == val) {
                Algo.debug(is);
//                times++;
                if (times >= m) break;
            }
        } while (Algo.nextPermutation(is));
        for (int i : is) {
            out.print(i + " ");
        }
        out.println();
    }
}
