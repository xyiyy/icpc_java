package main;

import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int cs = 1;
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            out.printf("Case %d: ", cs++);
            solve(n);
        }
    }

    private void solve(int n) {
        char[][] css = new char[n][];
        for (int i = 0; i < n; i++) {
            css[i] = in.next().toCharArray();
        }
        AhoCorasick(css);
        char[] cs = in.next().toCharArray();
        int l = cs.length;
        for (int i = 0; i <= l; i++) {
            Queue<Node> que = new LinkedList<Node>();
            if (i > 0) {
                que.add(root);
                while (!que.isEmpty()) {
                    Node t = que.poll();
                    if (t.accept) continue;
                    for (int j = 0; j < 4; j++) {
//                        Algo.debug(i, root, t, t.next[j], t.next[j].nxt);
                        t.next[j].nxt = Math.min(t.next[j].nxt, AGCT[j] == cs[i - 1] ? t.crt : t.crt + 1);
//                        Algo.debug(i, root, t, t.next[j], t.next[j].nxt);
                    }
                    for (Map.Entry<Character, Node> e : t.cs.entrySet()) {
                        Node u = e.getValue();
                        que.add(u);
                    }
                }
            }
            que.add(root);
            while (!que.isEmpty()) {
                Node t = que.poll();
                if (i == 0) {
                    if (t == root) t.nxt = 1;
                    else t.nxt = 0;
                }
                t.crt = t.nxt;
                t.nxt = INF;
                for (Map.Entry<Character, Node> e : t.cs.entrySet()) {
                    Node u = e.getValue();
                    que.add(u);
                }
            }
        }
        int ans = INF;
        Queue<Node> que = new LinkedList<Node>();
        que.add(root);
        while (!que.isEmpty()) {
            Node t = que.poll();
            if (t.accept) continue;
//            Algo.debug(root, t, t.crt);
            ans = Math.min(ans, t.crt);
            for (Map.Entry<Character, Node> e : t.cs.entrySet()) {
                Node u = e.getValue();
                que.add(u);
            }
        }
        out.println(ans == INF ? -1 : ans);
    }

    final int INF = 12341234;
    char[] AGCT = "AGCT".toCharArray();
    Node root;
    int m;
    void AhoCorasick(char[][] ps) {
        m = ps.length;
        root = new Node();
        for (int i = 0; i < m; i++) {
            Node t = root;
            for (char c : ps[i]) {
                if (!t.cs.containsKey(c)) t.cs.put(c, new Node());
                t = t.cs.get(c);
            }
            t.accept = true;
        }
        Queue<Node> que = new LinkedList<Node>();
        que.offer(root);
        while (!que.isEmpty()) {
            Node t = que.poll();
            for (Map.Entry<Character, Node> e : t.cs.entrySet()) {
                char c = e.getKey();
                Node u = e.getValue();
                que.offer(u);
                Node r = t.fail;
                while (r != null && !r.cs.containsKey(c)) r = r.fail;
                if (r == null) u.fail = root;
                else u.fail = r.cs.get(c);
            }
        }
        que.offer(root);
        while (!que.isEmpty()) {
            Node t = que.poll();
            outer: for (char[] cs : ps) {
                Node r = t;
                for (char c : cs) {
                    if (r.cs.containsKey(c)) r = r.cs.get(c);
                    else continue outer;
                }
                r.accept = true;
            }
            for (int i = 0; i < 4; i++) {
                char c = AGCT[i];
                Node r = t;
                while (r != null && !r.cs.containsKey(c)) r = r.fail;
                if (r == null) t.next[i] = root;
                else t.next[i] = r.cs.get(c);
            }
            for (Map.Entry<Character, Node> e : t.cs.entrySet()) {
                Node u = e.getValue();
                que.offer(u);
            }
        }
    }

    static class Node {
        Map<Character, Node> cs = new TreeMap<Character, Node>();
        boolean accept;
        Node fail;
        Node[] next = new Node[4];
        int crt;
        int nxt;
    }
}
