package main;

import com.shu_mj.ds.SuffixArray;
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
        int t = in.nextInt();
        while (t-- != 0) solve();
    }

    private void solve() {
        String S = in.nextLine();
        String T = in.nextLine();
        String ST = S + "#" + T;
        SuffixArray sa = new SuffixArray(ST.toCharArray());
        sa.buildHs();
        int ans = 0;
        for (int i = 0; i < ST.length(); i++) {
            if ((sa.si[i] < S.length()) ^ (sa.si[i + 1] < S.length())) {
                ans = Math.max(ans, sa.hs[i]);
            }
        }
        out.printf("Nejdelsi spolecny retezec ma delku %d.%n", ans);
    }
}
