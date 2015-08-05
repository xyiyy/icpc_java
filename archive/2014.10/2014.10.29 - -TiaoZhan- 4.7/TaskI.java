package main;

import com.shu_mj.ds.Hash;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskI {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            solve();
        }
    }

    Hash H = new Hash(50000);

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] is1 = in.nextIntArray(n);
        int[] is2 = in.nextIntArray(m);
        if (k > n) {
            out.println(0);
            return ;
        }
        int res = 0;
        long[] hs1 = H.build(is1);
        long[] hs2 = H.build(is2);
        Set<Long> is2k = new HashSet<Long>();
        for (int i = 0; i + k <= m; i++) {
            is2k.add(H.get(hs2, i, i + k));
        }
        Set<Long> is2kp1 = new HashSet<Long>();
        for (int i = 0; i + k + 1 <= m; i++) {
            is2kp1.add(H.get(hs2, i, i + k + 1));
        }
        if (is2k.contains(H.get(hs1, n - k, n))) res++;
        for (int i = 0; i + k + 1 <= n; i++) {
            if (is2k.contains(H.get(hs1, i, i + k)) && !is2kp1.contains(H.get(hs1, i, i + k + 1))) res++;
        }
        out.println(res);
    }
}
