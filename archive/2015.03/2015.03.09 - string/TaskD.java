package main;

import com.shu_mj.ds.Hash;
import com.shu_mj.ds.Hash2;
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
        int n = in.nextInt();
        for (int i = 0; i < n; i++) run();
    }

    Hash2 h = new Hash2(1000);

    void run() {
        char[] cs = in.next().toCharArray();
        int m = cs.length;
        int n = in.nextInt();
        char[][] css = in.nextCharMap(n);
        long[] hss = new long[n];
        for (int i = 0; i < n; i++) {
            char[] t = css[i].clone();
            if (t.length > 1) Arrays.sort(t, 1, t.length - 1);
            hss[i] = h.getHash(t);
        }
        Map<Long, Integer> index = new HashMap<Long, Integer>();
        for (int i = 0; i < n; i++) {
            if (index.containsKey(hss[i])) index.put(hss[i], -1);
            else index.put(hss[i], i);
        }
        int[] dp = new int[m + 1];
        dp[0] = 1;
        Set<Integer> lenSet = new TreeSet<Integer>();
        for (char[] t : css) lenSet.add(t.length);
        int[] lens = Algo.unBox(lenSet.toArray(new Integer[0]));
        int[] pre = new int[m + 1];
        for (int i = 0; i < m; i++) if (dp[i] != 0) {
            for (int l : lens) {
                if (i + l > m) break;
                char[] t = Arrays.copyOfRange(cs, i, i + l);
                if (t.length > 1) Arrays.sort(t, 1, t.length - 1);
                long ht = h.getHash(t);
                if (index.containsKey(ht)) {
                    if (index.get(ht) == -1) {
                        dp[i + l] += 2;
                    } else {
                        dp[i + l] += dp[i];
                        if (dp[i + l] > 2) dp[i + l] = 2;
                        pre[i + l] = index.get(ht);
                    }
                }
            }
        }
//        Algo.debug(dp);
//        Algo.debug(pre);
        if (dp[m] == 0) {
            out.println("impossible");
        } else if (dp[m] > 1) {
            out.println("ambiguous");
        } else {
            int crt = m;
            List<Integer> ss = new ArrayList<Integer>();
            while (crt > 0) {
                ss.add(pre[crt]);
                crt = crt - css[pre[crt]].length;
            }
            boolean f = true;
            for (int i = ss.size() - 1; i >= 0; i--) {
                if (f) f = false; else out.print(" ");
                out.print(css[ss.get(i)]);
            }
            out.println();
        }
    }
}
