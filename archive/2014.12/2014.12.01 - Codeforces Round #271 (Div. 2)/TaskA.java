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

    String ss = "qwertyuiopasdfghjkl;zxcvbnm,./";

    void run() {
        int d = in.next().charAt(0) == 'L' ? 1 : -1;
        for (char c : in.next().toCharArray()) {
            out.print(ss.charAt(ss.indexOf(c) + d));
        }
        out.println();
    }
}
