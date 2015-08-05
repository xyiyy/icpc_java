package main;

import com.shu_mj.tpl.Scanner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: charrec
     */
    Scanner in;
    PrintWriter out;
    private final int INF = 123456;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        Scanner fin;
        try {
            fin = new Scanner(new FileInputStream("font.in"));
        } catch (FileNotFoundException e) {
            return ;
        }
        int[][] fs = new int[27][20];
        fin.next();
        for (int i = 0; i < 27; i++) {
            for (int j = 0; j < 20; j++) {
                fs[i][j] = Integer.parseInt(fin.next(), 2);
            }
        }
        int[][][] fss = new int[27][20][21];
        for (int i = 0; i < 27; i++) {
            for (int j = 0; j < 20; j++) {
                for (int k = 0; k < 21; k++) {
                    fss[i][j][k] = fs[i][k <= j ? k : k - 1];
                }
            }
        }
        int n = in.nextInt();
        int[] cs = new int[n];
        for (int i = 0; i < n; i++) {
            cs[i] = Integer.parseInt(in.next(), 2);
        }
/*        int[][][] dif = new int[n][27][20];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 27; j++) {
                for (int k = 0; k < 20; k++) {
                    dif[i][j][k] = Integer.bitCount(cs[i] ^ fs[j][k]);
                }
            }
        }*/
        int[] c0 = new int[n];
        int[] c1 = new int[n];
        int[] c2 = new int[n];
        int[] p0 = new int[n];
        int[] p1 = new int[n];
        int[] p2 = new int[n];
        Arrays.fill(c0, INF);
        Arrays.fill(c1, INF);
        Arrays.fill(c2, INF);
        Arrays.fill(p0, -1);
        Arrays.fill(p1, -1);
        Arrays.fill(p2, -1);
        for (int i = 0; i < n; i++) {
            if (i + 19 <= n) {
                for (int j = 0; j < 27; j++) {
                    for (int k = 0; k < 20; k++) {
                        int r = cmp(cs, i, i + 19, fs[j], -1, k);
                        if (p0[i] == -1 || r < c0[i]) {
                            p0[i] = j;
                            c0[i] = r;
                        }
                    }
                }
            }
            if (i + 20 <= n) {
                for (int j = 0; j < 27; j++) {
                    int r = cmp(cs, i, i + 20, fs[j], -1, -1);
                    if (p1[i] == -1 || r < c1[i]) {
                        p1[i] = j;
                        c1[i] = r;
                    }
                }
            }
            if (i + 21 <= n) {
                for (int j = 0; j < 27; j++) {
                    for (int k = i; k < i + 21; k++) {
                        if (true || cs[k] == cs[k + 1]) {
                            int r = cmp(cs, i, i + 21, fs[j], k, -1);
//                            Algo.debug(i, j, k, c2[i], p2[i], r);
                            if (p2[i] == -1 || r < c2[i]) {
                                p2[i] = j;
                                c2[i] = r;
                            }
                        }
                    }
                }
            }
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        int[] p = new int[n + 1];
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i - 19 >= 0) {
                if (dp[i] > dp[i - 19] + c0[i - 19]) {
                    dp[i] = dp[i - 19] + c0[i - 19];
                    p[i] = i - 19;
                    a[i] = p0[i - 19];
                }
            }
            if (i - 20 >= 0) {
                if (dp[i] > dp[i - 20] + c1[i - 20]) {
                    dp[i] = dp[i - 20] + c1[i - 20];
                    p[i] = i - 20;
                    a[i] = p1[i - 20];
                }
            }
            if (i - 21 >= 0) {
                if (dp[i] > dp[i - 21] + c2[i - 21]) {
                    dp[i] = dp[i - 21] + c2[i - 21];
                    p[i] = i - 21;
                    a[i] = p2[i - 21];
                }
            }
        }
        List<Integer> ls = new ArrayList<Integer>();
//        Algo.debug(c0);
//        Algo.debug(p0);
//        Algo.debug(c1);
//        Algo.debug(p1);
//        Algo.debug(c2);
//        Algo.debug(p2);
//        Algo.debug(dp);
//        Algo.debug(p);
//        Algo.debug(a);
        for (int i = n; i > 0; i = p[i]) {
//            Algo.debug(i);
            ls.add(a[i]);
        }
        Collections.reverse(ls);
        for (int i : ls) {
            out.print((i == 0 ? " " : (char) ('a' + i - 1)));
        }
        out.println();
    }

    private int compare(int n, int[] cs, int[] f) {
        int res = 12341234;
        for (int i = 0; i + 20 <= n; i++) {
            res = Math.min(res, cmp(cs, i, i + 20, f, -1, -1));
        }
        for (int i = 0; i + 19 <= n; i++) {
            for (int j = 0; j < 20; j++) {
                res = Math.min(res, cmp(cs, i, i + 19, f, -1, j));
            }
        }
        for (int i = 0; i + 21 <= n; i++) {
            for (int j = i; j < i + 20; j++) {
                if (cs[j] == cs[j + 1]) {
                    res = Math.min(res, cmp(cs, i, i + 21, f, j, -1));
                }
            }
        }
        return res;
    }

    private int cmp(int[] s, int b, int e, int[] t, int ss, int ts) {
        int res = 0;
        for (int i = b, j = 0; i < e && j < t.length; i++, j++) {
            if (i == ss) i++;
            if (j == ts) j++;
            res += Integer.bitCount(s[i] ^ t[j]);
        }
        return res;
    }
}
