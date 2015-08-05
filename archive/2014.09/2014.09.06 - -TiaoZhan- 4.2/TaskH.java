package main;

import com.shu_mj.math.Num;
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
        boolean[] bs = new boolean[101];
        for (int i : in.nextIntArray(n)) {
            bs[i] ^= true;
        }
        for (int i = 1; i <= 100; i++) if (bs[i]) {
            out.println(1);
            return ;
        }
        out.println(0);
    }

}
