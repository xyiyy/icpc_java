package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskI {
    Scanner in;
    PrintWriter out;
    private int w;
    private int h;
    private int n;
    private int[] ha;
    private int[] hb;
    private int[] hc;
    private char[][] maps;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            w = in.nextInt();
            h = in.nextInt();
            n = in.nextInt();
            if (w == 0 && h == 0 && n == 0) break;
            solve();
        }
    }

    private void solve() {
        maps = new char[h][];
        int begin = 0, end = 0;
        for (int i = 0; i < h; i++) {
            maps[i] = in.nextLine().toCharArray();
        }
        if (n == 1) {
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    char c = maps[i][j];
                    if (c >= 'a' && c <= 'c') {
                        maps[i][j] = 'a';
                    }
                    if (c >= 'A' && c <= 'C') {
                        maps[i][j] = 'A';
                    }
                }
            }
        } else if (n == 2) {
            boolean ca = false;
            for (char[] cs : maps) for (char c : cs) if (c == 'a') ca = true;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    char c = maps[i][j];
                    if (c == 'c') {
                        if (ca) maps[i][j] = 'b';
                        else maps[i][j] = 'a';
                    }
                    if (c == 'C') {
                        if (ca) maps[i][j] = 'B';
                        else maps[i][j] = 'A';
                    }
                }
            }
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                char c = maps[i][j];
                int pl = (i << 4) | j;
                if (c >= 'a' && c <= 'c') {
                    begin |= (pl << ((c - 'a') * 8));
                } else if (c >= 'A' && c <= 'C') {
                    end |= (pl << ((c - 'A') * 8));
                }
                if (c != '#' && c != ' ') {
                    maps[i][j] = ' ';
                }
            }
        }
        ha = getH(end);
        if (n == 1) {
            out.println(ha[begin]);
            return ;
        }
        hb = getH(end >> 8);
        if (n > 2) hc = getH(end >> 16);
        PriorityQueue<P> que = new PriorityQueue<P>();
        que.add(new P(hStar(begin), begin));
        BitSet vis = new BitSet(1 << (8 * n));
        while (!que.isEmpty()) {
            P p = que.poll();
            int crt = p.y;
            int cost = p.x - hStar(crt);
            if (crt == end) {
                out.println(cost);
                return ;
            }
            if (vis.get(crt)) continue;
            vis.set(crt);
            cost++;
            int ax = ((crt) & 0xff) >> 4;
            int ay = ((crt) & 0xff) & 0xf;
            int bx = ((crt >> 8) & 0xff) >> 4;
            int by = ((crt >> 8) & 0xff) & 0xf;
            if (n == 2) {
                for (int i = 0; i < 5; i++) {
                    int ix = ax + dx[i];
                    int iy = ay + dy[i];
                    if (!in(ix, iy) || maps[ix][iy] == '#') continue;
                    for (int j = 0; j < 5; j++) {
                        int jx = bx + dx[j];
                        int jy = by + dy[j];
                        if (!in(jx, jy) || maps[jx][jy] == '#') continue;
                        if (same(ax, ay, jx, jy) && same(bx, by, ix, iy)) continue;
                        if (same(ix, iy, jx, jy)) continue;
                        int st = (((jx << 4) | jy) << 8) | (ix << 4) | iy;
                        que.add(new P(cost + hStar(st), st));
                    }
                }
            } else {
                int cx = ((crt >> 16) & 0xff) >> 4;
                int cy = ((crt >> 16) & 0xff) & 0xf;
                for (int i = 0; i < 5; i++) {
                    int ix = ax + dx[i];
                    int iy = ay + dy[i];
                    if (!in(ix, iy) || maps[ix][iy] == '#') continue;
                    for (int j = 0; j < 5; j++) {
                        int jx = bx + dx[j];
                        int jy = by + dy[j];
                        if (!in(jx, jy) || maps[jx][jy] == '#') continue;
                        if (same(ax, ay, jx, jy) && same(bx, by, ix, iy)) continue;
                        if (same(ix, iy, jx, jy)) continue;
                        for (int k = 0; k < 5; k++) {
                            int kx = cx + dx[k];
                            int ky = cy + dy[k];
                            if (!in(kx, ky) || maps[kx][ky] == '#') continue;
                            if (same(ax, ay, kx, ky) && same(cx, cy, ix, iy)) continue;
                            if (same(bx, by, kx, ky) && same(cx, cy, jx, jy)) continue;
                            if (same(ix, iy, kx, ky)) continue;
                            if (same(jx, jy, kx, ky)) continue;
                            int st = (((kx << 4) | ky) << 16) | (((jx << 4) | jy) << 8) | (ix << 4) | iy;
                            que.add(new P(cost + hStar(st), st));
                        }
                    }
                }
            }
        }
    }

    private boolean same(int ax, int ay, int bx, int by) {
        return ax == bx && ay == by;
    }

    private int hStar(int s) {
        int h = ha[s & 0xff];
        if (n > 1) h = Math.max(h, hb[(s >> 8) & 0xff]);
        if (n > 2) h = Math.max(h, hc[(s >> 16) & 0xff]);
        return h;
    }

    class P implements Comparable<P> {
        int x;
        int y;

        @Override
        public int compareTo(P o) {
            return x - o.x;
        }

        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int[] dx = { 0, 0, 1, -1, 0};
    int[] dy = { 1, -1, 0, 0, 0};

    private int[] getH(int b) {
        b &= 0xff;
        int[] step = new int[1 << 8];
        Arrays.fill(step, -1);
        step[b] = 0;
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(b);
        while (!que.isEmpty()) {
            int c = que.poll();
            int cx = c >> 4;
            int cy = c & 0xf;
            for (int i = 0; i < 4; i++) {
                int x = cx + dx[i];
                int y = cy + dy[i];
                if (in(x, y) && maps[x][y] == ' ' && step[(x << 4) | y] == -1) {
                    step[(x << 4) | y] = step[c] + 1;
                    que.add((x << 4) | y);
                }
            }
        }
        return step;
    }

    private boolean in(int x, int y) {
        return x >= 0 && x < h && y >= 0 && y < w;
    }

}
