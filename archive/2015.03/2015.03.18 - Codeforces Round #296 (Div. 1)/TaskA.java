package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int w = in.nextInt();
        int h = in.nextInt();
        int n = in.nextInt();
        TreeMap<Integer, Integer> ws = new TreeMap<Integer, Integer>();
        TreeMap<Integer, Integer> hs = new TreeMap<Integer, Integer>();
        TreeMap<Integer, Integer> wv = new TreeMap<Integer, Integer>();
        TreeMap<Integer, Integer> hv = new TreeMap<Integer, Integer>();
        ws.put(0, w);
        hs.put(0, h);
        Num.inc(wv, w);
        Num.inc(hv, h);
        while (n-- != 0) {
            if (in.next().charAt(0) == 'V') {
                int x = in.nextInt();
                int id = ws.lowerKey(x);
                int old = ws.get(id);
                int n1 = x - id;
                int n2 = old - n1;
                Num.dec(wv, old);
                Num.inc(wv, n1);
                Num.inc(wv, n2);
                ws.put(id, n1);
                ws.put(x, n2);
            } else {
                int x = in.nextInt();
                int id = hs.lowerKey(x);
                int old = hs.get(id);
                int n1 = x - id;
                int n2 = old - n1;
                Num.dec(hv, old);
                Num.inc(hv, n1);
                Num.inc(hv, n2);
                hs.put(id, n1);
                hs.put(x, n2);
            }
//            Algo.debug(ws);
//            Algo.debug(hs);
//            Algo.debug(wv);
//            Algo.debug(hv);
            int x = wv.lastKey();
            int y = hv.lastKey();
            out.println(1L * x * y);
        }
    }
}
