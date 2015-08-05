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
        while (in.hasNext()) {
            int a = in.nextInt();
            int b = in.nextInt();
            if (a == 0 && b == 0) break;
            solve(a, b);
        }
    }

    private void solve(int a, int b) {
        if (dfs(a, b)) out.println("Stan wins");
        else out.println("Ollie wins");
    }

    private boolean dfs(int a, int b) {
        if (a > b) return dfs(b, a);
        if (b % a == 0) return true;
        if (b - a > a) return true;
        return !dfs(a, b - a);
    }
}
