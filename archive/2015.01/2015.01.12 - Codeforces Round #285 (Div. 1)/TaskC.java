package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;
    private int N;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        N = in.nextInt();
        int[] is = in.nextIntArray(N);
        int same = 0;
        while (same < N / 2 && is[same] == is[N - 1 - same]) same++;
        if (same == N / 2) {
            out.println((long) N * (N + 1) / 2);
            return ;
        }
        boolean[] odd = new boolean[N + 1];
        for (int i : is) odd[i] ^= true;
        int oddCnt = Algo.count(odd, true);
        if (oddCnt > 1 || oddCnt == 1 && N % 2 == 0) {
            out.println(0);
            return ;
        }
        int[] a = Arrays.copyOfRange(is, same, N - same);
        int m = a.length;
        int pre = getPre(a, m);
        Algo.reverse(a);
        int suf = getPre(a, m);
        out.println((same + 1L) * (m - pre + 1L + same + m - suf));
    }
    int getPre(int[] a, int n) {
        int l = 1, r = n + 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (fit(a, m, n)) r = m;
            else l = m + 1;
        }
        return l;
    }

    private boolean fit(int[] a, int m, int n) {
        for (int i = m; i < n / 2; i++) {
            if (a[i] != a[n - i - 1]) return false;
        }
        int[] cnt = new int[N + 1];
        for (int i = 0; i < m; i++) {
            cnt[a[i]]++;
        }
        for (int i = 0; i < m && i < n - m; i++) {
            if (cnt[a[n - i - 1]]-- == 0) return false;
        }
        return true;
    }

    final long M = 107;

}
