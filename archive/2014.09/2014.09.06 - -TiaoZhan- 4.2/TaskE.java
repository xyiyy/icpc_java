package main;

import com.shu_mj.datetime.DateTime;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        boolean[] win = new boolean[1100000];
        for (int y = 2001; y >= 1900; y--) {
            for (int m = 12; m >= 1; m--) {
                for (int d = 31; d >= 1; d--) if (legal(y, m, d)) {
                    int ny = y;
                    int nm = m + 1;
                    int nd = d;
                    if (nm == 13) {
                        nm = 1;
                        ny++;
                    }
                    if (legal(ny, nm, nd) && !win[toi(ny, nm, nd)]) {
                        win[toi(y, m, d)] = true;
                        continue;
                    }
                    int[] ns = DateTime.nextDay(y, m, d);
                    ny = ns[0]; nm = ns[1]; nd = ns[2];
                    if (legal(ny, nm, nd) && !win[toi(ny, nm, nd)]) {
                        win[toi(y, m, d)] = true;
                    }
                }
            }
        }
        while (t-- != 0) {
            int y = in.nextInt();
            int m = in.nextInt();
            int d = in.nextInt();
            out.println(win[toi(y, m, d)] ? "YES" : "NO");
        }
    }

    boolean legal(int y, int m, int d) {
        if (y > 2001) return false;
        if (y == 2001 && m > 11) return false;
        if (y == 2001 && m == 11 && d > 4) return false;
        return d <= DateTime.ds[m] || d <= 29 && m == 2 && DateTime.isLeapYear(y);
    }

    final int DELTA = DateTime.days(1900, 1, 1);
    int toi(int year, int month, int day) {
        return DateTime.days(year, month, day) - DELTA;
    }

}
