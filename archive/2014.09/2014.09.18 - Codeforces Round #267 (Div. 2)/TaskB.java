package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] is = in.nextIntArray(m);
        int x = in.nextInt();
        int cnt = 0;
        for (int i : is) {
            if (Integer.bitCount(i ^ x) <= k) cnt++;
        }
        out.println(cnt);
    }
}
