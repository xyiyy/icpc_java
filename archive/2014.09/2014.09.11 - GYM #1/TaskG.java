package main;

import com.shu_mj.ds.UnionFindSet;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
    Scanner in;
    PrintWriter out;
    private int[] pre;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        UnionFindSet uf = new UnionFindSet(n);
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextInt(), in.nextInt());
        }
        int m = in.nextInt();
        pre = new int[n];
        for (int i = 0; i < n; i++) {
            pre[i] = i;
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            union(u, v);
        }
        int[] dis = new int[n];
        int[] disId = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) if (isSame(i, 0)) {
            for (int j = 0; j < n; j++) if (!isSame(j, 0)) {
                int d = dis(ps[i], ps[j]);
                if (disId[j] == -1 || dis[j] > d) {
                    disId[j] = i;
                    dis[j] = d;
                }
            }
        }
        for (;;) {
            int id = -1;
            for (int i = 0; i < n; i++) if (!isSame(i, 0)) {
                if (id == -1 || dis[i] < dis[id]) id = i;
            }
            if (id == -1) break;
            out.println((id + 1) + " " + (disId[id] + 1));
            for (int i = 0; i < n; i++) if (isSame(i, id)) {
                for (int j = 0; j < n; j++) if (!isSame(j, 0) && !isSame(j, id)) {
                    int d = dis(ps[i], ps[j]);
                    if (dis[j] > d) {
                        disId[j] = i;
                        dis[j] = d;
                    }
                }
            }
            union(id, 0);
        }
        out.println();
    }

    private boolean isSame(int u, int v) {
        return find(u) == find(v);
    }

    private void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u != v) {
            if (u > v) { int t = u; u = v; v = t; }
            pre[u] = v;
        }
    }

    private int find(int u) {
        if (pre[u] != u) {
            pre[u] = find(pre[u]);
        }
        return pre[u];
    }

    private int dis(P p, P q) {
        return (p.x - q.x) * (p.x - q.x) + (p.y - q.y) * (p.y - q.y);
    }

    class P {
        int x;
        int y;

        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
