package main;







import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;

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
            solve4();
        }
    }

    int[] dx = { 0, 0, 1, -1 };
    int[] dy = { 1, -1, 0, 0 };
    int[] dd = { 1, -1, 4, -4 };
    char[] dc = { 'R', 'L', 'D', 'U' };
    int[] inv = { 1, 0, 3, 2 };
    char[] path = new char[50];
    private void solve() {
        int[] is = in.nextIntArray(16);
        int pos = -1;
        for (int i = 0; i < 16; i++) {
            is[i]--;
            if (is[i] < 0) {
                is[i] = 15;
                pos = i;
            }
        }
        if (can(is)) {
            long begin = encode(is);
            for (int i = 0; i <= 45; i++) {
                minAns = i;
                if (dfs(0, begin, pos, -1)) {
                    for (int j = 0; j < i; j++) {
                        out.print(path[j]);
                    }
                    out.println();
                    return ;
                }
            }
        }
        out.println("This puzzle is not solvable.");
    }
    private void solve3() {
        int[] is = in.nextIntArray(16);
        int pos = -1;
        for (int i = 0; i < 16; i++) {
            is[i]--;
            if (is[i] < 0) {
                is[i] = 15;
                pos = i;
            }
        }
        if (can(is)) {
            for (int i = 0; i <= 45; i++) {
                minAns = i;
                if (dfs3(0, is, pos, -1)) {
                    for (int j = 0; j < i; j++) {
                        out.print(path[j]);
                    }
                    out.println();
                    return ;
                }
            }
        }
        out.println("This puzzle is not solvable.");
    }


    private boolean dfs3(int dep, int[] is, int pos, int not) {
        if (goal(is)) return true;
        if (dep + hStar3(is) > minAns) return false;
        int x = pos / 4;
        int y = pos % 4;
        for (int i = 0; i < 4; i++) if (i != not) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < 4 && ny < 4) {
                int nPos = nx * 4 + ny;
                path[dep] = dc[i];
                Algo.swap(is, pos, nPos);
                if (dfs3(dep + 1, is, nPos, inv[i])) return true;
                Algo.swap(is, pos, nPos);
            }
        }
        return false;
    }

    private int hStar3(int[] is) {
        int res = 0;
        for (int i = 0; i < 16; i++) {
            int v = is[i];
            if (v != 15) res += dis[v][i];
        }
        return res;
    }

    private boolean goal(int[] is) {
        for (int i = 0; i < 16; i++) if (is[i] != i) return false;
        return true;
    }

    private boolean dfs(int dep, long st, int pos, int not) {
        if (st == goal) return true;
        if (dep + hStar(st) > minAns) return false;
        int x = pos / 4;
        int y = pos % 4;
        for (int i = 0; i < 4; i++) if (i != not) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < 4 && ny < 4) {
                int nPos = nx * 4 + ny;
                path[dep] = dc[i];
                if (dfs(dep + 1, swap(st, pos, nPos), nPos, inv[i])) return true;
            }
        }
        return false;
    }

    private void solve2() {
        int[] is = in.nextIntArray(16);
        int pos = -1;
        for (int i = 0; i < 16; i++) {
            is[i]--;
            if (is[i] < 0) {
                is[i] = 15;
                pos = i;
            }
        }
        if (can(is)) {
            PriorityQueue<State> open = new PriorityQueue<State>();
            Map<Long, P> closed = new HashMap<Long, P>();
            open.add(new State(hStar(encode(is)), pos, 0, encode(is)));
            closed.put(encode(is), new P(0, 0, ' '));
            while (!open.isEmpty()) {
                State crt = open.poll();
                if (crt.st == goal) {
                    path(closed, crt.st);
                    out.println();
                    return ;
                }
                if (closed.get(crt.st).step < crt.step) continue;
                if (crt.step > 45) continue;
                int x = crt.blankPos / 4;
                int y = crt.blankPos % 4;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx >= 0 && ny >= 0 && nx < 4 && ny < 4) {
                        int nPos = nx * 4 + ny;
                        long nSt = swap(crt.st, crt.blankPos, nPos);
                        int step = crt.step + 1;
                        if (!closed.containsKey(nSt) || closed.get(nSt).step > step) {
                            closed.put(nSt, new P(step, crt.st, dc[i]));
                            open.add(new State(step + hStar(nSt), nPos, step, nSt));
                        }
                    }
                }
            }
        }
        out.println("This puzzle is not solvable.");
    }
    private void solve4() {
        int[] is = in.nextIntArray(16);
        int pos = -1;
        for (int i = 0; i < 16; i++) {
            is[i]--;
            if (is[i] < 0) {
                is[i] = 15;
                pos = i;
            }
        }
        if (can(is)) {
            PriorityQueue<State4> open = new PriorityQueue<State4>();
            Map<Long, P> closed = new HashMap<Long, P>();
            open.add(new State4(hStar3(is), pos, 0, is));
            closed.put(encode(is), new P(0, 0, ' '));
            while (!open.isEmpty()) {
                State4 crt = open.poll();
                long crtSt = encode(crt.is);
                if (goal(crt.is)) {
                    path(closed, crtSt);
                    out.println();
                    return ;
                }
                if (closed.get(crtSt).step < crt.step) continue;
                if (crt.step > 45) continue;
                int x = crt.blankPos / 4;
                int y = crt.blankPos % 4;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx >= 0 && ny >= 0 && nx < 4 && ny < 4) {
                        int nPos = nx * 4 + ny;
                        int[] nIs = crt.is.clone();
                        Algo.swap(nIs, crt.blankPos, nPos);
                        long nSt = encode(nIs);
                        int step = crt.step + 1;
                        if (!closed.containsKey(nSt) || closed.get(nSt).step > step) {
                            closed.put(nSt, new P(step, crtSt, dc[i]));
                            open.add(new State4(step + hStar3(nIs), nPos, step, nIs));
                        }
                    }
                }
            }
        }
        out.println("This puzzle is not solvable.");
    }

    private void path(Map<Long, P> closed, long st) {
        if (closed.get(st).dir == ' ') return ;
        path(closed, closed.get(st).pre);
        out.print(closed.get(st).dir);
    }

    class P {
        int step;
        long pre;
        char dir;

        P(int step, long pre, char dir) {
            this.step = step;
            this.pre = pre;
            this.dir = dir;
        }
    }
    long swap(long st, int i, int j) {
        long t = (st >>> (i * 4)) & 0xf;
        st &= ~(0xfL << (i * 4));
        st |= ((st >>> (j * 4)) & 0xf) << (i * 4);
        st &= ~(0xfL << (j * 4));
        st |= t << (j * 4);
        return st;
    }
    private long encode(int[] is) {
        long res = 0;
        for (int i = 0; i < 16; i++) {
            res |= (is[i] * 1L) << (i * 4);
        }
        return res;
    }

    int hStar(long st) {
        int res = 0;
        for (int i = 0; i < 16; i++) {
            int v = (int) ((st >>> (i * 4)) & 0xf);
            if (v != 15) res += dis[v][i];
        }
        return res;
    }
    int[][] dis = new int[16][16];
    {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                dis[i][j] = Math.abs(i / 4 - j / 4) + Math.abs(i % 4 - j % 4);
            }
        }
    }
    class State implements Comparable<State> {
        int cost;
        int blankPos;
        int step;
        long st;


        State(int cost, int blankPos, int step, long st) {
            this.cost = cost;
            this.blankPos = blankPos;
            this.step = step;
            this.st = st;
        }

        @Override
        public int compareTo(State o) {
            return cost - o.cost;
        }
    }
    class State4 implements Comparable<State4> {
        int cost;
        int blankPos;
        int step;
        int[] is;


        State4(int cost, int blankPos, int step, int[] is) {
            this.cost = cost;
            this.blankPos = blankPos;
            this.step = step;
            this.is = is;
        }

        @Override
        public int compareTo(State4 o) {
            return cost - o.cost;
        }
    }

    private boolean can(int[] is) {
        int cnt = 0;
        int pos = -1;
        for (int i = 0; i < 16; i++) if (is[i] != 15) {
            for (int j = 0; j < i; j++) if (is[j] != 15) {
                if (is[j] > is[i]) cnt++;
            }
        } else pos = i;
        cnt += pos / 4;
        return cnt % 2 == 1;
    }

    long goal;
    {
        goal = 0;
        for (int i = 0; i < 16; i++) {
            goal |= ((i * 1L) << (i * 4));
        }
    }
}
