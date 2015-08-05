package main;



import com.shu_mj.ds.UnionFindSet;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class Task10TestCase {

    Random in = new Random();
    String ln = System.lineSeparator();

    @TestCase
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<Test>();
        for (int i = 0; i < 10; i++) {
            list.add(gen());
        }
        return list;
    }

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int n = in.nextInt(100) + 1;
        int m = in.nextInt(1001);
        int k = in.nextInt(100) + 1;
        input.append(n + " " + m + " " + k + ln);
        E[] es = new E[m];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt(n);
            int v = in.nextInt(n);
            int c = in.nextInt(100) + 1;
            es[i] = new E(u, v, c);
            input.append((u + 1) + " " + (v + 1) + " " + c + ln);
        }
        Arrays.sort(es);
        for (int i = 0; i < k; i++) {
            int u = in.nextInt(n);
            int v = in.nextInt(n);
            input.append((u + 1) + " " + (v + 1) + ln);
            output.append(solve(es, n, u, v) + ln);
        }
        return new Test(input.toString(), output.toString());
    }

    private String solve(E[] es, int n, int u, int v) {
        if (u == v) return "0";
        UnionFindSet uf = new UnionFindSet(n);
        for (E e : es) {
            uf.union(e.u, e.v);
            if (uf.isSame(u, v)) return "" + e.cost;
        }
        return "no path";
    }

    class E implements Comparable<E> {
        int u;
        int v;
        int cost;

        E(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(E o) {
            return cost - o.cost;
        }
    }
}
