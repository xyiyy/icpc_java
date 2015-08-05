package main;



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
        int n = in.nextInt();
        int p = in.nextInt();
        char[] cs = in.next().toCharArray();
        if (p == 1) {
            out.println("NO");
            return ;
        }
        if (p == 2) {
            if (cs.length == 1) {
                if (cs[0] == 'a') out.println('b');
                else out.println("NO");
            } else {
                if (cs[0] == 'a') out.println("ba");
                else out.println("NO");
            }
            return ;
        }
        if (dfs(cs, n - 1, p)) out.println(cs);
        else out.println("NO");
    }

    private boolean dfs(char[] cs, int i, int p) {
        if (i == 0) {
            if (cs[i] == 'a' + p - 1) return false;
            else {
                cs[i]++;
                return true;
            }
        } else if (i == 1) {
            if (cs[i] == 'a' + p - 1 || cs[i] == 'a' + p - 2 && cs[i - 1] == 'a' + p - 1) {
                if (dfs(cs, i - 1, p)) {
                    cs[i] = 'a';
                    while (cs[i] == cs[i - 1]) cs[i]++;
                    return true;
                } else return false;
            }
            do {
                cs[i]++;
            } while (cs[i] == cs[i - 1]);
            return true;
        } else {
            char a = (char) ('a' + p - 3);
            char b = (char) ('a' + p - 2);
            char c = (char) ('a' + p - 1);
            if (cs[i] == c || cs[i] == b && (cs[i - 1] == c || cs[i - 2] == c) || cs[i] == a && (cs[i - 1] == b || cs[i - 2] == b) && (cs[i - 1] == c || cs[i - 2] == c)) {
                if (dfs(cs, i - 1, p)) {
                    cs[i] = 'a';
                    while (cs[i - 1] == cs[i] || cs[i - 2] == cs[i]) cs[i]++;
                    return true;
                } else {
                    return false;
                }
            }
            do {
                cs[i]++;
            } while (cs[i] == cs[i - 1] || cs[i] == cs[i - 2]);
            return true;
        }
    }
}
