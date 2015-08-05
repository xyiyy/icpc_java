package main;

import com.shu_mj.ds.Hash;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: prefix
     */
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        List<String> ss = new ArrayList<String>();
        for (;;) {
            String s = in.next();
            if (s.equals(".")) break;
            ss.add(s);
        }
        StringBuilder sb = new StringBuilder();
        while (in.hasNext()) sb.append(in.next());
        String S = sb.toString();
        int n = S.length();
        int m = ss.size();
        Hash H = new Hash(Math.max(200, n));
        long[] hs = new long[m];
        for (int i = 0; i < m; i++) {
            hs[i] = H.getHash(ss.get(i).toCharArray());
        }
        long[] hS = H.build(S.toCharArray());
        boolean[] ok = new boolean[n + 1];
        ok[0] = true;
        for (int i = 0; i <= n; i++) if (ok[i]) {
            for (int j = 0; j < m; j++) {
                int end = i + ss.get(j).length();
                if (end <= n && H.get(hS, i, end) == hs[j]) {
                    ok[end] = true;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= n; i++) if (ok[i]) ans = i;
        out.println(ans);
    }
}
