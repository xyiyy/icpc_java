package main;





import com.shu_mj.ds.UnionFindSet;
import com.shu_mj.graph.SCC;
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
        int a = in.nextInt();
        int b = in.nextInt();
        int[] is = in.nextIntArray(n);
        Map<Integer, Integer> id = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            id.put(is[i], i);
        }
        SCC.V[] vs = new SCC.V[n * 2];
        for (int i = 0; i < vs.length; i++) vs[i] = new SCC.V();
        for (int i = 0; i < n; i++) {
            if (id.containsKey(a - is[i])) {
                int j = id.get(a - is[i]);
                vs[i].add(vs[j]);
                vs[j + n].add(vs[i + n]);
            } else {
                vs[i].add(vs[i + n]);
            }
            if (id.containsKey(b - is[i])) {
                int j = id.get(b - is[i]);
                vs[i + n].add(vs[j + n]);
                vs[j].add(vs[i]);
            } else {
                vs[i + n].add(vs[i]);
            }
        }
        SCC.scc(vs);
        for (int i = 0; i < n; i++) {
            if (vs[i].comp == vs[i + n].comp) {
                out.println("NO");
                return ;
            }
        }
        out.println("YES");
        for (int i = 0; i < n; i++) {
            if (vs[i].comp > vs[i + n].comp) {
                out.print("0 ");
            } else {
                out.print("1 ");
            }
        }
        out.println();
    }
}
