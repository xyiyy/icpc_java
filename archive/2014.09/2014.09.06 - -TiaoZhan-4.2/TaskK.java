package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskK {
    Scanner in;
    PrintWriter out;
    private char[][] maps;
    private int[][][][][] dp;
    private int n;
    private int m;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        n = in.nextInt();
        m = in.nextInt();
        maps = new char[n][];
        for (int i = 0; i < n; i++) maps[i] = in.next().toCharArray();
        dp = new int[2][n + m + 10][n + m + 10][n + m + 10][n + m + 10];
        for (int[][][][] issss : dp) for (int[][][] isss : issss) Algo.fill(isss, -1);
        out.println((grundy(0, 0, n - 1 + m - 1, -(m - 1), n - 1) ^ grundy(1, 0, n - 1 + m - 1, -(m - 1), n - 1)) == 0 ?
                "LOSE" : "WIN");
    }

    private int grundy(int o, int s1, int t1, int s2, int t2) {
        if (dp[o][s1 + 2][t1 + 2][s2 + m + 2][t2 + m + 2] != -1) return dp[o][s1 + 2][t1 + 2][s2 + m + 2][t2 + m + 2];
//        Set<Integer> set = new HashSet<Integer>();
        BitSet set = new BitSet();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) if ((((i ^ j) & 1) ^ o) == 0 && inS(i + j, s1, t1) && inS(i - j, s2, t2)) {
                if (maps[i][j] == 'L') {
//                    set.add(grundy(o, s1, i + j - 1, s2, t2) ^ grundy(o, i + j + 1, t1, s2, t2));
                    set.set(grundy(o, s1, i + j - 1, s2, t2) ^ grundy(o, i + j + 1, t1, s2, t2));
                } else if (maps[i][j] == 'R') {
//                    set.add(grundy(o, s1, t1, s2, i - j - 1) ^ grundy(o, s1, t1, i - j + 1, t2));
                    set.set(grundy(o, s1, t1, s2, i - j - 1) ^ grundy(o, s1, t1, i - j + 1, t2));
                } else { // X
//                    set.add(grundy(o, s1, i + j - 1, s2, i - j - 1) ^
//                            grundy(o, s1, i + j - 1, i - j + 1, t2) ^
//                            grundy(o, i + j + 1, t1, s2, i - j - 1) ^
//                            grundy(o, i + j + 1, t1, i - j + 1, t2));
                    set.set(grundy(o, s1, i + j - 1, s2, i - j - 1) ^
                            grundy(o, s1, i + j - 1, i - j + 1, t2) ^
                            grundy(o, i + j + 1, t1, s2, i - j - 1) ^
                            grundy(o, i + j + 1, t1, i - j + 1, t2));
                }
            }
        }
        int res = set.nextClearBit(0);
//        int res = 0;
//        while (set.contains(res)) res++;
        return dp[o][s1 + 2][t1 + 2][s2 + m + 2][t2 + m + 2] = res;
    }

    private boolean inS(int i, int s, int t) {
        return i >= s && i <= t;
    }
}
