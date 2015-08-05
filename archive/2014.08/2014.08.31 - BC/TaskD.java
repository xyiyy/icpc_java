package main;



import com.shu_mj.ds.MatSum;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) solve();
    }

    private void solve() {
        int n = in.nextInt();
        Mat mat = new Mat(n);
        int[] p0 = in.nextIntArray(n);
        int[] p1 = in.nextIntArray(n);
        int[] rp1 = new int[n];
        int[] is = new int[n];
        int[] ris = new int[n];
        long all = 0;
        for (int i = 0; i < n; i++) {
            rp1[p1[i]] = i;
        }
        for (int i = 0; i < n; i++) {
            is[i] = rp1[p0[i]];
            ris[is[i]] = i;
            all += mat.insert(i, is[i]);
        }
        int m = in.nextInt();
        while (m-- != 0) {
            if (in.next().charAt(0) == 'Q') {
                out.println(all);
            } else if (in.nextInt() == 0) {
                int i0 = in.nextInt();
                int j0 = in.nextInt();
                int i = is[i0];
                int j = is[j0];
                all -= mat.remove(i0, i);
                all -= mat.remove(j0, j);
                all += mat.insert(i0, j);
                all += mat.insert(j0, i);
                Algo.swap(is, i0, j0);
                ris[is[i0]] = i0;
                ris[is[j0]] = j0;
            } else {
                int i = in.nextInt();
                int j = in.nextInt();
                int i0 = ris[i];
                int j0 = ris[j];
                all -= mat.remove(i0, i);
                all -= mat.remove(j0, j);
                all += mat.insert(i0, j);
                all += mat.insert(j0, i);
                Algo.swap(is, i0, j0);
                ris[is[i0]] = i0;
                ris[is[j0]] = j0;
            }
        }
    }

    class Mat {
        final int N;
        final int B = 100;
        final int BS;
        int[] X;
        int[] Y;
        MatSum cnt;
        Mat(int n) {
            N = n;
            X = new int[n];
            Y = new int[n];
            BS = (n + B - 1) / B;
            cnt = new MatSum(BS, BS);
            Arrays.fill(X, -1);
            Arrays.fill(Y, -1);
        }

        void add(int x, int y) {
            Y[x] = y;
            X[y] = x;
            cnt.add(x / B, y / B, 1);
        }

        void sub(int x, int y) {
            Y[x] = -1;
            X[y] = -1;
            cnt.add(x / B, y / B, -1);
        }

        int query(int x, int y) {
            return sum(x, N) + sum(N, y) - 2 * sum(x, y);
        }

        int insert(int x, int y) {
            int res = query(x, y);
            add(x, y);
            return res;
        }

        int remove(int x, int y) {
            int res = query(x, y);
            sub(x, y);
            return res;
        }

        int query(int x0, int y0, int x1, int y1) {
            return sum(x1, y1) + sum(x0, y0) - sum(x0, y1) - sum(x1, y0);
        }

        int sum(int x, int y) {
            int w = x / B;
            int h = y / B;
            int res = cnt.sum(0, 0, w, h);
            for (int i = w * B; i < x; i++) {
                if (Y[i] != -1 && Y[i] < h * B) res++;
            }
            for (int i = h * B; i < y; i++) {
                if (X[i] != -1 && X[i] < x) res++;
            }
            return res;
        }
    }

}
