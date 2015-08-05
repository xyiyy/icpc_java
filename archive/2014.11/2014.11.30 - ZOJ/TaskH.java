package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskH {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0) solve();
    }

    private void solve() {
        int n = in.nextInt();
        char[][] maps = new char[n][];
        for (int i = 0; i < n; i++) {
            maps[i] = in.next().toCharArray();
        }
        maps[n / 2][n / 2] = '0';
        if (!connect(maps, n)) {
            out.println(-1);
            return ;
        }
        Set<St> vis = new HashSet<St>();
        PriorityQueue<St> que = new PriorityQueue<St>();
        vis.add(new St(maps, 0, hs(maps)));
        que.add(new St(maps, 0, hs(maps)));
        while (!que.isEmpty()) {
            St crt = que.poll();
            if (end(crt)) {
                out.println(crt.cost);
                return ;
            }
            LinkedList<P> ps = new LinkedList<P>();
            go(crt.maps, ps);
            for (P p : ps) {
                char[][] m = clone(crt.maps);
                dig(m, p.x, p.y);
                St s = new St(m, crt.cost + crt.maps[p.x][p.y] - '0', hs(m));
                if (!vis.contains(s)) {
                    vis.add(s);
                    que.add(s);
                }
            }
        }
    }

    private int hs(char[][] maps) {
        int n = maps.length;
        int res = 0;
        boolean[][] vis = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!vis[i][j] && maps[i][j] == '@') {
                    res += dfsH(maps, vis, i, j, n);
                }
            }
        }
        return res;
    }

    private int dfsH(char[][] maps, boolean[][] vis, int x, int y, int n) {
        vis[x][y] = true;
        int res = 9;
        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            if (in(tx, ty, n) && !vis[tx][ty] && (maps[tx][ty] == '@' || maps[tx][ty] == '0')) {
                res = Math.min(res, dfsH(maps, vis, tx, ty, n));
            }
            if (in(tx, ty, n) && maps[tx][ty] > '0' && maps[tx][ty] <= '9') {
                res = Math.min(res, maps[tx][ty] - '0');
            }
        }
        return res;
    }

    private void dig(char[][] maps, int x, int y) {
        int n = maps.length;
        boolean[][] vis = new boolean[n][n];
        dfsAt(maps, vis, x, y, n);
    }

    private void dfsAt(char[][] maps, boolean[][] vis, int x, int y, int n) {
        vis[x][y] = true;
        maps[x][y] = '0';
        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            if (in(tx, ty, n) && (maps[tx][ty] == '@' || maps[tx][ty] == '0') && !vis[tx][ty]) {
                dfsAt(maps, vis, tx, ty, n);
            }
        }
    }

    private void go(char[][] maps, LinkedList<P> ps) {
        int n = maps.length;
        boolean[][] vis = new boolean[n][n];
        dfsZero(maps, vis, n / 2, n / 2, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (maps[i][j] > '0' && maps[i][j] <= '9' && nei(vis, i, j, n)) {
                    ps.add(new P(i, j));
                }
            }
        }
    }

    private boolean nei(boolean[][] vis, int x, int y, int n) {
        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            if (in(tx, ty, n) && vis[tx][ty]) return true;
        }
        return false;
    }

    private void dfsZero(char[][] maps, boolean[][] vis, int x, int y, int n) {
        vis[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            if (in(tx, ty, n) && (maps[tx][ty] == '0' || maps[tx][ty] == '@') && !vis[tx][ty]) {
                dfsZero(maps, vis, tx, ty, n);
            }
        }
    }

    char[][] clone(char[][] maps) {
        char[][] res = new char[maps.length][];
        for (int i = 0; i < maps.length; i++) res[i] = maps[i].clone();
        return res;
    }
    class P {
        int x;
        int y;

        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private boolean end(St crt) {
        for (char[] cs : crt.maps) {
            for (char c : cs) {
                if (c == '@') return false;
            }
        }
        return true;
    }

    private boolean connect(char[][] maps, int n) {
        boolean[][] vis = new boolean[n][n];
        dfs(maps, vis, n / 2, n / 2);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (maps[i][j] == '@' && !vis[i][j]) return false;
            }
        }
        return true;
    }

    private void dfs(char[][] maps, boolean[][] vis, int x, int y) {
        vis[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            if (in(tx, ty, vis.length) && maps[tx][ty] != 'X' && !vis[tx][ty]) {
                dfs(maps, vis, tx, ty);
            }
        }
    }
    int[] dx = { 0, 0, 1, -1 };
    int[] dy = { 1, -1, 0, 0 };
    boolean in(int x, int y, int n) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    class St implements Comparable<St> {
        char[][] maps;
        int cost;
        int h;

        St(char[][] maps, int cost, int h) {
            this.maps = maps.clone();
            for (int i = 0; i < maps.length; i++) this.maps[i] = maps[i].clone();
            this.cost = cost;
            this.h = h;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof St)) return false;
            return Arrays.deepEquals(maps, ((St) o).maps);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(maps);
        }

        @Override
        public int compareTo(St o) {
            return cost + h - o.cost - o.h;
        }
    }
}
