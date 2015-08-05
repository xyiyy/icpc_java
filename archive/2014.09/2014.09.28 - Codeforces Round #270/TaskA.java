package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        Num.primeTable(n, new ArrayList<Integer>());
        for (int i = 4; i <= n; i++) {
            if (!Num.isPrime[i] && !Num.isPrime[n - i]) {
                out.println(i + " " + (n - i));
                return ;
            }
        }
    }
}
