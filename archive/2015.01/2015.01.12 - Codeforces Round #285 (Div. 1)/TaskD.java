package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = 2000;
        int m = in.nextInt();
//        BitSet[] a = new BitSet[m];
//        BitSet[] x = new BitSet[m];
        BigInteger[] a = new BigInteger[m];
        BigInteger[] x = new BigInteger[m];
        for (int i = 0; i < m; i++) {
            a[i] = in.nextBigInteger();
            x[i] = BigInteger.ZERO.setBit(i);
        }
        for (int i = 0; i < m; i++) {
            int id = -1;
            for (int j = 0; j < n; j++) if (a[i].testBit(j)) { id = j; break; }
            if (id != -1) {
                for (int j = i + 1; j < m; j++) {
                    if (a[j].testBit(id)) {
                        a[j] = a[j].xor(a[i]);
                        x[j] = x[j].xor(x[i]);
                    }
                }
                out.println(0);
            } else {
                out.print(x[i].bitCount() - 1);
                for (int j = 0; j < m; j++) if (x[i].testBit(j)) {
                    if (j != i) out.print(" " + j);
                }
                out.println();
            }
        }
    }
}
