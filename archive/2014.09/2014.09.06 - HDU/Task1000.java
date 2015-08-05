package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1000 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        new Task(in, out).run();
    }
}
class Task implements Runnable {

    private final Scanner in;
    private final PrintWriter out;

    public Task(Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        dfs(1000000);
        while (in.hasNext()) {
            out.println(in.nextInt() + in.nextInt());
        }
    }

    private void dfs(int i) {
        if (i == 0) return ;
        dfs(i - 1);
    }
}