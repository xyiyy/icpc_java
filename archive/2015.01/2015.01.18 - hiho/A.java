package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class A {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        while (in.hasNext()) run();
    }

    void run() {
        String s = in.next();
        out.println(dfs(s, 0, s.length()) ? "yes" : "no");
    }

    private boolean dfs(String s, int b, int e) {
//        Algo.debug(b, e);
        if (e - b == 0) return false;
        if (b + 1 == e) {
            return s.charAt(b) == '0' || s.charAt(b) == '1';
        } else {
            if (s.charAt(b) != '(') {
                if (!dfs(s, b, b + 1)) return false;
                int n = b + 1;
                while (n < e && s.charAt(n) == '*') n++;
                if (n == e) return true;
                if (s.charAt(n) == '|') return dfs(s, n + 1, e);
                return dfs(s, n, e);
            } else {
                int id = e;
                int crt = 1;
                for (int i = b + 1; i < e; i++) {
                    if (s.charAt(i) == '(') crt++;
                    if (s.charAt(i) == ')') crt--;
                    if (crt == 0) {
                        id = i;
                        break;
                    }
                }
                if (id == e) return false;
                if (!dfs(s, b + 1, id)) return false;
                int n = id + 1;
                while (n < e && s.charAt(n) == '*') n++;
                if (n == e) return true;
                if (s.charAt(n) == '|') return dfs(s, n + 1, e);
                return dfs(s, n, e);
            }
        }
    }
}
