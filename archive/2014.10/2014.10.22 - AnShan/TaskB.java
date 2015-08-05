package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;
    private int top;
    private ArrayList<Integer> que;
    private Map<Integer, Integer> words;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0) solve();
    }

    private void solve() {
        int n = in.nextInt();
        top = -1;
        que = new ArrayList<Integer>();
        words = new HashMap<Integer, Integer>();
        for (int i = 1; i <= n; i++) {
            String type = in.next();
            out.printf("Operation #%d: ", i);
            if (type.equals("Add")) {
                int u = in.nextInt();
                if (que.contains(u)) {
                    out.println("same priority.");
                } else {
                    que.add(u);
                    out.println("success.");
                }
            } else if (type.equals("Close")) {
                int u = in.nextInt();
                if (!que.contains(u)) {
                    out.println("invalid priority.");
                } else {
                    int w = 0;
                    if (words.containsKey(u)) {
                        w = words.get(u);
                        words.remove(u);
                    }
                    que.remove((Integer) u);
                    out.println("close " + u + " with " + w + ".");
                    if (top == u) top = -1;
                }
            } else if (type.equals("Chat")) {
                int w = in.nextInt();
                if (que.isEmpty()) {
                    out.println("empty.");
                } else {
                    if (top != -1) {
                        inc(top, w);
                    } else {
                        inc(que.get(0), w);
                    }
                    out.println("success.");
                }
            } else if (type.equals("Rotate")) {
                int x = in.nextInt();
                if (que.size() < x) {
                    out.println("out of range.");
                } else {
                    x--;
                    int k = que.get(x);
                    que.remove(x);
                    que.add(0, k);
                    out.println("success.");
                }
            } else if (type.equals("Prior")) {
                if (que.isEmpty()) {
                    out.println("empty.");
                } else {
                    int x = maxIndex(que);
                    int k = que.get(x);
                    que.remove(x);
                    que.add(0, k);
                    out.println("success.");
                }
            } else if (type.equals("Choose")) {
                int u = in.nextInt();
                if (!que.contains(u)) {
                    out.println("invalid priority.");
                } else {
                    que.remove((Integer) u);
                    que.add(0, u);
                    out.println("success.");
                }
            } else if (type.equals("Top")) {
                int u = in.nextInt();
                if (!que.contains(u)) {
                    out.println("invalid priority.");
                } else {
                    top = u;
                    out.println("success.");
                }
            } else if (type.equals("Untop")) {
                if (top != -1) {
                    top = -1;
                    out.println("success.");
                } else {
                    out.println("no such person.");
                }
            } else {
                for (; ; ) ;
            }
        }
        if (top != -1) {
            if (words.containsKey(top)) {
                out.println("Bye " + top + ": " + words.get(top));
            }
            que.remove((Integer) top);
        }
        while (!que.isEmpty()) {
            if (words.containsKey(que.get(0))) {
                out.println("Bye " + que.get(0) + ": " + words.get(que.get(0)));
            }
            que.remove(0);
        }
    }

    private int maxIndex(ArrayList<Integer> que) {
        int id = -1;
        for (int i = 0; i < que.size(); i++) {
            if (id == -1 || que.get(i) > que.get(id)) id = i;
        }
        return id;
    }

    private void inc(int k, int v) {
        if (words.containsKey(k)) words.put(k, v + words.get(k));
        else words.put(k, v);
    }
}
