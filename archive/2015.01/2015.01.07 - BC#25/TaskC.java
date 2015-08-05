package main;

import com.shu_mj.ds.LCA;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;
    private int clock;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        while (in.hasNext()) run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        final V[] vs = new V[n];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new V();
        }
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vs[u].add(v);
            vs[v].add(u);
        }
        LCA lca = new LCA(vs, 0);
        clock = 0;
        dfsO(vs, 0, -1);
        List<Integer>[] cs = new List[100000];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = new ArrayList<Integer>();
        }
        while (m-- > 0) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            cs[b].add(a);
        }
        for (int i = 0; i < cs.length; i++) {
            Collections.sort(cs[i], new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return vs[o1].order - vs[o2].order;
                }
            });
        }
        for (List<Integer> ls : cs) {
            int last = -1;
            for (int i : ls) {
                if (i != last) {
                    vs[i].ans++;
                    if (last != -1) {
                        vs[lca.lca(i, last)].ans--;
                    }
                }
                last = i;
            }
        }
        dfs(vs, 0, -1);
        for (int i = 0; i < n; i++) {
            out.print((i == 0 ? "" : " ") + vs[i].ans);
        }
        out.println();
    }

    void dfs(V[] vs, int u, int fa) {
        for (int v : vs[u]) if (v != fa) {
            dfs(vs, v, u);
            vs[u].ans += vs[v].ans;
        }
    }
    private void dfsO(V[] vs, int u, int fa) {
        for (int v : vs[u]) if (v != fa) {
            dfsO(vs, v, u);
        }
        vs[u].order = clock++;
    }

    class V extends LinkedList<Integer> {
        int order;
        int ans;
    }
}
