package main;

import com.shu_mj.ds.MaxRect;
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
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] iss = in.nextIntMatrix(m, n);
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (iss[i][j] == 1) {
                    iss[i][j] = (i < m - 1 ? iss[i + 1][j] : 0) + 1;
                }
            }
        }
        int ans = 0;
        for (int[] is : iss) {
            ans = Math.max(ans, (int) MaxRect.maxRect(is));
        }
        out.println(ans);
    }
}
