package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class D {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            out.printf("Case #%d: ", i + 1);
            solve();
        }
    }

    int k = 20;

    private void solve() {
        int n = in.nextInt();
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new V();
        }
        for (int i = 0; i < n; i++) {
            int x = in.nextInt() - 1;
            if (i != 0) {
                vs[x].add(i);
            }
        }
        int[][] dp = new int[n][k + 1];
        Algo.fill(dp, INF);
        dfs(vs, dp, 0);
        out.println(Algo.min(dp[0]));
    }

    private void dfs(V[] vs, int[][] dp, int u) {
        if (vs[u].size() == 0) {
            for (int i = 2; i <= k; i++) dp[u][i] = 1;
            dp[u][1] = 2;
        } else {
            for (int v : vs[u]) dfs(vs, dp, v);
            int[] d = new int[k + 1];
            for (int v : vs[u]) {
                for (int i = 1; i <= k; i++) {
                    d[i] += dp[v][i];
                }
            }
            for (int i = 1; i <= k; i++) {
                for (int j = 1; j <= k; j++) if (i != j) {
                    dp[u][j] = Math.min(dp[u][j], d[i] + i);
                }
            }
        }
    }

    int INF = 12341234;

    class V extends ArrayList<Integer> {

    }
}
