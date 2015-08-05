package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: sort3
     */
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        int cnt = 0;
        int[] cc = new int[3];
        for (int i = 0; i < n; i++) {
            is[i]--;
            cc[is[i]]++;
        }
        int[] should = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if (cc[j] > 0) {
                    should[i] = j;
                    cc[j]--;
                    break;
                }
            }
        }
        outer: for (int i = 0; i < n; i++) {
            if (should[i] == is[i]) continue;
            for (int j = i + 1; j < n; j++) {
                if (should[i] == is[j] && should[j] == is[i]) {
                    cnt++;
                    Algo.swap(is, i, j);
                    continue outer;
                }
            }
            for (int j = i + 1; j < n; j++) {
                if (should[i] == is[j] && should[j] != is[j]) {
                    cnt++;
                    Algo.swap(is, i, j);
                    break;
                }
            }
        }
        out.println(cnt);
    }
}
