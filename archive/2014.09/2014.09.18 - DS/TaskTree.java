package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskTree {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n - 1);
        T[] ts = new T[n];
        for (int i = 0; i < n; i++) ts[i] = new T(i);
        for (int i = 0; i < n - 1; i++) {
            insert(ts[is[i]], ts[i + 1]);
        }
        inOrder(ts[0]); out.println();
        preOrder(ts[0]); out.println();
    }

    private void preOrder(T t) {
        if (t != null) {
            out.print(t.id + " ");
            preOrder(t.left);
            preOrder(t.right);
        }
    }

    private void inOrder(T t) {
        if (t != null) {
            inOrder(t.left);
            out.print(t.id + " ");
            inOrder(t.right);
        }
    }

    private void insert(T t, T s) {
        if (t.left == null) t.left = s;
        else {
            t = t.left;
            while (t.right != null) t = t.right;
            t.right = s;
        }
    }

    class T {
        int id;
        T left;
        T right;

        T(int id, T left, T right) {
            this.id = id;
            this.left = left;
            this.right = right;
        }
        T(int id) {
            this(id, null, null);
        }
    }
}
