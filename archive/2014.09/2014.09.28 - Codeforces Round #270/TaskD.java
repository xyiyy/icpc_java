package main;

import com.shu_mj.ds.LCA;
import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskD {
    Scanner in;
    PrintWriter out;
    private int[][] mat;
    private boolean[] vis;
    private int n;
    private boolean[] ok;
    private int[] pre;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        n = in.nextInt();
        mat = in.nextIntMatrix(n, n);
        List<Integer> x = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            boolean ok = true;
            for (int j = i; j < n; j++) {
                if (i == j && mat[i][i] != 0 || i != j && mat[i][j] != mat[j][i] || i != j && mat[i][j] == 0) {
                    out.println("NO");
                    return ;
                }
                if (i != 0 && j != i && j != 0) {
                    if (mat[0][j] + mat[j][i] == mat[i][j]) ok = false;
                }
            }
            if (i != 0 && ok) {
                x.add(i);
            }
        }
        if (x.size() == 0) {
            out.println("YES");
            return ;
        }
        vis = new boolean[n];
        pre = new int[n];
        Arrays.fill(pre, -1);
        ok = new boolean[n];
        vis[0] = true;
        for (int i : x) {
            if (!dfs(i, 0)) {
                out.println("NO");
                return ;
            }
        }
        for (int i = 1; i < n; i++) if (!ok[i]) {
            out.println("NO");
            return ;
        }
        /*int[] dep = new int[n];
        for (int i = 1; i < n; i++) {
            dep[i]++;
            for (int j = 0; j < n; j++) if (j != 0 && j != i) {
                if (mat[0][j] + mat[j][i] == mat[0][i]) dep[i]++;
            }
        }
        dfs3(dep, 0);*/
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) vs[i] = new V();
        for (int i = 1; i < n; i++) if (pre[i] == -1) {
            out.println("NO");
            return ;
        } else {
            vs[i].add(pre[i]);
            vs[pre[i]].add(i);
        }
//        dfs2(vs, 0);
        /*LCA lca = new LCA(vs, 0);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int p = lca.lca(i, j);
                if (mat[p][i] + mat[p][j] != mat[i][j]) {
                    out.println("NO");
                    return ;
                }
            }
        }*/
        for (int i = 0; i < n; i++) {
            if (!dfs4(vs, i, -1, i, 0)) {
                out.println("NO");
                return ;
            }
        }
        out.println("YES");
    }

    private boolean dfs4(V[] vs, int v, int p, int i, int d) {
        if (mat[i][v] != d) return false;
        for (int u : vs[v]) if (u != p) {
            if (!dfs4(vs, u, v, i, d + mat[v][u])) return false;
        }
        return true;
    }

    private void dfs3(int[] dep, int r) {
        for (int v = 0; v < n; v++) if (dep[v] == dep[r] + 1 && mat[0][r] + mat[r][v] == mat[0][v]) {
            pre[v] = r;
            dfs3(dep, v);
        }
    }

    private void dfs2(V[] vs, int r) {
        for (int i = 1; i < n; i++) {
            if (pre[i] == r) {
                vs[r].add(i);
                vs[i].add(r);
                dfs2(vs, i);
            }
        }
    }

    class V extends ArrayList<Integer> {

    }

    private boolean dfs(int x, int p) {
        if (pre[x] == -1 || mat[0][p] > mat[0][pre[x]]) pre[x] = p;
        if (ok[x]) return true;
        vis[x] = true;
        for (int i = 0; i < n; i++) if (i != x && i != 0) {
            if (mat[0][x] + mat[x][i] == mat[0][i]) {
                if (vis[i]) return false;
                if (!dfs(i, x)) return false;
            }
        }
        vis[x] = false;
        return ok[x] = true;
    }

}
