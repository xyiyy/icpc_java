package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task3826 {
    Scanner in;
    PrintWriter out;
    private String input;
    private Map<P, Map<String, P>> kvSet;
    private P root;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0) {
            solve();
            System.gc();
        }
    }

    private void solve() {
        input = in.next();
        kvSet = new HashMap<P, Map<String, P>>();
        root = build(0, input.length());
        int q = in.nextInt();
        while (q-- != 0) {
            String s = in.next();
            out.println(query(s));
        }
    }

    private String query(String q) {
        String[] ss = q.split("\\.");
        P r = root;
        for (String s : ss) {
            if (!kvSet.containsKey(r) || !kvSet.get(r).containsKey(s)) return "Error!";
            r = kvSet.get(r).get(s);
        }
        return r.toString();
    }

    private P build(int b, int e) {
        P res = new P(b, e);
        if (input.charAt(b) == '{') {
            Map<String, P> map = new HashMap<String, P>();
            b++;
            while (b < e) {
                int mid = input.indexOf(':', b);
                if (mid < 0 || mid >= e) break;
                int end = getEnd(mid + 1);
                map.put(input.substring(b, mid), build(mid + 1, end));
                b = end + 1;
            }
            kvSet.put(res, map);
        }
        return res;
    }

    private int getEnd(int s) {
        if (input.charAt(s) == '\"') {
            return input.indexOf('\"', s + 1) + 1;
        }
        int n = 0;
        for (;;) {
            if (input.charAt(s) == '{') n++;
            if (input.charAt(s) == '}') n--;
            if (n == 0) return s + 1;
            s++;
        }
    }

    class P {
        int x;
        int y;

        P(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return input.substring(x, y);
        }
    }
}
