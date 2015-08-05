package main;

import com.shu_mj.graph.BipartiteMatching;
import com.shu_mj.graph.Dinic;
import com.shu_mj.graph.HopcroftKarp;
import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] is = in.nextIntArray(n);
        int res = 0;
        List<Integer> ps = new ArrayList<Integer>();
        Num.primeTable(100000, ps);
        for (int i : is) if (i > 1) ps.add(i);
        int[] as = new int[m];
        int[] bs = new int[m];
        for (int i = 0; i < m; i++) {
            as[i] = in.nextInt() - 1;
            bs[i] = in.nextInt() - 1;
        }
        for (int p : ps) {
            //if (outOf(is, p)) break;
            if (noV(is, as, bs, p)) continue;
            Dinic.V[] vs = new Dinic.V[n + 2];
            for (int i = 0; i < vs.length; i++) {
                vs[i] = new Dinic.V();
            }
            Dinic.V s = vs[n];
            Dinic.V t = vs[n + 1];
            for (int i = 0; i < m; i++) {
                int a = as[i];
                int b = bs[i];
                if (is[a] % p == 0 && is[b] % p == 0) {
                    int c = 0;
                    int ia = is[a];
                    int ib = is[b];
                    while (ia % p == 0 && ib % p == 0) {
                        ia /= p;
                        ib /= p;
                        c++;
                    }
                    if (a % 2 == 1) vs[a].add(vs[b], c);
                    else vs[b].add(vs[a], c);
                }
            }
            for (int i = 0; i < n; i++) {
                if (is[i] % p == 0) {
                    int c = 0;
                    while (is[i] % p == 0) {
                        is[i] /= p;
                        c++;
                    }
                    if (i % 2 == 1) s.add(vs[i], c);
                    else vs[i].add(t, c);
                }
            }
            res += Dinic.dinic(s, t);
        }
        /*for (int p : ps) {
            if (outOf(is, p)) break;
            for (;;) {
                if (noV(is, as, bs, p)) break;
                HopcroftKarp.V[] vs = new HopcroftKarp.V[n];
                for (int i = 0; i < vs.length; i++) {
                    vs[i] = new HopcroftKarp.V();
                }
                for (int i = 0; i < m; i++) {
                    int a = as[i];
                    int b = bs[i];
                    if (is[a] % p == 0 && is[b] % p == 0) {
                        vs[a].connect(vs[b]);
                    }
                }
                res += HopcroftKarp.hopcroftKarp(vs);
                for (int i = 0; i < m; i++) {
                    int a = as[i];
                    int b = bs[i];
                    if (is[a] % p == 0 && is[b] % p == 0 && vs[a].pair == vs[b]) {
                        is[a] /= p;
                        is[b] /= p;
                    }
                }
            }
        }
        */
        out.println(res);
    }

    private boolean noV(int[] is, int[] as, int[] bs, int p) {
        for (int i = 0; i < as.length; i++) {
            int a = as[i];
            int b = bs[i];
            if (is[a] % p == 0 && is[b] % p == 0) return false;
        }
        return true;
    }

    private boolean outOf(int[] is, int p) {
        for (int i : is) {
            if (i >= (long) p * p) return false;
        }
        return true;
    }
}
