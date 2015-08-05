package main;

import com.shu_mj.ds.Hash;
import com.shu_mj.ds.Hash2;
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
        Hash2 h = new Hash2(1000000);
        int n = in.nextInt();
        int m = in.nextInt();
        long[] ss = new long[n];
        Set<Long> set = new HashSet<Long>();
        for (int i = 0; i < n; i++) {
            ss[i] = h.getHash(in.next().toCharArray());
            set.add(ss[i]);
        }
        outer: for (int i = 0; i < m; i++) {
            char[] cs = in.next().toCharArray();
            long cs0 = h.getHash(cs);
            long cs0l = cs0 >>> 32;
            long cs0r = cs0 & 0xffffffffL;
            for (int j = 0; j < cs.length; j++) {
                char ini = cs[j];
                long cs1l = cs0l - ini * h.psl[cs.length - j - 1] % h.ML;
                long cs1r = cs0r - ini * h.psr[cs.length - j - 1] % h.MR;
                for (int c = 'a'; c <= 'c'; c++) {
                    if (c != ini) {
                        long cs2l = cs1l + c * h.psl[cs.length - j - 1] % h.ML;
                        long cs2r = cs1r + c * h.psr[cs.length - j - 1] % h.MR;
                        cs2l = (cs2l % h.ML + h.ML) % h.ML;
                        cs2r = (cs2r % h.MR + h.MR) % h.MR;
                        long cs2 = (cs2l << 32) | cs2r;
                        if (set.contains(cs2)) {
                            out.println("YES");
                            continue outer;
                        }
                    }
                }
            }
            out.println("NO");
        }
    }
}
