package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskH {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        while (in.hasNext()) run();
    }

    void run() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        Arrays.sort(is);
        boolean f = true;
        for (int i : is) {
            if (f) f = false; else out.print(" ");
            out.print(i);
        }
        out.println();
    }
}
