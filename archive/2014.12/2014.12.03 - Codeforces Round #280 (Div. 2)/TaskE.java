package main;



import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
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
        int n = in.nextInt();
        int m = in.nextInt();
        int[] cnt = new int[n];
        int dx = in.nextInt();
        int dy = in.nextInt();
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            // x = dx * t
            // y = dy * t + k
            // k = y - dy * x / dx
            cnt[(int) (y - (long) dy * x % n * Num.inv(dx, n) % n + n) % n]++;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (cnt[i] > cnt[ans]) ans = i;
        }
        out.println(0 + " " + ans);
    }
}
