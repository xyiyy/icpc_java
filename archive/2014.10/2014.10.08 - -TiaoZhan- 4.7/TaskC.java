package main;

import com.shu_mj.ds.RMQ;
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
        int[] is = in.nextIntArray(n);
        int[] ss = is.clone();
        Arrays.sort(ss);
        ss = Algo.unique(ss);
        for (int i = 0; i < n; i++) is[i] = Algo.lowerBound(ss, is[i]) + 1;
        int[] rev = is.clone();
        Algo.reverse(rev);
        SuffixArray sa = new SuffixArray(rev);
        int p1 = 0;
        for (int i = 0; i < n; i++) {
            p1 = n - sa.si[i];
            if (p1 >= 1 && n - p1 >= 2) break;
        }
        int m = n - p1;
        rev = Algo.merge(Arrays.copyOfRange(is, p1, n), Arrays.copyOfRange(is, p1, n));
        Algo.reverse(rev);
        sa = new SuffixArray(rev);
        int p2 = 0;
        for (int i = 0; i <= 2 * m; i++) {
            p2 = p1 + m - sa.si[i];
            if (p2 - p1 >= 1 && n - p2 >= 1) break;
        }
        Algo.reverse(is, 0, p1);
        Algo.reverse(is, p1, p2);
        Algo.reverse(is, p2, n);
        for (int i = 0; i < n; i++) {
            out.println(ss[is[i] - 1]);
        }
    }
}
class SuffixArray {
    public int n;
    public int[] cs;
    public int[] si, is;// ソート後と元のインデックスの対応

    public int[] indexSort(int[] is) {
        int max = Algo.max(is);
        int[] c = new int[max + 1];
        for (int i : is) c[i]++;
        for (int i = 1; i <= max; i++) c[i] += c[i - 1];
        int n = is.length;
        int[] si = new int[n];
        for (int i = n - 1; i >= 0; i--) si[--c[is[i]]] = i;
        return si;
    }

    public SuffixArray(int[] t) {
        n = t.length;
        cs = new int[n + 1];
        System.arraycopy(t, 0, cs, 0, n);
        cs[n] = 0;// 番兵(他の全ての値より小さくすること)
        is = new int[n + 1];
        for (int i = 0; i <= n; i++) is[i] = cs[i];
        si = indexSort(is);
        int[] a = new int[n + 1], b = new int[n + 1];
        for (int h = 0; ; ) {
            for (int i = 0; i < n; i++) {
                int x = si[i + 1], y = si[i];
                b[i + 1] = b[i];
                if (is[x] > is[y] || is[x + h] > is[y + h]) b[i + 1]++;
            }
            for (int i = 0; i <= n; i++) is[si[i]] = b[i];
            if (b[n] == n) break;
            h = Math.max(1, h << 1);
            for (int k = h; k >= 0; k -= h) {
                Arrays.fill(b, 0);
                b[0] = k;
                for (int i = k; i <= n; i++) b[is[i]]++;
                for (int i = 0; i < n; i++) b[i + 1] += b[i];
                for (int i = n; i >= 0; i--) {
                    a[--b[si[i] + k > n ? 0 : is[si[i] + k]]] = si[i];
                }
                int[] tmp = si; si = a; a = tmp;
            }
        }
    }

    public int[] hs;// hs[i]:=ソート後のsuffix のi とi+1 のLCP

    public void buildHs() {
        hs = new int[n + 1];
        for (int i = 0, h = 0; i < n; i++) {
            for (int j = si[is[i] - 1]; cs[i + h] == cs[j + h]; h++) ;
            hs[is[i] - 1] = h;
            if (h > 0) h--;
        }
    }

    public RMQ rmq;

    public void buildRMQ() {
        rmq = new RMQ(hs);// 値を返す版
    }

    // 位置i,j から始まる部分列のLCP を求める
    public int getLCP(int i, int j) {
        if (i == j) return n - i;
        return rmq.vs[rmq.query(Math.min(is[i], is[j]), Math.max(is[i], is[j]))];
    }
}
