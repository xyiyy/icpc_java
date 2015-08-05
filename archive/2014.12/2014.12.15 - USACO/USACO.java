package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: shuttle
     */
    Scanner in;
    PrintWriter out;
    private int[] st;
    private int maxDepth;
    private int[] p;
    private int n;
    private Map<Integer, Integer> step;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        n = in.nextInt();
        List<Integer> ls = new LinkedList<Integer>();
        for (int i = 1, s = -1; i <= n * 2; i++, s *= -1) {
            int c = i > n ? 2 * n - i : i;
            for (int j = c; j >= -c; j -= 2) {
                ls.add(j * s);
            }
        }
        p = Algo.unBox(ls.toArray(new Integer[0]));
        for (int i = 0; i < ls.size(); i++) {
            p[i] += n;
        }
        int i = ls.size();
        for (int j = 0; j < i; ) {
            out.print(p[j++] + 1);
            while (j < i) {
                out.print(" " + (p[j++] + 1));
                if (j % 20 == 0) {
                    break;
                }
            }
            out.println();
        }
    }

    void run2() {
        n = in.nextInt();
        init();
        Algo.debug(step.size());
        st = new int[n * 2 + 1];
        for (int i = 0; i < n * 2 + 1; i++) {
            if (i < n) st[i] = -1;
            if (i > n) st[i] = 1;
        }
        p = new int[1024];
        for (int i = 0; ; i++) {
//            Algo.debug(i);
            maxDepth = i;
            if (dfs(0, n, 0)) {
                for (int j = 0; j < i; ) {
                    out.print(p[j] + 1);
                    for (j++; j < i; j++) {
                        if (j % 20 == 19) {
                            break;
                        }
                        out.print(" " + (p[j] + 1));
                    }
                    out.println();
                }
                for (int j = 0; j < i; j++) {
                    System.err.printf("%3d", (p[j] - n));
                }
                System.err.println();
                break;
            }
        }
    }

    int encode(int[] is) {
        int r = 1;
        int c = 0;
        int cc = 0;
        for (int i : is) {
            if (i == 0) {
                c = cc;
                continue;
            }
            cc++;
            r <<= 1;
            r ^= i == 1 ? 1 : 0;
        }
        r <<= 5;
        r ^= c;
        return r;
    }
    int[] decode(int r) {
        int[] is = new int[n * 2 + 1];
        int c = r & 31;
        r >>= 5;
        for (int i = n * 2; i >= 0; i--) {
            if (i == c) continue;
            is[i] = (r & 1) != 0 ? 1 : -1;
            r >>= 1;
        }
        return is;
    }
    private void init() {
        int[] bs = new int[n * 2 + 1];
        for (int i = 0; i <= n * 2; i++) {
            if (i < n) bs[i] = 1;
            if (i > n) bs[i] = -1;
        }
        step = new HashMap<Integer, Integer>();
        step.put(encode(bs), 0);
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(encode(bs));
        int max = 80;
//        Algo.debug(bs, decode(encode(bs)));
        while (!que.isEmpty()) {
            int crt = que.poll();
            int crtStep = step.get(crt);
            int[] is = decode(crt);
            int c = 0;
            for (int i = 0; i <= n * 2; i++) if (is[i] == 0) c = i;
            for (int i = -2; i <= 2; i++) if (i != 0) {
                int s = c + i;
                if (s >= 0 && s <= n * 2) {
                    if ((i & 1) != 0 || is[c + i / 2] + is[s] == 0) {
                        { int t = is[c]; is[c] = is[s]; is[s] = t; }
                        int nst = encode(is);
                        if (!step.containsKey(nst)) {
                            step.put(nst, crtStep + 1);
                            if (crtStep < max) {
                                que.add(nst);
                            }
                        }
                        { int t = is[c]; is[c] = is[s]; is[s] = t; }
                    }
                }
            }
        }
    }

    private boolean dfs(int d, int c, int inv) {
        if (d + h(c) > maxDepth) return false;
        if (c == n && goal()) return true;
        for (int i = -2; i <= 2; i++) if (i != 0 && i + inv != 0) {
            int s = c + i;
            if (s >= 0 && s <= n * 2) {
                if ((i & 1) != 0 || st[c + i / 2] + st[s] == 0) {
                    { int t = st[c]; st[c] = st[s]; st[s] = t; }
                    p[d] = s;
                    if (dfs(d + 1, s, i)) return true;
                    { int t = st[c]; st[c] = st[s]; st[s] = t; }
                }
            }
        }
        return false;
    }

    private int h(int c) {
        int ha = encode(st);
        if (step.containsKey(ha)) return step.get(ha);
        int r = 0;
//        if (c < n) r += (n - c + 1) / 2;
//        r += Math.abs(n - c);
        for (int i = 0; i < n; i++) {
            if (st[i] == -1) {
                r += (n - i) / 2 + 1;
//                r += n + 1 - i;
            }
        }
        for (int i = n + 1; i <= 2 * n; i++) {
            if (st[i] == 1) {
                r += (i - n) / 2 + 1;
//                r += i - n + 1;
            }
        }
        return r * 3 / 2;
    }

    private boolean goal() {
        for (int i = 0; i < n; i++) {
            if (st[i] != 1) return false;
        }
        for (int i = n + 1; i <= n * 2; i++) {
            if (st[i] != -1) return false;
        }
        return true;
    }
}
