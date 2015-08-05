package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }


    void run() {
        int n = in.nextInt();
        int k = in.nextInt();
        int[][] iss = new int[n][4];
        Set<Integer> set = new TreeSet<Integer>();
        for (int i = 1; i < 1000000; i++) set.add(i);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                for (int p : set) if (fit(iss[i], j, p)) {
                    iss[i][j] = p;
                    break;
                }
                set.remove(iss[i][j]);
            }
        }
        int max = 0;
        for (int[] is : iss) max = Math.max(max, Algo.max(is));
        out.println(max * k);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                out.print(k * iss[i][j] + " ");
            }
            out.println();
        }
    }

    private boolean fit(int[] is, int j, int p) {
        for (int i = 0; i < j; i++) {
            if (Num.gcd(is[i], p) != 1) return false;
        }
        return true;
    }

    void run2() {
        int n = in.nextInt();
        int k = in.nextInt();
        long[][] iss = new long[n][4];
        PriorityQueue<P> que = new PriorityQueue<P>();
        ArrayList<Integer> primes = new ArrayList<Integer>();
        Num.primeTable(1000000, primes);
        que.add(new P(1, 1));
        for (int p : primes) que.add(new P(p, p));
        P[] ps = new P[4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                P p = que.poll();
                iss[i][j] = p.y;
                p.y *= p.x;
                ps[j] = p;
            }
            for (int j = 0; j < 4; j++) if (i != 0 || j != 0) que.add(ps[j]);
        }
        out.println(iss[n - 1][3] * k);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                out.print(iss[i][j] * k + " ");
            }
            out.println();
        }
    }
    class P implements Comparable<P> {
        long x;
        long y;

        P(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(P o) {
            return Long.compare(y, o.y);
        }
    }
}
