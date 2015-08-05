package main;



import com.shu_mj.ds.UnionFindSet;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task10 {
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
        int k = in.nextInt();
        E[] es = new E[m];
        int maxCost = 0;
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int c = in.nextInt();
            maxCost = Math.max(c, maxCost);
            es[i] = new E(u, v, c);
        }
        while (k-- != 0) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            if (!havePath(n, es, u, v, maxCost)) out.println("no path");
            else {
                int l = 0, r = maxCost;
                while (l < r) {
                    int mid = (l + r) / 2;
                    if (havePath(n, es, u, v, mid)) r = mid;
                    else l = mid + 1;
                }
                out.println(l);
            }
        }
    }

    private boolean havePath(int n, E[] es, int u, int v, int maxCost) {
        UnionFindSet uf = new UnionFindSet(n);
        for (E e : es) if (e.cost <= maxCost) {
            uf.union(e.u, e.v);
        }
        return uf.isSame(u, v);
    }

    class E {
        int u;
        int v;
        int cost;

        E(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
    }
}
