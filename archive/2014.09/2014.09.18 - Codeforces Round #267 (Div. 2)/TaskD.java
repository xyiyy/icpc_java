package main;

import com.shu_mj.ds.Hash;
import com.shu_mj.graph.SCC;
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

    final long D = (long) (1e11);
    void run() {
        int m = in.nextInt();
        Map<Long, Integer> index = new HashMap<Long, Integer>();
        List<Long> value = new ArrayList<Long>();
        int[] is = new int[m];
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            String s = in.next().toLowerCase();
            char[] cs = s.toCharArray();
            long hs = Hash.getHash(cs);
            if (!index.containsKey(hs)) {
                index.put(hs, cnt++);
                value.add(Algo.count(cs, 'r') * D + cs.length);
            }
            is[i] = index.get(hs);
        }
        int n = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            String s = in.next().toLowerCase();
            char[] cs = s.toCharArray();
            long hs = Hash.getHash(cs);
            if (!index.containsKey(hs)) {
                index.put(hs, cnt++);
                value.add(Algo.count(cs, 'r') * D + cs.length);
            }
            x[i] = index.get(hs);
            String t = in.next().toLowerCase();
            char[] ct = t.toCharArray();
            long ht = Hash.getHash(ct);
            if (!index.containsKey(ht)) {
                index.put(ht, cnt++);
                value.add(Algo.count(ct, 'r') * D + ct.length);
            }
            y[i] = index.get(ht);
        }
        SCC.V[] vs = new SCC.V[cnt];
        for (int i = 0; i < cnt; i++) {
            vs[i] = new SCC.V();
        }
        for (int i = 0; i < n; i++) {
            vs[x[i]].add(vs[y[i]]);
        }
        int compCnt = SCC.scc(vs);
        V[] us = new V[compCnt];
        for (int i = 0; i < us.length; i++) us[i] = new V();
        for (int i = 0; i < cnt; i++) {
            us[vs[i].comp].min = Math.min(us[vs[i].comp].min, value.get(i));
        }
        for (int i = 0; i < n; i++) {
            us[vs[x[i]].comp].vs.add(us[vs[y[i]].comp]);
        }
        for (V v : us) if (!v.vis) {
            dfs(v);
        }
        long ans = 0;
        for (int i : is) {
            ans += us[vs[i].comp].min;
        }
        out.println(ans / D + " " + ans % D);
    }

    private void dfs(V v) {
        for (V u : v.vs) {
            if (!u.vis) dfs(u);
            v.min = Math.min(v.min, u.min);
        }
        v.vis = true;
    }

    class V {
        boolean vis = false;
        long min = Long.MAX_VALUE;
        List<V> vs = new ArrayList<V>();

    }
}
