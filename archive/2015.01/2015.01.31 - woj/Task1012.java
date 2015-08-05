package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1012 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        while (in.hasNext()) run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] maps = in.nextCharMap(n);
        int[][] hs = new int[n][m];
        long res = 0;
        for (int i = 0; i < n; i++) {
            long crt = 0;
            int[] st = new int[m];
            int top = 0;
            for (int j = 0; j < m; j++) {
                hs[i][j] = maps[i][j] == 'w' ? 1 + (i > 0 ? hs[i - 1][j] : 0) : 0;
                while (top > 0 && hs[i][j] <= hs[i][st[top - 1]]) {
                    crt -= (st[top - 1] - (top > 1 ? st[top - 2] : -1)) * hs[i][st[top - 1]];
                    top--;
                }
                st[top++] = j;
                crt += (st[top - 1] - (top > 1 ? st[top - 2] : -1)) * hs[i][st[top - 1]];
                res += crt;
            }
        }
        out.println(res);
    }
}
