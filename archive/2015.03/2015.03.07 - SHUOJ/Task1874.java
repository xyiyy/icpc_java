package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1874 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        while (in.hasNext()) run();
    }

    long M = (long) (1e9 + 7);
    int N = (int) (1e4);

    void run() {
        long a = in.nextInt();
        long b = in.nextInt();
        long c = in.nextInt();
        if (b > a) {
            out.println(0);
            return ;
        }
        long res = Num.combination((int) a, (int) b, M);
        res *= Num.pow(c, b, M);
        res %= M;
        out.println(res);
    }
}
