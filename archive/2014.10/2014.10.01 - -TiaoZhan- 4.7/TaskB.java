package main;

import com.shu_mj.math.Num;
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
        int cs = 1;
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            out.printf("Case %d: ", cs++);
            solve(n);
        }
    }

    private void solve(int n) {
        int m = in.nextInt();
        int t = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();
        char[][] maps = new char[n][];
        for (int i = 0; i < n; i++) {
            maps[i] = in.next().toCharArray();
        }
        char[][][] tcss = new char[t][p][];
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < p; j++) {
                tcss[i][j] = in.next().toCharArray();
            }
        }
        int ans = t;
        Random r = new Random();
        for (int i = 0; i < 1; i++) {
//            R = (long) (1e9 + r.nextInt((int) 1e9));
//            C = (long) (1e9 + r.nextInt((int) 1e9));
            ans = Math.min(ans, get(n, m, t, p, q, maps, tcss));
        }
        out.println(ans);
    }
    int get(int n, int m, int t, int p, int q, char[][] maps, char[][][] tcss) {
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        for (int i = 0; i < t; i++) {
            char[][] tcs = tcss[i];
            Num.inc(map, hash(tcs));
        }
        if (p > n || q > m) {
            return 0;
        }
        long[][] hash = new long[n][m];
        for (int i = 0; i < n; i++) {
            long h = 0;
            long pw = 1;
            for (int j = 0; j < q - 1; j++) {
                h = h * C + maps[i][j];
                pw = pw * C;
            }
            for (int j = q - 1; j < m; j++) {
                h = h * C + maps[i][j];
                hash[i][j] = h;
                h = h - pw * maps[i][j - q + 1];
            }
        }
        long[][] hash2 = new long[n][m];
        for (int j = q - 1; j < m; j++) {
            long h = 0;
            long pw = 1;
            for (int i = 0; i < p - 1; i++) {
                h = h * R + hash[i][j];
                pw = pw * R;
            }
            for (int i = p - 1; i < n; i++) {
                h = h * R + hash[i][j];
                hash2[i][j] = h;
                h = h - pw * hash[i - p + 1][j];
            }
        }
        int ans = 0;
        for (int i = p - 1; i < n; i++) {
            for (int j = q - 1; j < m; j++) {
                if (map.containsKey(hash2[i][j])) {
                    ans += map.get(hash2[i][j]);
                    map.remove(hash2[i][j]);
                }
            }
        }
        return ans;
    }

    long R = 931013;
    long C = 940721;
    private long hash(char[][] css) {
        long res = 0;
        for (char[] cs : css) {
            long tmp = 0;
            for (char c : cs) {
                tmp = tmp * C + c;
            }
            res = res * R + tmp;
        }
        return res;
    }
}
