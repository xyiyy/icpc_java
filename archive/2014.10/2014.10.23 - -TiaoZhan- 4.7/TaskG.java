package main;

import com.shu_mj.ds.SuffixArray;
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
        while (n-- != 0) solve();
    }

    private void solve() {
        String s = in.next();
        SuffixArray sa = new SuffixArray((s + s).toCharArray());
        int n = s.length();
        sa.buildHs();
        for (int i = 0; ; i++) {
            if (sa.si[i] < n) {
                int ans = sa.si[i];
                while (i < n * 2 && sa.hs[i] >= n) {
                    if (sa.si[i] < n) {
                        ans = Math.min(ans, sa.si[i]);
                    }
                    i++;
                }
                out.println(sa.si[i] + 1);
                return ;
            }
        }
    }
}
