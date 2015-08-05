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
        int last = -1;
        for (int i = 0; i < cs.length; i++) {
            if ((i != 0 || cs[i] != '9') && cs[i] > '9' - cs[i] + '0') {
                cs[i] = (char) ('9' - cs[i] + '0');
                last = i;
            }
        }
        long x = Long.parseLong(String.valueOf(cs));
        if (x == 0) {
            cs[last] = (char) ('9' - cs[last] + '0');
        }
        x = Long.parseLong(String.valueOf(cs));
        out.println(x);
    }
}
