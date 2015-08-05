package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: sprime
     */
    Scanner in;
    PrintWriter out;
    private int n;
    private List<Integer> ls;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        n = in.nextInt();
        ls = new ArrayList<Integer>();
        dfs(0, 0);
        Collections.sort(ls);
        for (int i : ls) out.println(i);
    }

    private void dfs(int val, int dep) {
        if (dep == n) ls.add(val);
        else {
            for (int i = 0; i < 10; i++) if (dep == 0 && i == 2 || i % 2 == 1) {
                int newVal = val * 10 + i;
                if (Num.isPrime(newVal)) dfs(newVal, dep + 1);
            }
        }
    }
}
