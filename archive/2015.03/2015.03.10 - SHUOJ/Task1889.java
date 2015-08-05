package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1889 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        T t = build(0, n);
//        Algo.debug(t.size);
        while (m-- != 0) {
            int tp = in.nextInt();
            int l = in.nextInt() - 1;
            int r = in.nextInt();
            if (tp == 1) {
                T[] ts = splitSize(t, r);
                T[] ts0 = splitSize(ts[0], l);
                out.println(ts0[1].sum);
                t = merge(merge(ts0[0], ts0[1]), ts[1]);
            } else {
                int x = in.nextInt();
                if (x <= l) {
                    T[] ts = splitSize(t, r);
                    T[] ts0 = splitSize(ts[0], l);
                    T left = ts0[0], mid = ts0[1], right = ts[1];
                    ts = splitSize(left, x);
                    t = merge(ts[0], merge(mid, merge(ts[1], right)));
                } else {
                    T[] ts = splitSize(t, x);
                    T rt = ts[1]; t = ts[0];
//                    Algo.debug(t.size, r, x);
                    ts = splitSize(t, r);
                    T[] ts0 = splitSize(ts[0], l);
                    T left = ts0[0], mid = ts0[1], right = ts[1];
                    t = merge(left, merge(right, merge(mid, rt)));
                }
            }
        }
    }

    T build(int s, int t) {
        if (t - s <= 0) return NULL;
        else {
            int m = (s + t) / 2;
            T l = build(s, m);
            T mid = new T(in.nextInt());
            T r = build(m + 1, t);
            mid.p = Math.min(l.p, r.p) - 1;
            return mid.change(l, r);
        }
    }


    class T {
        int key, sum, size;
        double p;
        T left, right;

        T(int key, int sum, int size, double p, T left, T right) {
            this.key = key;
            this.sum = sum;
            this.size = size;
            this.p = p;
            this.left = left;
            this.right = right;
        }

        T(int key) {
            this(key, key, 1, Math.random(), NULL, NULL);
        }

        T change(T left, T right) {
            size = left.size + right.size + 1;
            sum = left.sum + right.sum + key;
            this.left = left;
            this.right = right;
            return this;
        }

        T push() {
            if (this != NULL) {

            }
            return this;
        }
    }

    T NULL = new T(0, 0, 0, 0, null, null);

    T[] splitSize(T t, int size) {
        T[] res;
        if (size <= 0) {
            res = new T[] { NULL, t };
        } else if (size <= t.push().left.size) {
            res = splitSize(t.left, size);
            res[1] = t.change(res[1], t.right);
        } else {
            res = splitSize(t.right, size - t.left.size - 1);
            res[0] = t.change(t.left, res[0]);
        }
        return res;
    }

    T[] splitKey(T t, int key) {
        T[] res;
        if (t == NULL) {
            res = new T[] { NULL, NULL };
        } else if (key < t.push().key) {
            res = splitKey(t.left, key);
            res[1] = t.change(res[1], t.right);
        } else {
            res = splitKey(t.right, key);
            res[0] = t.change(t.left, res[0]);
        }
        return res;
    }

    void print(T t, String indent) {
        if (t != NULL) {
            print(t.push().right, indent + "      ");
            System.err.printf("%s%3d%3d%n", indent, t.key, t.size);
            print(t.left, indent + "      ");
        }
        if (indent.length() == 0)
            System.err.println("----------------------------------");
    }

    T merge(T t1, T t2) {
        if (t1 == NULL) return t2;
        if (t2 == NULL) return t1;
        if (t1.p < t2.p) return t1.push().change(t1.left, merge(t1.right, t2));
        return t2.push().change(merge(t1, t2.left), t2.right);
    }


}
