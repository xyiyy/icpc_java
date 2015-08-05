package main;

import com.shu_mj.math.Num;
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
        Num.primeTable(100000, new ArrayList<Integer>());
        while (in.hasNext()) run();
    }

    void run() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        int m = Algo.max(is);
        int[] cnt = new int[m + 1];
        for (int i : is) {
            cnt[i]++;
        }
        int[] pCnt = new int[m + 1];
        for (int i = 2; i <= m; i++) if (cnt[i] > 0) {
            for (int j = 1; j * j <= i; j++) {
                if (i % j == 0) {
                    pCnt[j] += cnt[i];
                    if (j * j != i) pCnt[i / j] += cnt[i];
                }
            }
        }
        long res = 0;
        for (int i = 2; i <= m; i++) if (cnt[i] > 0) {
            List<Long> ps = Num.primeFactors(i);
            int num = 0;
            for (int j = 1; j < (1 << ps.size()); j++) {
                int mul = 1;
                for (int k = 0; k < ps.size(); k++) {
                    if ((j & (1 << k)) != 0) {
                        mul *= ps.get(k);
                    }
                }
                if (Integer.bitCount(j) % 2 == 1) num += pCnt[mul];
                else num -= pCnt[mul];
            }
            res += (n - num) * 1L * cnt[i];
        }
        out.println(res / 2);
    }
}
