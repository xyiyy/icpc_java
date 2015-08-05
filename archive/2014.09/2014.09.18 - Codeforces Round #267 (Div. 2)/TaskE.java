package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int m = 100000;
        out.println(m);
        for (int i = 0; i < m; i++) {
            if (i < m - 1) out.print("r ");
            else out.println("r");
        }
        out.println(1);
        out.print("r ");
        for (int i = 1; i < 500000; i++) {
            out.print('a');
        }
        out.println();
    }
}
