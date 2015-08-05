package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: dualpal
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
        int s = in.nextInt();
        s++;
        for (int i = 0; i < n; i++) {
            while (!fit(s)) s++;
            out.println(s);
            s++;
        }
    }

    private boolean fit(int s) {
        int c = 0;
        for (int i = 2; i <= 10; i++) {
            if (Integer.toString(s, i).equals(new StringBuilder(Integer.toString(s, i)).reverse().toString())) c++;
        }
        return c >= 2;
    }
}
