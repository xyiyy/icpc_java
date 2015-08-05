package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1882 {
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
        long k = in.nextLong();
        // Aij = 2 * i * j * 3 * i * 3 * j + 3
        long l = 0, r = 123451234512345L;
        while (l < r) {
            long mid = (l + r) / 2;
            if (count(n, m, mid) < k) l = mid + 1;
            else r = mid;
        }
        out.println(l - 1);
    }

    private long count(int n, int m, long x) {
        long cnt = 0;
        for (int i = 1; i <= n; i++) {
            int l = 1, r = m + 1;
            while (l < r) {
                int j = (l + r) / 2;
                long A = 2L * i * j + 3 * i + 3 * j + 3;
                if (A < x) l = j + 1;
                else r = j;
            }
            cnt += l - 1;
        }
        return cnt;
    }
}
