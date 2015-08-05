package main;



import com.shu_mj.math.Num;
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
        int[] is = in.nextIntArray(6);
        TreeMap<Integer, Integer> cnt = new TreeMap<Integer, Integer>();
        for (int i : is) {
            Num.inc(cnt, i);
        }
        if (cnt.size() == 1) {
            out.println("Elephant");
        } else if (cnt.size() == 2) {
            int a = cnt.firstEntry().getValue();
            int b = cnt.lastEntry().getValue();
            if (a == 2 || b == 2) out.println("Elephant");
            else if (a == 1 || b == 1) out.println("Bear");
            else out.println("Alien");
        } else if (cnt.size() == 3) {
            int[] cs = new int[3];
            int i = 0;
            for (Map.Entry<Integer, Integer> es : cnt.entrySet()) {
                cs[i++] = es.getValue();
            }
            if ((cs[0] == 1 || cs[1] == 1) && (cs[0] == 4 || cs[1] == 4 || cs[2] == 4)) {
                out.println("Bear");
            } else {
                out.println("Alien");
            }
        } else {
            out.println("Alien");
        }
    }
}
