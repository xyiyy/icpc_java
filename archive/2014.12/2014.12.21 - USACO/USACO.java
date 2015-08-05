package main;

import com.shu_mj.graph.Dinic;
import com.shu_mj.tpl.Scanner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: telecow
     */
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
        int s = in.nextInt() - 1;
        int t = in.nextInt() - 1;
        Dinic.V[] vs = new Dinic.V[n * 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new Dinic.V();
            if (i % 2 == 1) vs[i ^ 1].add(vs[i], 1);
        }
        int[][] es = in.nextIntMatrix(m, 2);
        for (int[] e : es) {
            int u = e[0] - 1;
            int v = e[1] - 1;
            vs[u * 2 + 1].add(vs[v * 2], Dinic.INF);
            vs[v * 2 + 1].add(vs[u * 2], Dinic.INF);
        }
        int f = Dinic.dinic(vs[s * 2 + 1], vs[t * 2]);
        out.println(f);
        boolean[] del = new boolean[n];
        boolean first = true;
        for (int i = 0; i < n; i++) if (i != s && i != t) {
            del[i] = true;
            for (int j = 0; j < vs.length; j++) {
                vs[j] = new Dinic.V();
                if (j % 2 == 1 && !del[j / 2]) vs[j ^ 1].add(vs[j], 1);
            }
            for (int[] e : es) {
                int u = e[0] - 1;
                int v = e[1] - 1;
                vs[u * 2 + 1].add(vs[v * 2], Dinic.INF);
                vs[v * 2 + 1].add(vs[u * 2], Dinic.INF);
            }
            int nf = Dinic.dinic(vs[s * 2 + 1], vs[t * 2]);
            if (nf == f - 1) {
                if (first) first = false; else out.print(" ");
                out.print((i + 1));
                f = nf;
            } else {
                del[i] = false;
            }
        }
        out.println();
    }

    void run2() {
        Scanner fin;
        try {
            fin = new Scanner(new FileInputStream("font.in"));
        } catch (FileNotFoundException e) {
            return ;
        }
        int[][] fs = new int[27][20];
        fin.next();
        for (int i = 0; i < 27; i++) {
            for (int j = 0; j < 20; j++) {
                fs[i][j] = Integer.parseInt(fin.next(), 2);
            }
        }
        int n = in.nextInt();
        int[] cs = new int[n];
        for (int i = 0; i < n; i++) {
            cs[i] = Integer.parseInt(in.next(), 2);
        }
        int ans = 0;
        int cost = 12341234;
        for (int i = 0; i < 27; i++) {
            int cc = compare(n, cs, fs[i]);
            if (cc < cost) {
                cost = cc;
                ans = i;
            }
        }
        if (ans == 0) out.println(" ");
        else out.println((char) ('a' + ans - 1));
    }

    private int compare(int n, int[] cs, int[] f) {
//        int res = compare(cs, f);
        for (int i = 1; i < n; i++) {
            if (cs[i] == cs[i - 1]) {

            }
        }
        return 0;
    }
}
