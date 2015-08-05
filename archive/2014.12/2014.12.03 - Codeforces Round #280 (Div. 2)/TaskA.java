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
        int n = in.nextInt();
        for (int i = 1, s = 0, t = 0; ; i++) {
            s += i;
            t += s;
            if (t > n) {
                out.println(i - 1);
                return ;
            }
        }
    }
}
