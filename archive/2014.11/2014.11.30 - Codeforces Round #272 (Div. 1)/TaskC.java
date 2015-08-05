package main;



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
        char[] s = in.next().toCharArray();
        char[] p = in.next().toCharArray();
        int n = s.length;
        int m = p.length;
        int[] id = new int[n];
        Arrays.fill(id, -1);
        int cnt = 0;
        for (int i = 0, crt = 0; i < n; i++) {
            id[i] = cnt;
            if (s[i] == p[crt]) {
                crt++;
                if (crt == m) {
                    cnt++;
                    crt = 0;
                }
            }
        }
        List<P> ps = new ArrayList<P>();
        for (int i = 0; i < n; i++) {
            if (s[i] == p[0]) {
                int x = i;
                int y = i;
                int crt = 0;
                for (; crt < m; crt++) {
                    while (y < n && s[y] != p[crt]) y++;
                    if (y == n) break;
                    y++;
                }
                if (crt == m) {
                    ps.add(new P(x, y - 1));
                }
            }
        }
        int pn = ps.size();
        int[] is = new int[cnt];
        int[][] dp = new int[cnt][pn];
//        Algo.debug(ps);
        int[][] dp2 = new int[cnt][pn];
        Algo.fill(dp, 12341234);
        int[][] pre = new int[cnt][pn];
        Algo.fill(pre, -1);
        for (int i = 0; i < pn; i++) {
            dp[0][i] = ps.get(i).y - ps.get(i).x + 1;
            dp2[0][i] = i;
            if (i > 0) {
                if (dp[0][i] > dp[0][i - 1]) {
                    dp[0][i] = dp[0][i - 1];
                    dp2[0][i] = dp2[0][i - 1];
                }
            }
        }
        for (int i = 1; i < cnt; i++) {
            int gid = 0;
            for (int j = i, g = 0; j < pn; j++) {
//                while (ps.get(g).y < ps.get(j).x) {
//                    if (dp[i - 1][gid] > dp[i - 1][g]) gid = g;
//                    g++;
//                }
                int up = Algo.lowerBound(ps, new P(0, ps.get(j).x)) - 1;
                if (up >= 0) {
                    dp[i][j] = dp[i - 1][up] + ps.get(j).y - ps.get(j).x + 1;
                    dp2[i][j] = j;
                    pre[i][j] = up;
                }
                if (j > 0) {
                    if (dp[i][j] > dp[i][j - 1]) {
                        dp[i][j] = dp[i][j - 1];
                        dp2[i][j] = dp2[i][j - 1];
                    }
                }
//                for (int k = 0; ps.get(k).y < ps.get(j).x; k++) {
//                    int tmp = dp[i - 1][k] + ps.get(j).y - ps.get(j).x + 1;
//                    if (tmp < dp[i][j]) {
//                        dp[i][j] = tmp;
//                        pre[i][j] = k;
//                    }
//                }
            }
        }
//        Algo.debug(cnt, dp[cnt - 1]);
        int first = 0;
        for (int i = 0; i < pn; i++) {
            if (dp[cnt - 1][i] < dp[cnt - 1][first]) first = i;
        }
        for (int i = cnt - 1; i >= 0; i--) {
            first = dp2[i][first];
            is[i] = ps.get(first).y - ps.get(first).x + 1;
            first = pre[i][first];
        }
        int[] ans = new int[n + 1];
        for (int i = 1; i <= cnt * m; i++) {
            ans[i] = i / m;
        }
        Arrays.sort(is);
        int sum = (int) Algo.sum(is);
        int delta = sum - cnt * m;
        for (int i = cnt * m; i <= n - delta; i++) {
            ans[i] = cnt;
        }
        for (int i = 0; i < cnt; i++) is[i] -= m;
//        Algo.debug(is);
        for (int i = 0, crt = n; i < cnt; i++) {
            int val = calc(dp, dp2, pre, ps, pn, i + 1) - m * (i + 1);
//            Algo.debug(i, val);
            while (n - crt < val) {
                ans[crt--] = i;
            }
        }
        for (int i = n; i >= 0; i--) {
            out.print(ans[i] + " ");
        }
        out.println();
    }

    private int calc(int[][] dp, int[][] dp2, int[][] pre, List<P> ps, int pn, int cnt) {
        int first = 0;
        for (int i = 0; i < pn; i++) {
            if (dp[cnt - 1][i] < dp[cnt - 1][first]) first = i;
        }
        int sum = 0;
        for (int i = cnt - 1; i >= 0; i--) {
            first = dp2[i][first];
            sum += ps.get(first).y - ps.get(first).x + 1;
            first = pre[i][first];
        }
        return sum;
    }

    class P implements Comparable<P> {
        int x;
        int y;

        P(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(P o) {
            return y - o.y;
        }

        @Override
        public String toString() {
            return "P{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
