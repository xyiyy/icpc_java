package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;

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
        int[] is = in.nextIntArray(n);
        int oddCount = 0;
        for (int i : is) if (i % 2 == 1) oddCount++;
        if (oddCount * 2 != n) {
            im();
            return ;
        }
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new V();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (is[i] % 2 == 0 ^ is[j] % 2 == 0) {
                    vs[i].add(j);
                }
            }
        }
        List<List<Integer>> cs = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) if (is[i] % 2 == 0) {
            boolean[] vis = new boolean[n];
            vis[i] = true;
            List<Integer> ls = new ArrayList<Integer>();
            ls.add(i);
            for (int u : vs[i]) dfs(is, vs, vis, u, i, ls, cs);
        }

    }

    private void dfs(int[] is, V[] vs, boolean[] vis, int crt, int des, List<Integer> ls, List<List<Integer>> cs) {
        if (is[crt] % 2 == 0 && is[crt] < is[des]) {
            return ;
        }
        ls.add(crt);
        for (int u : vs[crt]) {
            if (u == des && ls.size() > 2) {
                insert(ls, cs);
            }
            if (!vis[u]) {
                vis[u] = true;
                dfs(is, vs, vis, u, des, ls, cs);
                vis[u] = false;
            }
        }
        ls.remove(ls.size() - 1);
    }

    private void insert(List<Integer> ls, List<List<Integer>> cs) {
        for (int i : ls) {
            if (i % 2 == 0 && i < ls.get(0)) return ;
        }
        List<Integer> c = new ArrayList<Integer>();
        c.addAll(ls);
        cs.add(ls);
    }


    class V extends ArrayList<Integer> {

    }

    private void im() {
        out.println("Impossible");
    }
}
