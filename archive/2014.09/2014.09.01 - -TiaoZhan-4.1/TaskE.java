package main;

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
        Num.primeTable(10000, new ArrayList<Integer>());
        while (in.hasNext()) {
            int b = in.nextInt();
            if (b == 0) break;
            solve(b);
        }
    }

    long[][] dp = new long[101][101];
    {
        Algo.fill(dp, -1);
    }

    private void solve(int b) {
        int i = in.nextInt();
        int n = in.nextInt();
        if (dp[b][i] == -1) dp[b][i] = dfs(b, i, (long) (1e7));
        long res = dp[b][i];
        String str = String.format("%07d", res);
        out.println(str.substring(str.length() - n));
    }

    private long dfs(int b, int i, long mod) {
        if (i == 0) return 1 % mod;
        long p = Num.phi(mod);
        long r = dfs(b, i - 1, p);
        return pow(b, r, mod);
    }

    long mod(long a, long m) {
        if (a < m) return a;
        return a % m + m;
    }

    long pow(long p, long e, long mod) {
        long res = 1;
        while (e != 0) {
            if ((e & 1L) != 0) res = mod(res * p, mod);
            p = mod(p * p, mod);
            e >>= 1;
        }
        return res;
    }


    private long dfs(int b, int i, long mod, long delta) {
        Algo.debug(b, i, mod, delta);
        if (i == 0 || b == 1) return (1 + mod - delta) % mod;
        int d = (int) Num.gcd(b, mod);
        long p = Num.phi(mod);
        if (d == 1) return (dfs(b, i - 1, p, 0) + mod - delta) % mod;
        return (d * dfs(b / d, i, mod / d, 0) * d % mod * dfs(d, i, mod / d, 2 % (mod / d)) % mod + mod - delta) % mod;
    }
}
