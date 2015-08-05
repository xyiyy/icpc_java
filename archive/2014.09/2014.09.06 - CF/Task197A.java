package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task197A {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int a = in.nextInt();
        int b = in.nextInt();
        int r = in.nextInt();
        if (r + r <= a && r + r <= b) out.println("First");
        else out.println("Second");
    }
}
