package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
    Scanner in;
    PrintWriter out;
    private long hGoal;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int[][] goal = new int[4][8];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                goal[i][j] = i * 10 + j;
            }
        }
        hGoal = hash(goal);
        int t = in.nextInt();
        while (t-- != 0) solve();
    }

    final long B = (long) (1e9 + 7);
    private long hash(int[][] iss) {
        long res = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                res = res * B + iss[i][j];
            }
        }
        return res;
    }


    private void solve() {
        int[][] begin = new int[4][8];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                int sv = in.nextInt();
                int s = sv / 10 - 1, v = sv % 10 - 1;
                if (v == 0) {
                    begin[s][7] = i * 10 + j + 1;
                    begin[s][v] = s * 10;
                } else {
                    begin[s][v] = i * 10 + j + 1;
                }
            }
        }
        if (hash(begin) == hGoal) {
            out.println(0);
            return ;
        }
        Set<Long> vis = new HashSet<Long>();
        Queue<P> que = new LinkedList<P>();
        que.add(new P(begin, 0));
        vis.add(hash(begin));
        while (!que.isEmpty()) {
            P crtP = que.poll();
            int[][] iss = crtP.state;
            int step = crtP.step;
            for (int i = 0; i < 4; i++) {
                int[][] jss = next(iss, i);
                if (jss == null) continue;
                long h = hash(jss);
                if (!vis.contains(h)) {
                    if (h == hGoal) {
                        out.println(step + 1);
                        return;
                    }
                    vis.add(h);
                    que.add(new P(jss, step + 1));
                }
            }
        }
        out.println(-1);
    }

    private int[][] next(int[][] iss, int d) {
        int x = -1, y = -1;
        outer : for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                if (iss[i][j] == iss[d][7] - 1) {
                    x = i;
                    y = j;
                    break outer;
                }
            }
        }
        if (x == -1 || y == 6) return null;
        int[][] jss = new int[4][];
        for (int i = 0; i < 4; i++) jss[i] = iss[i].clone();
        y++;
        jss[x][y] = iss[d][7]; jss[d][7] = iss[x][y];
        return jss;
    }

    class P {
        int[][] state;
        int step;

        P(int[][] state, int step) {
            this.state = state;
            this.step = step;
        }
    }

}
