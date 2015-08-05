package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int q = in.nextInt();
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new V();
            vs[i].val = in.nextInt();
        }
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vs[u].vs.add(v);
            vs[v].vs.add(u);
        }
        Num.primeTable(MAX, new ArrayList<Integer>());
        build(vs);
        while (q-- != 0) {
            if (in.nextInt() == 1) {
                int v = in.nextInt() - 1;
                out.println(vs[v].ans + 1);
            } else {
                int v = in.nextInt() - 1;
                int val = in.nextInt();
                vs[v].val = val;
                build(vs);
            }
        }
    }
    final int MAX = (int) (2 * 1e6 + 10);
    void build(V[] vs) {
        int[] ds = new int[MAX];
        int[] id = new int[MAX];
        Arrays.fill(id, -2);
        build(vs, ds, id, 0, -1, 1);
    }

    private void build(V[] vs, int[] ds, int[] id, int u, int fa, int dep) {
        List<Long> ps = Num.primeFactors(vs[u].val);
        int d = -1;
        vs[u].ans = -2;
        for (long p : ps) {
            int ip = (int) p;
            if (ds[ip] > d) {
                d = ds[ip];
                vs[u].ans = id[ip];
            }
        }
        Map<Integer, Integer> dsc = new HashMap<Integer, Integer>();
        Map<Integer, Integer> ids = new HashMap<Integer, Integer>();
        for (long p : ps) {
            int ip = (int) p;
            if (ds[ip] < dep) {
                dsc.put(ip, ds[ip]);
                ids.put(ip, id[ip]);
                ds[ip] = dep;
                id[ip] = u;
            }
        }
        for (int v : vs[u].vs) if (v != fa) {
            build(vs, ds, id, v, u, dep + 1);
        }
        for (Map.Entry<Integer, Integer> e : ids.entrySet()) {
            int ip = e.getKey();
            id[ip] = e.getValue();
            ds[ip] = dsc.get(ip);
        }
    }

    class V {
        List<Integer> vs = new ArrayList<Integer>();
        int val;
        int ans;
    }
}
