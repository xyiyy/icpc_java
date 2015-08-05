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
        long n = in.nextInt();
        long a = in.nextInt();
        long b = in.nextInt();
        if (a * b >= n * 6) {
            out.println(a * b);
            out.println(a + " " + b);
            return ;
        }
        boolean rev = false;
        if (a > b) { long t = a; a = b; b = t; rev = true; }
        long ans = a * b - n * 6;
        long ansA = -1, ansB = -1;
        for (long i = a; i * b <= n * 6 && i < a + 1e7; i++) {
            if (n * 6 % i == 0) {
                a = i;
                b = n * 6 / a;
                out.println(a * b);
                if (rev) { long t = a; a = b; b = t; }
                out.println(a + " " + b);
                return ;
            }
            if (ans < 0 || ans > (n * 6 + i - 1) / i * i) {
                ansA = i;
                ansB = (n * 6 + i - 1) / i;
                ans = ansA * ansB;
            }
        }
        a = ansA; b = ansB;
        out.println(ansA * ansB);
        if (rev) { long t = a; a = b; b = t; }
        out.println(a + " " + b);
    }
}
