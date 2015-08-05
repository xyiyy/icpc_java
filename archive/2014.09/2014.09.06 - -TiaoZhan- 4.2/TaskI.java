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
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            solve(n);
        }
    }

    private void solve(int n) {
        int[] is = in.nextIntArray(n);
        int s = 0;
        for (int i : is) s ^= i;
        if (s == 0) out.println(0);
        else {
            int c = 0;
            for (int i : is) if (i > (i ^ s)) c++;
            out.println(c);
        }
    }
}
