package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskMat {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0) solve();
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int q = in.nextInt();
        Mat mat = new Mat(n, m);
        while (q-- != 0) {
            char c = in.next().charAt(0);
            int x0 = in.nextInt() - 1;
            int y0 = in.nextInt() - 1;
            int x1 = in.nextInt();
            int y1 = in.nextInt();
//            Algo.debug(q, c, x0, y0, x1, y1);
            if (c == 'A') {
                int val = in.nextInt();
                mat.add(x0, y0, x1, y1, val);
            } else {
                out.println(mat.sum(x0, y0, x1, y1));
            }
        }
    }

    class T {
        long sum;
        long delta;
        int x0;
        int y0;
        int x1;
        int y1;
        T t00;
        T t01;
        T t10;
        T t11;

        T(long sum, long delta, int x0, int y0, int x1, int y1, T t00, T t01, T t10, T t11) {
            this.sum = sum;
            this.delta = delta;
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;
            this.t00 = t00;
            this.t01 = t01;
            this.t10 = t10;
            this.t11 = t11;
        }

        T(int x0, int y0, int x1, int y1) {
            this(0, 0, x0, y0, x1, y1, null, null, null, null);
        }

        @Override
        public String toString() {
            return "T{" +
                    "sum=" + sum +
                    ", delta=" + delta +
                    ", x0=" + x0 +
                    ", y0=" + y0 +
                    ", x1=" + x1 +
                    ", y1=" + y1 +
                    '}';
        }
    }
    class Mat {
        int N;
        T root;
        private int x0;
        private int y0;
        private int x1;
        private int y1;
        private long val;

        public Mat(int n, int m) {
            N = Integer.highestOneBit(Math.max(n, m)) << 1;
            root = new T(0, 0, N, N);
        }

        public void add(int x0, int y0, int x1, int y1, long val) {
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;
            this.val = val;
            add(root);
        }

        private void add(T t) {
//            Algo.debug(t);
            if (outside(t)) {

            } else if (inside(t)) {
                t.sum += val * (t.x1 - t.x0) * (t.y1 - t.y0);
                t.delta += val;
            } else {
                t.sum += val * (Math.min(t.x1, x1) - Math.max(t.x0, x0)) * (Math.min(t.y1, y1) - Math.max(t.y0, y0));
                int xm = (t.x1 + t.x0) / 2;
                int ym = (t.y1 + t.y0) / 2;
                if (t.t00 == null) t.t00 = new T(t.x0, t.y0, xm, ym);
                if (t.t01 == null) t.t01 = new T(t.x0, ym, xm, t.y1);
                if (t.t10 == null) t.t10 = new T(xm, t.y0, t.x1, ym);
                if (t.t11 == null) t.t11 = new T(xm, ym, t.x1, t.y1);
                add(t.t00);
                add(t.t01);
                add(t.t10);
                add(t.t11);
            }
        }

        private boolean inside(T t) {
            return t.x0 >= x0 && t.x1 <= x1 && t.y0 >= y0 && t.y1 <= y1;
        }

        public long sum(int x0, int y0, int x1, int y1) {
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;
            return sum(root, 0);
        }

        private long sum(T t, long delta) {
//            Algo.debug(t);
            if (outside(t)) {
                return 0;
            } else if (inside(t)) {
                return t.sum + delta * (t.x1 - t.x0) * (t.y1 - t.y0);
            } else {
                delta += t.delta;
                int xm = (t.x1 + t.x0) / 2;
                int ym = (t.y1 + t.y0) / 2;
                if (t.t00 == null) t.t00 = new T(t.x0, t.y0, xm, ym);
                if (t.t01 == null) t.t01 = new T(t.x0, ym, xm, t.y1);
                if (t.t10 == null) t.t10 = new T(xm, t.y0, t.x1, ym);
                if (t.t11 == null) t.t11 = new T(xm, ym, t.x1, t.y1);
                return sum(t.t00, delta) + sum(t.t01, delta) + sum(t.t10, delta) + sum(t.t11, delta);
            }
        }

        private boolean outside(T t) {
            return t.x0 >= x1 || t.x1 <= x0 || t.y0 >= y1 || t.y1 <= y0;
        }
    }
}
