package main;

import com.shu_mj.ds.Hash;
import com.shu_mj.ds.Hash2;
import com.shu_mj.ds.SuffixArray;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: hidden
     */
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        StringBuilder sb = new StringBuilder();
        while (in.hasNext()) sb.append(in.next());
        String s = sb.toString();
        SuffixArray sa = new SuffixArray((s + s).toCharArray());
        Hash h = new Hash(s.length() * 2);
        long[] hs = h.build((s + s).toCharArray());
        for (int i = 0; ; i++) {
            if (sa.si[i] < n) {
                int res = sa.si[i];
                for (int j = i; j <= n * 2; j++) {
                    if (sa.si[j] >= n) break;
                    if (h.get(hs, res, res + n) != h.get(hs, sa.si[j], sa.si[j] + n)) break;
                    res = Math.min(res, sa.si[j]);
                }
                out.println(res);
                return ;
            }
        }
    }
}
