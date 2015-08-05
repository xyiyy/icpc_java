package main;

import com.shu_mj.graph.Dinic;
import com.shu_mj.graph.HopcroftKarp;
import com.shu_mj.math.Num;
import com.shu_mj.tpl.PII;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskCTestCase {

    Random in = new Random();
    String ln = System.lineSeparator();

    @TestCase
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<Test>();
        for (int i = 0; i < 0; i++) {
            list.add(gen());
        }
        return list;
    }

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int n = in.nextInt(100) + 1;
        if (n < 2) n = 2;
        int m = in.nextInt(n) + 1;
        int[] is = new int[n];
        input.append(n + " " + m + ln);
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt((int) 1e9) + 1;
            input.append(is[i] + " ");
        }
        input.append(ln);
        int[] as = new int[m];
        int[] bs = new int[m];
        Set<PII> set = new TreeSet<PII>();
        for (int j = 0; j < m; j++) {
            for (;;) {
                as[j] = in.nextInt(n / 2) * 2;
                bs[j] = in.nextInt((n - 1) / 2) * 2 + 1;
                if (as[j] > bs[j]) { int t = as[j]; as[j] = bs[j]; bs[j] = t; }
                if (!set.contains(new PII(as[j], bs[j]))) break;
            }
            set.add(new PII(as[j], bs[j]));
            input.append((as[j] + 1) + " " + (bs[j] + 1) + ln);
        }
        List<Integer> ps = new ArrayList<Integer>();
        Num.primeTable(100000, ps);
        int res = 0;
        for (int p : ps) {
            if (outOf(is, p)) break;
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
        output.append(res + ln);
        return new Test(input.toString(), output.toString());
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
