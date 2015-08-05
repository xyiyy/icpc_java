package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskK {
    Scanner in;
    PrintWriter out;
    private int minAns;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0) {
            solve();
        }
    }

    int[] dx = { 0, 0, 1, -1 };
    int[] dy = { 1, -1, 0, 0 };
    int[] dd = { 1, -1, 4, -4 };
    char[] dc = { 'R', 'L', 'D', 'U' };
    int[] inv = { 1, 0, 3, 2 };
    int[] goal = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0 };
    long gH = hash(goal);
    char[] path = new char[55];

    private void solve() {
        int[] is = in.nextIntArray(16);
        int pos = getZero(is);
        if (reverse(is)) for (int i = 0; i <= 45; i++) {
            minAns = i;
            if (dfs(0, pos, is, -1)) {
                for (int j = 0; j < i; j++) {
                    out.print(path[j]);
                }
                out.println();
                return ;
            }
        }
        out.println("This puzzle is not solvable.");
    }
    boolean reverse(int[] is) {
        int res = 0;
        int zero = 0;
        for (int i = 0; i < 16; i++) {
            if(is[i] == 0) zero = i;
            for(int j = i + 1; j < 16; j++) {
                if(is[i] > is[j]) res++;
            }
        }
        int x = zero / 4;
        int y = zero % 4;
        res += Math.abs(x - 3) + Math.abs(y - 3);
        return res % 2 == 1;
    }

    private boolean dfs(int dep, int pos, int[] is, int not) {
        if (dep + hStar(is) > minAns) return false;
        if (Arrays.equals(is, goal)) return true;
        int y = pos & 0x3;
        for (int i = 0; i < 4; i++) if (i != not) {
            int ty = y + dy[i];
            int np = pos + dd[i];
            if (ty >= 0 && ty < 4 && np >= 0 && np < 16) {
                { int t = is[pos]; is[pos] = is[np]; is[np] = t; }
                path[dep] = dc[i];
                if (dfs(dep + 1, np, is, inv[i])) return true;
                { int t = is[pos]; is[pos] = is[np]; is[np] = t; }
            }
        }
        return false;
    }

    private void print(Map<Long, P> path, P p) {
        if (p.dir == '0') return ;
        print(path, path.get(p.pre));
        out.print(p.dir);
    }

    private int getZero(int[] is) {
        for (int i = 0; i < 16; i++) if (is[i] == 0) return i;
        return -1;
    }

    class P {
        long pre;
        char dir;
        int cost;

        P(long pre, char dir, int cost) {
            this.pre = pre;
            this.dir = dir;
            this.cost = cost;
        }
    }
    class State implements Comparable<State> {
        int crt;
        int[] is;
        int cost;

        State(int crt, int[] is, int cost) {
            this.crt = crt;
            this.is = is;
            this.cost = cost;
        }

        @Override
        public int compareTo(State o) {
            return cost - o.cost;
        }
    }

    int[] rGoal = { 15, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
    private int hStar(int[] is) {
        int res = 0;
        for (int i = 0; i < 16; i++) {
            res += delta(i, rGoal[is[i]]);
        }
        return 0;
    }

    private int delta(int a, int b) {
        return Math.abs(a / 4 - b / 4) + Math.abs(a % 4 - b % 4);
    }

    private long hash(int[] is) {
        long h = 0;
        for (int i : is) h = h * 1000000007 + i;
        return h;
    }
}
