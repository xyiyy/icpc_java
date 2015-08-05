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
        while (in.hasNext()) run();
    }

    void run() {
        String s = in.next().replaceAll("10", "T");
        int n = s.length();
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = c2i(s.charAt(i));
        }
        int[] cnt = new int[13];
        Arrays.fill(cnt, 4);
        for (int i : is) cnt[i]--;
        long res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < is[i]; j++) if (cnt[j] > 0) {
                cnt[j]--;
                res += shuffle(cnt);
//                Algo.debug(j, shuffle(cnt));
                res %= M;
                cnt[j]++;
            }
//            Algo.debug(i, is[i], res);
            if (cnt[is[i]] > 0) cnt[is[i]]--;
            else break;
        }
        if (Algo.sum(cnt) == 0 && n > 26) res = (res + 1) % M;
        out.println(res);
    }

    final long M = (long) (1e9 + 7);
    long[] fs = Num.factorialTable(52, M);
//    long[] rfs = Num.invTable(52, M);
    long[] rfs = new long[53];
    {
        for (int i = 1; i <= 52; i++) {
            rfs[i] = Num.inv(fs[i], M);
        }
    }
    private long shuffle(int[] cnt) {
        long res = fs[(int) Algo.sum(cnt)];
//        Algo.debug("Inner", res);
        for (int i : cnt) {
            if (i != 0) {
                res *= rfs[i];
                res %= M;
//                Algo.debug(res, i, rfs[i]);
            }
        }
//        Algo.debug(cnt);
//        Algo.debug(res);
        return res;
    }

    private int c2i(char c) {
        if (c == 'A') return 0;
        if (c >= '2' && c <= '9') return c - '1';
        if (c == 'T') return 9;
        if (c == 'J') return 10;
        if (c == 'Q') return 11;
        return 12;
    }
}
