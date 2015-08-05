package main;

import com.shu_mj.ds.UnionFindSet;
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
        int[] is = new int[n];
        int[] ps = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt() - 1;
            ps[is[i]] = i;
        }
        char[][] mat = new char[n][];
        for (int i = 0; i < n; i++) {
            mat[i] = in.next().toCharArray();
        }
        UnionFindSet uf = new UnionFindSet(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (mat[i][j] == '1') {
                    uf.union(i, j);
                }
            }
        }
        List<Integer>[] ls = new List[n];
        for (int i = 0; i < n; i++) {
            ls[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; i++) {
            ls[uf.find(i)].add(is[i]);
        }
        int[] rs = new int[n];
        Arrays.fill(rs, -1);
        for (int i = 0; i < n; i++) if (rs[i] == -1) {
            int v = uf.find(i);
            Collections.sort(ls[v]);
            for (int j = i, k = 0; j < n; j++) {
                if (uf.find(j) == v) {
                    rs[j] = ls[v].get(k++);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            out.print((rs[i] + 1) + " ");
        }
        out.println();
    }
}
