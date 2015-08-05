package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
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
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        char[] cs = Integer.toString(n).toCharArray();
        n = cs.length;
        for (int i = n - 1; i >= 0; ) {
            if (cs[i] == '1') {
                i--;
            } else if (i > 0 && cs[i] == '8' && cs[i - 1] == '1') {
                i -= 2;
            } else if (i > 1 && cs[i] == '8' && cs[i - 1] == '8' && cs[i - 2] == '1') {
                i -= 3;
            } else {
                out.println("NO");
                return ;
            }
        }
        out.println("YES");
    }
}
