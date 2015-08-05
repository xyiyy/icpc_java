package main;

import com.shu_mj.tpl.Scanner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: lgame
     */
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        Scanner din;
        try {
            din = new Scanner(new FileInputStream("lgame.dict"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
        int[] values = {
                2, 5, 4, 4, 1, 6, 5,
                5, 1, 7, 6, 3, 5, 2,
                3, 5, 7, 2, 1, 2,
                4, 6, 6, 7, 5, 7
        };
        Set<String> dic = new HashSet<String>();
        for (;;) {
            String s = din.next();
            if (s.equals(".")) break;
            dic.add(s);
        }
        char[] cs = in.next().toCharArray();
        Arrays.sort(cs);
        int max = 0;
        List<String> ss = new LinkedList<String>();
        int n = cs.length;
        for (int i = 1; i < (1 << n); i++) {
            int m = Integer.bitCount(i);
            char[] s = new char[m];
            for (int j = 0, k = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) s[k++] = cs[j];
            }
            List<String> res = calc(s, dic);
            if (res.isEmpty()) continue;
            int v = 0;
            for (char c : s) v += values[c - 'a'];
            if (v > max) {
                max = v;
                ss = res;
            } else if (v == max) {
                ss.addAll(res);
            }
        }
        out.println(max);
        Collections.sort(ss);
        for (String s : Algo.unique(ss.toArray(new String[0]))) out.println(s);
    }

    private List<String> calc(char[] cs, Set<String> dic) {
        List<String> res = new LinkedList<String>();
        int n = cs.length;
        do {
            String s = String.valueOf(cs);
            if (dic.contains(s)) {
                res.add(s);
            }
            for (int i = 0; i < n - 1; i++) {
                String l = s.substring(0, i + 1);
                String r = s.substring(i + 1);
                if (dic.contains(l) && dic.contains(r)) {
                    if (l.compareTo(r) > 0) {
                        String t = l;
                        l = r;
                        r = t;
                    }
                    res.add(l + " " + r);
                }
            }
        } while (Algo.nextPermutation(cs));
        return res;
    }
}
