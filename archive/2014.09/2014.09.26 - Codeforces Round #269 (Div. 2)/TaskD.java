package main;



import com.shu_mj.ds.Hash;
import com.shu_mj.ds.Hash2;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] is = in.nextIntArray(n);
        int[] js = in.nextIntArray(m);
        for (int i = n - 1; i >= 0; i--) {
            if (i > 0) is[i] -= is[i - 1];
            else is[i] = 0;
        }
        for (int i = m - 1; i >= 0; i--) {
            if (i > 0) js[i] -= js[i - 1];
            else js[i] = 0;
        }
        Hash2 h = new Hash2(Math.max(n, m));
        long[] ih = h.build(is);
        long jh = h.getHash(js, 1, m);
        int ans = 0;
        for (int i = 0; i + m <= n; i++) {
            if (h.get(ih, i + 1, i + m) == jh) ans++;
        }
        out.println(ans);
    }
}
