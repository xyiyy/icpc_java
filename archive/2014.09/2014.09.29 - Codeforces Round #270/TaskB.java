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
        int n = in.nextInt();
        int k = in.nextInt();
        int[] is = in.nextIntArray(n);
        for (int i = 0; i < n; i++) is[i]--;
        Arrays.sort(is);
        int ans = 0;
        int max = Algo.max(is);
        for (int d = 0; d < max; d++) {
            for (int i = 0; i < n; ) {
                if (is[i] <= d) i++;
                else {
                    ans++;
                    i += k;
                    if (i < n) ans++;
                }
            }
        }
        ans += max;
        out.println(ans);
    }
}
