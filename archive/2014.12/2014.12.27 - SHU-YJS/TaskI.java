package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskI {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        while (in.hasNext()) run();
    }

    void run() {
        int[] as = in.nextIntArray(5);
        int[] bs = in.nextIntArray(5);
        Arrays.sort(as);
        Arrays.sort(bs);
        int a = 0, b = 0;
        for (int i = 0; i < 5; i++) {
            if (as[i] == bs[i]) b++;
            else if (as[i] > bs[i]) a++;
            else a--;
        }
        out.println((Math.abs(a)) + " " + b);
    }
}
