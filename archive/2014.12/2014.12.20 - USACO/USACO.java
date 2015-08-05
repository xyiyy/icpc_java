package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: window
     */
    Scanner in;
    PrintWriter out;
    private int[] left;
    private int[] right;
    private int[] x0;
    private int[] y0;
    private int[] x1;
    private int[] y1;
    private int r;
    private int l;
    private char updated;
    private double last;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = 128;
        left = new int[n];
        right = new int[n];
        r = -1;
        l = -1;
        x0 = new int[n];
        y0 = new int[n];
        x1 = new int[n];
        y1 = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        updated = ' ';
        last = 0;
        while (in.hasNext()) {
            String line = in.next();
            char f = line.charAt(0);
            if (f == 'w') {
                String[] ss = line.substring(2, line.length() - 1).split(",");
                char I = ss[0].charAt(0);
                int x = Integer.parseInt(ss[1]);
                int y = Integer.parseInt(ss[2]);
                int X = Integer.parseInt(ss[3]);
                int Y = Integer.parseInt(ss[4]);
                if (x > X) { int t = x; x = X; X = t; }
                if (y > Y) { int t = y; y = Y; Y = t; }
                x0[I] = x;
                y0[I] = y;
                x1[I] = X;
                y1[I] = Y;
                top(I);
            } else {
                char I = line.charAt(2);
                if (f == 't') {
                    free(I);
                    top(I);
                } else if (f == 'b') {
                    free(I);
                    bottom(I);
                } else if (f == 'd') {
                    free(I);
                } else { // s
                    out.printf("%.3f%n", 100 * calc(I));
                }
            }
        }
    }

    private double calc(char i) {
        if (updated == i) return last;
        updated = i;
        double all = (x1[i] - x0[i]) * (y1[i] - y0[i]);
        double sum = 0;
        List<E> es = new ArrayList<E>();
        for (int j = right[i]; j != -1; j = right[j]) {
            es.add(new E(x0[j], y0[j], y1[j], 1));
            es.add(new E(x1[j], y0[j], y1[j], -1));
        }
        Collections.sort(es);
        int[] line = new int[y1[i] - y0[i] + 1];
//        Seg seg = new Seg(N, y0[i], y1[i]);
        int lSum = 0;
        int lx = 0;
        for (int c = 0; c < es.size(); ) {
            int x = es.get(c).x;
            sum += lSum * Math.max(0, (Math.min(x, x1[i]) - Math.max(lx, x0[i])));
            while (c < es.size() && es.get(c).x == x) {
                E e = es.get(c);
                if (e.y0 >= y1[i] || e.y1 <= y0[i]) { c++; continue; }
                e.y0 = Math.max(e.y0, y0[i]);
                e.y1 = Math.min(e.y1, y1[i]);
                line[e.y0 - y0[i]] += e.lr;
                line[e.y1 - y0[i]] -= e.lr;
//                seg.update(e.y0, e.y1, e.lr);
                c++;
            }
            lx = x;
            lSum = 0;
//            lSum = seg.is[1];
            for (int j = 0, crt = 0; j < y1[i] - y0[i]; j++) {
                crt += line[j];
                if (crt > 0) lSum++;
            }
        }
        return last = 1 - sum / all;
    }

    class Seg {
        int[] is;
        int N;
        int y0;
        int y1;
        int[] ls;
        Seg(int n, int y0, int y1) {
            N = Integer.highestOneBit(n) << 1;
            this.y0 = y0;
            this.y1 = y1;
            is = new int[N * 2];
            ls = new int[N * 2];
        }

        void update(int l, int r, int val) {
            update(1, 0, N, l, r, val);
        }

        private void update(int o, int L, int R, int l, int r, int val) {
            if (L >= l && R <= r) {
                ls[o] += val;
            }
        }
    }

    class E implements Comparable<E> {
        int x;
        int y0;
        int y1;
        int lr;

        public E(int x, int y0, int y1, int lr) {
            this.x = x;
            this.y0 = y0;
            this.y1 = y1;
            this.lr = lr;
        }

        @Override
        public int compareTo(E o) {
            return x - o.x;
        }
    }

    private void bottom(char i) {
        updated = ' ';
        if (l == -1) l = r = i;
        else {
            right[i] = l;
            left[l] = i;
            l = i;
        }
    }

    private void top(char i) {
        updated = ' ';
        if (r == -1) l = r = i;
        else {
            left[i] = r;
            right[r] = i;
            r = i;
        }
    }

    private void free(char i) {
        updated = ' ';
        if (r == i) r = left[i];
        if (l == i) l = right[i];
        if (left[i] != -1) right[left[i]] = right[i];
        if (right[i] != -1) left[right[i]] = left[i];
        left[i] = right[i] = -1;
    }
}
