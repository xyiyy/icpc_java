package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
    Scanner in;
    PrintWriter out;
    private boolean[] vis;
    private int[] is;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            solve(n);
        }
    }

    private void solve(int n) {
        is = in.nextIntArray(n);
        Arrays.sort(is);
        Algo.reverse(is);
        int sum = (int) (Algo.sum(is));
        vis = new boolean[n];
        for (int i = is[0]; ; i++) {
            if (sum % i == 0) {
                if (dfs(0, i, 0)) {
                    out.println(i);
                    return ;
                }
            }
        }
    }

    private boolean dfs(int crt, int sum, int begin) {
        if (crt > sum) return false;
        if (crt == sum) return dfs(0, sum, 0);
        int suf = 0;
        for (int i = begin; i < is.length; i++) if (!vis[i]) suf += is[i];
        if (crt != 0 && crt + suf < sum) return false;
        if (crt == 0) {
            int id = -1;
            for (int i = begin; i < is.length; i++) if (!vis[i]) { id = i; break; }
            if (id == -1) return true;
            vis[id] = true;
            if (dfs(crt + is[id], sum, id + 1)) return true;
            vis[id] = false;
            return false;
        } else {
            int last = -1;
            for (int i = begin; i < is.length; i++) if (!vis[i]) {
                vis[i] = true;
                if (is[i] != last && dfs(crt + is[i], sum, i + 1)) return true;
                vis[i] = false;
                last = is[i];
            }
            return false;
        }
    }
}
