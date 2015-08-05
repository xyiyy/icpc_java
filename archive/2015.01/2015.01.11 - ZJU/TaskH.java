package main;

import com.shu_mj.ds.AhoCorasick;
import com.shu_mj.tpl.PII;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;
import test.on2015_01.on2015_01_09_FB.C.C;

public class TaskH {
    Scanner in;
    PrintWriter out;
    private int n;
    private int m;
    private int acm;

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

    private void solve() {
        n = in.nextInt();
        m = in.nextInt();
        char[][] mat = in.nextCharMap(n);
        int atx = -1;
        int aty = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == '@') {
                    atx = i;
                    aty = j;
                    mat[i][j] = '.';
                }
            }
        }
        int w = in.nextInt();
        char[][] ss = in.nextCharMap(w);
        AhoCorasick ac = new AhoCorasick(ss);
        List<AhoCorasick.Node> nodes = new ArrayList<AhoCorasick.Node>();
        Map<AhoCorasick.Node, Integer> index = ac.getNodeIndex(nodes);
        acm = nodes.size();
        int[] step = new int[n * m * acm];
        Queue<Integer> que = new LinkedList<Integer>();
        Arrays.fill(step, -1);
        que.add(st(atx, aty, 0));
        step[st(atx, aty, 0)] = 0;
        while (!que.isEmpty()) {
            int crt = que.poll();
            int stCrt = crt;
            int s = crt % acm; crt /= acm;
            int y = crt % m;
            int x = crt / m;
            if (!nodes.get(s).accept.isEmpty()) {
                out.println(step[stCrt]);
                return ;
            }
            for (int i = 0; i < 4; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];
                char c;
                if (in(tx, ty) && (c = mat[tx][ty]) != '#') {
                    if (c != '.') {
                        AhoCorasick.Node u = nodes.get(s);
                        while (u != null && !u.cs.containsKey(c)) u = u.fail;
                        if (u == null) u = ac.root;
                        else u = u.cs.get(c);
                        for (;;) {
                            int ns = index.get(u);
                            int stNxt = st(tx, ty, ns);
                            if (step[stNxt] == -1) {
                                que.add(stNxt);
                                step[stNxt] = step[stCrt] + 1;
                            } else break;
                            if (!u.cs.containsKey(c)) break;
                            u = u.cs.get(c);
                        }
                    } else {
                        int stNxt = st(tx, ty, s);
                        if (step[stNxt] == -1) {
                            que.add(stNxt);
                            step[stNxt] = step[stCrt] + 1;
                        }
                    }
                }
            }
        }
        out.println(-1);
    }

    private boolean in(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    private int st(int x, int y, int s) {
        return x * m * acm + y * acm + s;
    }

    int[] dx = { 0, 0, 1, -1 };
    int[] dy = { 1, -1, 0, 0 };
}
