package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
    Scanner in;
    PrintWriter out;
    private int m;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case %d: ", i);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        m = in.nextInt();
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) vs[i] = new V();
        for (int i = 1; i < n; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int damage = in.nextInt();
            int length = in.nextInt();
            vs[u].es.add(new E(vs[v], damage, length));
            vs[v].es.add(new E(vs[u], damage, length));
        }
        dfs(vs[0], null, 0, 0);
        out.println(go(vs[0], null));
    }

    private int go(V v, V pre) {
        int res = 0;
        if (v.son != null) {
            res = Math.max(res, go(v.son.to, v));
            v.map = v.son.to.map;
        } else {
            v.map = new TreeMap<Integer, Integer>();
        }
        tryToInsert(v.map, v.damage, v.depth);
        res = Math.max(res, get(v.map, m + v.damage) - v.depth);
        for (E e : v.es) if (e.to != pre && e != v.son) {
            res = Math.max(res, go(e.to, v));
            for (Map.Entry<Integer, Integer> en : e.to.map.entrySet()) {
                if (en.getKey() - v.damage <= m) res = Math.max(res, en.getValue() + get(v.map, m - en.getKey() + 2 * v.damage) - 2 * v.depth);
            }
            for (Map.Entry<Integer, Integer> en : e.to.map.entrySet()) {
                tryToInsert(v.map, en.getKey(), en.getValue());
            }
            e.to.map = null;
        }
        return res;
    }

    private int get(TreeMap<Integer, Integer> map, int damage) {
        Map.Entry<Integer, Integer> e = map.floorEntry(damage);
        if (e != null) return e.getValue();
        return 0;
    }

    private void tryToInsert(TreeMap<Integer, Integer> map, int damage, int depth) {
        Map.Entry<Integer, Integer> e = map.floorEntry(damage);
        if (e == null || e.getValue() < depth) {
            map.put(damage, depth);
            for (;;) {
                e = map.higherEntry(damage);
                if (e == null || e.getValue() > depth) break;
                map.remove(e.getKey());
            }
        }
    }

    private void dfs(V v, V pre, int damage, int depth) {
        v.damage = damage;
        v.depth = depth;
        v.size = 1;
        for (E e : v.es) if (e.to != pre) {
            dfs(e.to, v, damage + e.damage, depth + e.length);
            v.size += e.to.size;
            if (v.son == null || v.son.to.size < e.to.size) v.son = e;
        }
    }

    class V {
        List<E> es = new LinkedList<E>();
        int damage;
        int depth;
        int size;
        E son;
        TreeMap<Integer, Integer> map;
    }

    class E {
        V to;
        int damage;
        int length;

        E(V to, int damage, int length) {
            this.to = to;
            this.damage = damage;
            this.length = length;
        }
    }
}
