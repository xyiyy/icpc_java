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
        String cs = in.next();
        int n = in.nextInt();
        final long M = (long) (1e9 + 7);
        long[] ls = new long[10];
        long[] vs = new long[10];
        for (int i = 0; i < 10; i++) {
            ls[i] = 10;
            vs[i] = i;
        }
        String[] input = new String[n];
        for (int i = 0; i < n; i++) input[i] = in.next();
        while (n-- != 0) {
            String[] ss = input[n].split("-");
            int d = Integer.parseInt(ss[0]);
            String t = ss[1].substring(1);
            long ld = 1;
            long vd = 0;
            for (int i = t.length() - 1; i >= 0; i--) {
                int c = t.charAt(i) - '0';
                vd += ld * vs[c] % M;
                vd %= M;
                ld *= ls[c];
                ld %= M;
            }
            ls[d] = ld;
            vs[d] = vd;
        }
        String t = cs;
        long ld = 1;
        long vd = 0;
        for (int i = t.length() - 1; i >= 0; i--) {
            int c = t.charAt(i) - '0';
            vd += ld * vs[c] % M;
            vd %= M;
            ld *= ls[c];
            ld %= M;
        }
        out.println(vd);
    }
}
