package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        long d = in.nextInt();
        long[] is = in.nextLongArray(n);
        V[] dp = new V[n];
        dp[0] = new V(1, -1);
        T t = new T(is[0], new V(1, 0));
        for (int i = 1; i < n; i++) {
            dp[i] = new V(1, -1);
            T[] ts = splitKey(t, is[i] - d);
            if (ts[0] != NULL) {
                V v = ts[0].max;
                if (dp[i].val < v.val + 1) {
                    dp[i].val = v.val + 1;
                    dp[i].id = v.id;
                }
            }
            T[] ts1 = splitKey(ts[1], is[i] + d - 1);
            if (ts1[1] != NULL) {
                V v = ts1[1].max;
                if (dp[i].val < v.val + 1) {
                    dp[i].val = v.val + 1;
                    dp[i].id = v.id;
                }
            }
            t = merge(ts[0], merge(ts1[0], ts1[1]));
            if (dp[i].val > 0) t = add(t, is[i], new V(dp[i].val, i));
        }
        int first = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i].val > dp[first].val) first = i;
        }
        int cnt = dp[first].val;
        out.println(cnt);
        int[] ps = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            ps[cnt - i - 1] = first;
            first = dp[first].id;
        }
        for (int i = 0; i < cnt; i++) {
            out.print((ps[i] + 1) + " ");
        }
        out.println();
    }

    private T add(T t, long k, V v) {
        T[] ts = splitKey(t, k);
        T[] ts0 = splitKey(ts[0], k - 1);
        if (ts0[1] == NULL) return merge(ts0[0], merge(new T(k, v), ts[1]));
        ts0[1].val = max(ts0[1].val, v);
        ts0[1].max = ts0[1].val;
        return merge(merge(ts0[0], ts0[1]), ts[1]);
    }

    class V {
        int val;
        int id;

        V(int val, int id) {
            this.val = val;
            this.id = id;
        }
    }

    V max(V a, V b) {
        if (a.val > b.val) return a;
        return b;
    }

    class T {
        long key;
        V val;
        V max;
        int size;
        double p;
        T left;
        T right;

        T(long key, V val, int size, double p, T left, T right) {
            this.key = key;
            this.val = val;
            this.max = val;
            this.size = size;
            this.p = p;
            this.left = left;
            this.right = right;
        }

        T(long key, V val) {
            this(key, val, 1, Math.random(), NULL, NULL);
        }

        T change(T left, T right) {
            size = left.size + right.size + 1;
            max = max(max(left.max, right.max), val);
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

    T NULL = new T(0, new V(-12341234, -1), 0, 0, null, null);

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

    T[] splitKey(T t, long key) {
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
