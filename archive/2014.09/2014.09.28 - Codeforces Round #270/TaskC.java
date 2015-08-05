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
        String[][] ss = new String[2][n];
        for (int i = 0; i < n; i++) {
            ss[0][i] = in.next();
            ss[1][i] = in.next();
        }
        int[] is = in.nextIntArray(n);
        String last = null;
        for (int i : is) {
            i--;
            if (last == null) last = min(ss[0][i], ss[1][i]);
            else {
                String min = min(ss[0][i], ss[1][i]);
                String max = max(ss[0][i], ss[1][i]);
                if (min.compareTo(last) > 0) last = min;
                else if (max.compareTo(last) > 0) last = max;
                else {
                    out.println("NO");
                    return ;
                }
            }
        }
        out.println("YES");
    }

    private String max(String a, String b) {
        return a.compareTo(b) > 0 ? a : b;
    }

    private String min(String a, String b) {
        return a.compareTo(b) < 0 ? a : b;
    }
}
