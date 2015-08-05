package main;



import com.shu_mj.ds.LongBIT;
import com.shu_mj.ds.LongBITMod;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        long M = (long) (1e9 + 7);
        int max = Algo.max(is);
        LongBITMod val = new LongBITMod(max + 1, M);
        long[] dp = new long[max + 1];
        for (int i = 0; i < n; i++) {
            long s = val.sum(0, is[i] + 1);
            val.add(is[i], (s * is[i] + is[i] + M - dp[is[i]]) % M);
            dp[is[i]] = val.sum(is[i], is[i] + 1);
        }
        out.println(val.sum(0, max + 1));
    }
}
