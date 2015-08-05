package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskH {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) solve();
    }

    void solve() {
        int n = in.nextInt();
        int[] id = in.nextIntArray(n + 1);
        int[] is = new int[n + 1];
        Arrays.fill(is, -1);
        for (int i = n; i >= 0; i--) if (is[i] == -1) {
            int c = Integer.highestOneBit(i);
            for (int j = c, k = c - 1; j <= n && k >=0 && is[j] == -1 && is[k] == -1; j++, k--) {
                is[j] = k;
                is[k] = j;
            }
        }
        if (is[0] == -1) is[0] = 0;
        long ans = 0;
        for (int i = 0; i <= n; i++) {
            ans += id[i] ^ is[id[i]];
        }
        out.println(ans);
        for (int i = 0; i <= n; i++) {
            out.print(is[id[i]]);
            if (i == n) out.println();
            else out.print(' ');
        }
    }
}
