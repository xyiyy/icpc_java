package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
    Scanner in;
    PrintWriter out;
    private int[][][] rot;

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
        char[][][][] begin = get();
        char[][][][] end = get();
        boolean[][][] sm = new boolean[3][3][4];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 4; j++) {
                sm[i / 3][i % 3][j] = in.nextInt() == 1;
            }
        }
        rot = new int[3][3][1 << 18];
        Algo.fill(rot, -1);
        int h = hash(end, copy(begin));
        out.println(bfs(sm, h & 0xfffffccf));
    }

    private char[][][][] copy(char[][][][] begin) {
        char[][][][] c = new char[3][3][8][8];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int u = 0; u < 8; u++) {
                    for (int v = 0; v < 8; v++) {
                        c[i][j][u][v] = begin[i][j][u][v];
                    }
                }
            }
        }
        return c;
    }

    private int bfs(boolean[][][] sm, int h) {
        int[] step = new int[1 << 18];
        Arrays.fill(step, -1);
        step[0] = 0;
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(0);
        while (!que.isEmpty()) {
            int crt = que.poll();
            if ((crt & 0xfffffccf) == h) return step[crt];
            int[][] iss = null;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int st = rot[i][j][crt];
                    if (st == -1) {
                        boolean[] b = new boolean[9];
                        if (iss == null) iss = decode(crt);
                        rot(iss, sm, i, j, b);
                        int sts = 0;
                        int ts = 0;
                        for (int u = 0; u < 3; u++) {
                            for (int v = 0; v < 3; v++) {
                                int s = iss[u][v];
                                int t = iss[u][v];
                                if (b[u * 3 + v]) {
                                    if ((u + v - i - j) % 2 != 0) {
                                        s = (s + 1) % 4;
                                        t = (t + 3) % 4;
                                    } else {
                                        s = (s + 3) % 4;
                                        t = (t + 1) % 4;
                                    }
                                }
                                sts = sts * 4 + s;
                                ts = ts * 4 + t;
                            }
                        }
                        for (int u = 0; u < 3; u++) {
                            for (int v = 0; v < 3; v++) if (b[u * 3 + v]) {
                                rot[u][v][crt] = ((u + v - i - j) % 2 == 0 ? sts : ts);
                            }
                        }
                        st = rot[i][j][crt];
                    }
                    if (step[st] == -1) {
                        step[st] = step[crt] + 1;
                        que.add(st);
                    }
                }
            }
        }
        return -1;
    }

    int[] dx = { 0, -1, 0, 1 };
    int[] dy = { -1, 0, 1, 0 };
    int[] rev = { 2, 3, 0, 1 };
    private void rot(int[][] iss, boolean[][][] sm, int i, int j, boolean[] vis) {
        vis[i * 3 + j] = true;
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x >= 0 && y >= 0 && x < 3 && y < 3 && sm[i][j][(k + iss[i][j]) % 4] && sm[x][y][(rev[k] + iss[x][y]) % 4] && !vis[x * 3 + y]) {
                rot(iss, sm, x, y, vis);
            }
        }
    }

    private int[][] decode(int crt) {
        int[][] iss = new int[3][3];
        for (int i = 2; i >= 0; i--) {
            for (int j = 2; j >= 0; j--) {
                iss[i][j] = crt % 4;
                crt /= 4;
            }
        }
        return iss;
    }

    private int hash(char[][][][] end, char[][][][] begin) {
        int r = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 4; k++) {
                    if (eq(begin[i][j], end[i][j])) {
                        r = r * 4 + k;
                        break;
                    }
                    rot(begin[i][j]);
                }
            }
        }
        return r;
    }

    private void rot(char[][] c) {
        int n = c.length;
        char[][] a = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[n - j - 1][i] = c[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = a[i][j];
            }
        }
    }

    private boolean eq(char[][] a, char[][] b) {
        for (int i = 0; i < a.length; i++) {
            if (!Arrays.equals(a[i], b[i])) return false;
        }
        return true;
    }

    private char[][][][] get() {
        char[][][][] r = new char[3][3][8][];
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 3; j++) {
                r[i / 8][j][i % 8] = in.next().toCharArray();
            }
        }
        return r;
    }
}
