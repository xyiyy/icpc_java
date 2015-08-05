package main;

import com.shu_mj.ds.SuffixArray;
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
        while (in.hasNext()) {
            solve();
        }
    }

    private void solve() {
        String s1 = in.next();
        String s2 = in.next();
        int k = in.nextInt();
        SuffixArray sa = new SuffixArray((s1 + "#" + s2).toCharArray());
        sa.buildHs();
        sa.buildRMQ();
        int n1 = s1.length();
        int n2 = s2.length();
        int cnt = 0;
        for (int i = 0; i + n2 <= n1; i++) {
            if (fit(sa, n1, n2, k, i)) cnt++;
        }
        out.println(cnt);
    }

    private boolean fit(SuffixArray sa, int n1, int n2, int k, int b) {
        int len = sa.getLCP(b, n1 + 1);
        if (len >= n2) return true;
        for (int i = 0; i < k; i++) {
            len++;
            len += sa.getLCP(b + len, n1 + 1 + len);
            if (len >= n2) return true;
        }
        return false;
    }
}
