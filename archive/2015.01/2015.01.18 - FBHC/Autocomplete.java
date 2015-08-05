package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Autocomplete {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            out.printf("Case #%d: ", i + 1);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        String[] ss = new String[n];
        for (int i = 0; i < n; i++) {
//            if (in.hasNext())
                ss[i] = in.next();
        }
        Dic dic = new Dic();
        int res = 0;
        for (String s : ss) {
            if (s == null) continue;
            dic.insert(s);
            res += dic.getAns(s);
        }
        out.println(res);
    }
    class Dic {
        Node root = new Node();
        void insert(String s) {
            Node crt = root;
            for (char c : s.toCharArray()) {
                if (crt.cs[c - 'a'] == null) {
                    crt.cs[c - 'a'] = new Node();
                }
                crt = crt.cs[c - 'a'];
                crt.cnt++;
            }
        }
        int getAns(String s) {
            Node crt = root;
            int times = 0;
            for (char c : s.toCharArray()) {
                times++;
                crt = crt.cs[c - 'a'];
                if (crt.cnt == 1) return times;
            }
            return times;
        }
    }
    class Node {
        Node[] cs = new Node[26];
        int cnt = 0;
    }
}
