package main;

import com.shu_mj.math.Matrix;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] bs = in.nextIntArray(n);
        int[][] res = new int[n][];
        res[0] = new int[(bs[0] + 8) / 9];
        Arrays.fill(res[0], 9);
        if (bs[0] % 9 != 0) res[0][(bs[0] + 8) / 9 - 1] = bs[0] % 9;
        for (int i = 1; i < n; i++) {
            res[i] = go(res[i - 1], bs[i]);
        }
        for (int[] is : res) {
            for (int i : is) out.print(i);
            out.println();
        }
    }

    private int[] go(int[] is, int b) {
        int n = is.length;
        int m = Math.max(n, (b + 8) / 9);
        return new int[0];
    }
}
