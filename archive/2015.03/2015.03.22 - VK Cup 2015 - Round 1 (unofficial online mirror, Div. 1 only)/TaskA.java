package main;



import com.shu_mj.ds.RMQ;
import com.shu_mj.ds.SuffixArray;
import com.shu_mj.ds.SuffixArrayS;
import com.shu_mj.ds.SuffixTree;
import com.shu_mj.tpl.PII;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        char[] cs = in.next().toCharArray();
        int n = cs.length;
        int n2 = n * 2;
        cs = Algo.merge(cs, cs);
        SuffixArrayS sa = new SuffixArrayS(cs);
        Seg seg = new Seg(cs);
        int[] lr = new int[2];
        for (int i = 0; ; i++) {
            int id = sa.si[i];
            if (id < n) {
                seg.query(id, id + n, lr);
                if (lr[1] == 0) {
                    for (int j = 0; j < lr[0]; j++) out.print('(');
                    for (int j = 0; j < n; j++) out.print(cs[j + id]);
                    out.println();
                    return ;
                } else if (lr[0] == 0) {
                    for (int j = 0; j < n; j++) out.print(cs[j + id]);
                    for (int j = 0; j < lr[1]; j++) out.print(')');
                    out.println();
                    return ;
                }
            }
        }
    }

    class Seg {
        int N;
        int[] left, right;

        Seg(char[] cs) {
            int n = cs.length;
            N = Integer.highestOneBit(n) << 1;
            left = new int[N * 2];
            right = new int[N * 2];
            for (int i = 0; i < N; i++) {
                if (i < n && cs[i] == '(') {
                    left[i + N] = 0;
                    right[i + N] = 1;
                } else {
                    left[i + N] = 1;
                    right[i + N] = 0;
                }
            }
            for (int i = N - 1; i > 0; i--) {
                left[i] = left[i * 2] + Math.max(0, left[i * 2 + 1] - right[i * 2]);
                right[i] = right[i * 2 + 1] + Math.max(0, right[i * 2] - left[i * 2 + 1]);
            }
        }
        void query(int s, int t, int[] lr) {
            int l = 0, r = 0;
            while (0 < s && s + (s & -s) <= t) {
                int i = (N + s) / (s & -s);
                int nl = l + Math.max(0, left[i] - r);
                int nr = right[i] + Math.max(0, r - left[i]);
                l = nl; r = nr;
                s += s & -s;
            }
            int l2 = 0, r2 = 0;
            while (s < t) {
                int i = (N + t) / (t & -t) - 1;
                int nl = left[i] + Math.max(0, l2 - right[i]);
                int nr = r2 + Math.max(0, right[i] - l2);
                l2 = nl; r2 = nr;
                t -= t & -t;
            }
            lr[0] = l + Math.max(0, l2 - r);
            lr[1] = r2 + Math.max(0, r - l2);
        }
    }

}
