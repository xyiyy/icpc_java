package main;

import com.shu_mj.ds.RMQ;
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
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[][] mat = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[j][i] = -in.nextInt();
            }
        }
        RMQ[] ms = new RMQ[m];
        for (int i = 0; i < m; i++) {
            ms[i] = new RMQ(mat[i]);
        }
        int[] res = new int[m];
        int s = 0, t = 0;
        int[] is = new int[m];
        int max = 0;
        while (t < n) {
            while (t < n) {
                t++;
                int[] js = new int[m];
                for (int i = 0; i < m; i++) {
                    js[i] = -ms[i].queryValue(s, t);
                }
                if (Algo.sum(js) > k) {
                    t--;
                    break;
                }
                is = js;
            }
            if (s == t) {
                t++;
                s++;
                continue;
            }
            if (t - s > max) {
                max = t - s;
                res = is.clone();
            }
            s++;
        }
        for (int i : res) {
            out.print(i + " ");
        }
    }
}
