package main;

import com.shu_mj.math.Num;
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
        java.util.
        HashMap<Integer, Long> ans = new HashMap<Integer, Long>();
        HashMap<Integer, Long> crt = new HashMap<Integer, Long>();
        HashMap<Integer, Long> next = new HashMap<Integer, Long>();
        for (int i : in.nextIntArray(in.nextInt())) {
            next.clear();
            for (Map.Entry<Integer, Long> e : crt.entrySet()) {
                inc(next, Num.gcd(e.getKey(), i), e.getValue());
            }
            inc(next, i, 1);
            for (Map.Entry<Integer, Long> e : next.entrySet()) {
                inc(ans, e.getKey(), e.getValue());
            }
            { HashMap<Integer, Long> t = crt; crt = next; next = t; }
        }
        for (int i : in.nextIntArray(in.nextInt())) {
            if (!ans.containsKey(i)) out.println(0);
            else out.println(ans.get(i));
        }
    }

    private void inc(Map<Integer, Long> cnt, int k, long v) {
        if (!cnt.containsKey(k)) cnt.put(k, v);
        else cnt.put(k, cnt.get(k) + v);
    }
}
