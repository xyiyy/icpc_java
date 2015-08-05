package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: wormhole
     */
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) ps[i] = new P(i, in.nextInt(), in.nextInt());
        out.println(dfs(ps, 0));
    }

    private int dfs(P[] ps, int crt) {
        if (crt > 0 && crt % 2 == 0 && ps[crt - 2].id > ps[crt - 1].id) return 0;
        if (crt > 2 && crt % 2 == 1 && ps[crt - 3].id > ps[crt - 1].id) return 0;
        if (crt == ps.length) {
            if (fit(ps)) {
                return 1;
            } else {
                return 0;
            }
        } else {
            int res = 0;
            for (int i = crt; i < ps.length; i++) {
                Algo.swap(ps, crt, i);
                res += dfs(ps, crt + 1);
                Algo.swap(ps, crt, i);
            }
            return res;
        }
    }

    private boolean fit(P[] ps) {
        for (P p : ps) if (fit(ps, p.x, p.y)) {
            return true;
        }
        return false;
    }

    private boolean fit(P[] ps, int x, int y) {
        int n = ps.length;
        boolean[] vis = new boolean[n];
        for (;;) {
            int id = -1;
            for (int i = 0; i < n; i++) {
                if (ps[i].y == y && ps[i].x > x) {
                    if (id == -1 || ps[i].x < ps[id].x) {
                        id = i;
                    }
                }
            }
            if (id == -1) return false;
            if (vis[id]) return true;
            vis[id] = true;
            id ^= 1;
            x = ps[id].x;
            y = ps[id].y;
        }
    }

    class P {
        int id;
        int x;
        int y;

        P(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "P{" +
                    "id=" + id +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

}
