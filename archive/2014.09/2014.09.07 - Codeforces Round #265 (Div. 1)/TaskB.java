package main;

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
        int[][] iss = in.nextIntMatrix(8, 3);
        for (int[] is : iss) Arrays.sort(is);
//        do {
            do {
                do {
                    do {
                        do {
                            do {
                                do {
                                    do {
                                        if (fit(iss)) {
                                            out.println("YES");
                                            for (int[] is : iss) {
                                                out.println(is[0] + " " + is[1] + " " + is[2]);
                                            }
                                            return ;
                                        }
                                    } while (Algo.nextPermutation(iss[7]));
                                } while (Algo.nextPermutation(iss[6]));
                            } while (Algo.nextPermutation(iss[5]));
                        } while (Algo.nextPermutation(iss[4]));
                    } while (Algo.nextPermutation(iss[3]));
                } while (Algo.nextPermutation(iss[2]));
            } while (Algo.nextPermutation(iss[1]));
//        } while (Algo.nextPermutation(iss[0]));
        out.println("NO");
    }

    private boolean fit(int[][] iss) {
        long[] ds = { -1, -1, -1 };
        for (int i = 1; i < 8; i++) {
            long d = dis(iss[0], iss[i]);
            if (ds[0] == -1) ds[0] = d;
            if (ds[0] == d) continue;
            if (ds[1] == -1) ds[1] = d;
            if (ds[1] == d) continue;
            ds[2] = d;
        }
        Arrays.sort(ds);
        long a = ds[0];
        long b = ds[1];
        long c = ds[2];
        if (a <= 0 || a == b || b == c) return false;
        int ca, cb, cc;
        long[][] dis = new long[8][8];
        for (int i = 0; i < 8; i++) {
            ca = cb = cc = 0;
            for (int j = 0; j < 8; j++) if (i != j) {
                if (dis[i][j] == 0) dis[i][j] = dis[j][i] = dis(iss[i], iss[j]);
                long d = dis[i][j];
                if (d == a) ca++;
                if (d == b) cb++;
                if (d == c) cc++;
            }
            if (ca != 3 || cb != 3 || cc != 1) return false;
        }
        return true;
    }

    private long dis(int[] a, int[] b) {
        long x = a[0] - b[0];
        long y = a[1] - b[1];
        long z = a[2] - b[2];
        return x * x + y * y + z * z;
    }
}
