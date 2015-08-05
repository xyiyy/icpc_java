package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] stack = new int[n];
        int top = 0;
        long ans = 0;
        for (int i : in.nextIntArray(n)) {
            while (top > 0 && i >= stack[top - 1]) top--;
            ans += top;
            stack[top++] = i;
        }
        out.println(ans);
    }
}
