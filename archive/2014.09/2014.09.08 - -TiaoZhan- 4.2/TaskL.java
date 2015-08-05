package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskL {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) solve();
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int l = in.nextInt();
        int r = in.nextInt();
        int[] is = in.nextIntArray(n);
        l = (int) (l / (2 * Math.PI * r)) + 1;
        int[] xors = new int[50];
        for (int i : is) {
            int g = (int) (i / (2 * Math.PI * r) + 1) % l;
            for (int j = 0; g != 0; g >>= 1, j++) {
                xors[j] += g & 1;
            }
        }
        for (int i = 0; i < 50; i++) {
            if (xors[i] % (m + 1) != 0) {
                out.println("Alice");
                return ;
            }
        }
        out.println("Bob");
    }
}
