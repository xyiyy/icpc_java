package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1876 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] L = new int[n];
        int[] R = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0) L[i] = i - 1;
            else L[i] = -1;
            if (i < n - 1) R[i] = i + 1;
            else R[i] = -1;
        }
        int crt = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int t : in.nextIntArray(m)) {
            if (t == 1) {
                crt = L[crt];
            } else if (t == 2) {
                crt = R[crt];
            } else if (t == 3) {
                if (L[crt] != -1) R[L[crt]] = R[crt];
                L[R[crt]] = L[crt];
                pq.add(crt);
                crt = R[crt];
            } else {
                int top = pq.poll();
                R[top] = R[crt];
                L[top] = crt;
                R[L[top]] = top;
                if (R[top] != -1) L[R[top]] = top;
            }
        }
        while (L[crt] != -1) crt = L[crt];
        boolean f = true;
        while (crt != -1) {
            if (f) f = false; else out.print(" ");
            out.print(crt + 1);
            crt = R[crt];
        }
        out.println();
    }
}
