package main;

import com.shu_mj.math.Matrix;
import com.shu_mj.math.Num;
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
        int m = in.nextInt();
        int n = in.nextInt();
        long mod = 100000;
        if (m == 0) {
            out.println(Num.pow(4, n, mod));
            return ;
        }
        String[] ss = new String[m];
        for (int i = 0; i < m; i++) {
            ss[i] = in.next();
        }
        List<String> pfx = new ArrayList<String>();
        for (String s : ss) {
            for (int j = 0; j <= s.length(); j++) {
                pfx.add(s.substring(0, j));
            }
        }
        Collections.sort(pfx);
        String[] pf = Algo.unique(pfx.toArray(new String[0]));
        int K = pf.length;
        boolean[] ng = new boolean[K];
        int[][] next = new int[K][4];
        for (int i = 0; i < K; i++) {
            ng[i] = false;
            for (int j = 0; j < m; j++) {
                ng[i] |= ss[j].length() <= pf[i].length()
                        && pf[i].substring(pf[i].length() - ss[j].length(), pf[i].length()).equals(ss[j]);
            }
            for (int j = 0; j < 4; j++) {
                String s = pf[i] + "AGCT".charAt(j);
                int k;
                for (;;) {
                    k = Algo.lowerBound(pf, s);
                    if (k < K && pf[k].equals(s)) break;
                    s = s.substring(1, s.length());
                }
                next[i][j] = k;
            }
        }
//        int[][] dp = new int[n + 1][K];
//        dp[0][0] = 1;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < K; j++) {
//                for (int c = 0; c < 4; c++) {
//                    if (!ng[next[j][c]]) {
//                        dp[i + 1][next[j][c]] += dp[i][j];
//                        Algo.debug(i, j, next[j][c], dp[i][j], c);
//                    }
//                }
//            }
//        }
//        out.println(Algo.sum(dp[n]));
        long[][] mat = new long[K][K];
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < 4; j++) {
//                Algo.debug(i, j, next[i][j], ng[next[i][j]]);
                if (!ng[next[i][j]]) mat[i][next[i][j]]++;
            }
        }
//        Algo.debugTable(mat);
        long[][] ini = new long[K][K];
        ini[0][0] = 1;
//        Algo.debug(Matrix.mul(ini, mat)[0]);
//        Algo.debug(Matrix.mul(ini, Matrix.mul(mat, mat))[0]);
//        Algo.debug(Matrix.mul(ini, Matrix.mul(mat, Matrix.mul(mat, mat)))[0]);
        long[][] res = Matrix.mul(ini, Matrix.pow(mat, n, mod), mod);
        out.println(Algo.sum(res[0]) % mod);
    }
}
