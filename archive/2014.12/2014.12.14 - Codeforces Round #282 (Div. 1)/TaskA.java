package main;

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
        char[] cs = in.next().toCharArray();
        int n = cs.length;
        int lc = Algo.count(cs, '(');
        int rc = Algo.count(cs, ')');
        int sc = Algo.count(cs, '#');
        if (rc + sc > lc) {
            out.println(-1);
            return ;
        }
        int last = 0;
        for (int i = 0; i < n; i++) if (cs[i] == '#') last = i;
        int lastC = lc - rc - sc;

        int crt = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '(') crt--;
            else crt++;
            if (i == last) crt += lastC;
            if (crt > 0) {
                out.println(-1);
                return ;
            }
        }
        for (int i = 0; i < sc - 1; i++) out.println(1);
        out.println(1 + lastC);
    }
}
