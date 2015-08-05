package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
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
            out.println();
        }
    }

    void solve(int n) {
        int[] v = new int[n];
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = in.nextInt();
            r[i] = in.nextInt();
        }
        int M = 1009;
        for (int i = 0; i < n; i++) {
            out.println("DUP");
            int a = 1;
            for (int j = 0; j < n; j++) if (j != i) a *= v[i] - v[j];
            a %= M;
            a += M;
            a %= M;
            a = r[i] * BigInteger.valueOf(a).modInverse(BigInteger.valueOf(M)).intValue() % M;
            for (int j = 0; j < n; j++) if (j != i) {
                pushXSubV(v[j]);
                out.println("SWP");
            }
            pushXSubV((v[i] - 1 + M) % M);

            out.println("SWP");

            out.println("POP");

            out.println("NUM " + a);

            for (int j = 0; j < n; j++) {
                out.println("MUL");
                out.println("NUM " + M);
                out.println("MOD");
            }

            out.println("SWP");
        }
        out.println("POP");
        for (int i = 0; i < n - 1; i++) {
            out.println("ADD");
        }

        out.println("NUM " + M);
        out.println("MOD");
        out.println("NUM " + M);
        out.println("ADD");
        out.println("NUM " + M);
        out.println("MOD");
        out.println("END");
    }

    private void pushXSubV(int v) {
//		out.println();
//		out.println("Push (x - " + v + ")");
        out.println("DUP");
        out.println("NUM " + v);
        out.println("SUB");
//		out.println();
    }

}
