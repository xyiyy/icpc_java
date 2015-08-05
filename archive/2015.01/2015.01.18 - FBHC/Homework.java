package main;



import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Homework {
    Scanner in;
    PrintWriter out;
    private int[] cnt;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int N = 10000000;
        cnt = new int[N + 1];
        List<Integer> ps = new ArrayList<Integer>();
        Num.primeTable(N, ps);
        for (int p : ps) {
            for (int i = p; i <= N; i += p) {
                cnt[i]++;
            }
        }
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            out.printf("Case #%d: ", i + 1);
            solve();
        }
    }

    private void solve() {
        int a = in.nextInt();
        int b = in.nextInt();
        int k = in.nextInt();
        if (k > b) {
            out.println(0);
            return ;
        }
        int res = 0;
        for (int i = a; i <= b; i++) {
            if (cnt[i] == k) res++;
        }
        out.println(res);
    }
}
