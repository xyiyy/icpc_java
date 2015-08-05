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
        int m = in.nextInt();
        int[] qt = new int[m];
        int[] qx = new int[m];
        int[] qy = new int[m];
        List<List<Integer>> qs = new ArrayList<List<Integer>>();
        qs.add(new ArrayList<Integer>());
        for (int i = 0; i < m; i++) {
            qt[i] = in.nextInt();
            qx[i] = in.nextInt();
            if (qt[i] != 2) qy[i] = in.nextInt();
            else qs.add(new ArrayList<Integer>());
            if (qt[i] == 3) {
                qs.get(qy[i]).add(i);
            }
        }
        boolean[] ans = new boolean[m];
        T[] ts = new T[n + 1];
        for (int i = 1; i <= n; i++) ts[i] = new T(i);
        int c = 1;
        for (int i = 0; i < m; i++) {
            if (qt[i] == 1) {
                access(ts[qx[i]]).pre = ts[qy[i]];
            } else if (qt[i] == 2) {
                for (int j : qs.get(c)) {
                    if (ts[qx[i]] == ts[qx[j]]) {
                        ans[j] = true;
                        continue;
                    }
                    access(ts[qx[i]]);
                    access(ts[qx[j]]);

                    T t = ts[qx[i]];
                    while (t.pre.left == t || t.pre.right == t) {
                        t = t.pre;
                    }
                    ans[j] = t.pre == ts[qx[j]];
                }
                c++;
            }
        }
        for (int i = 0; i < m; i++) {
            if (qt[i] == 3) {
                out.println(ans[i] ? "YES" : "NO");
            }
        }
    }

    class T {
        int id;
        boolean rev;
        double p;
        T pre;
        T left;
        T right;

        T(int id, boolean rev, double p, T pre, T left, T right) {
            this.id = id;
            this.rev = rev;
            this.p = p;
            this.pre = pre;
            this.left = left;
            this.right = right;
        }

        T(int id) {
            this(id, false, Math.random(), NULL, NULL, NULL);
        }

        T change(T left, T right) {
            this.left = left; left.pre = this;
            this.right = right; right.pre = this;
            return this;
        }

        T setRev() {
            if (this == NULL) return NULL;
            rev ^= true;
            T t = left; left = right; right = t;
            return this;
        }

        T push() {
            if (rev) {
                left.setRev();
                right.setRev();
                rev ^= true;
            }
            return this;
        }
    }

    T merge(T t1, T t2) {
        if (t1 == NULL) return t2;
        if (t2 == NULL) return t1;
        if (t1.p < t2.p) return t1.push().change(t1.left, merge(t1.right, t2));
        return t2.push().change(merge(t1, t2.left), t2.right);
    }

    T[] split(T t) {
        pushDownAllMark(t);
        T[] res = new T[2];
        res[1] = t.right;
        res[0] = t.change(t.left, NULL);
        T tcp = t;
        for (;;) {
            if (t.pre.left == t) {
                t = t.pre;
                res[1] = t.change(res[1], t.right);
            } else if (t.pre.right == t) {
                t = t.pre;
                res[0] = t.change(t.left, res[0]);
            } else {
                res[0].pre = t.pre;
                res[1].pre = tcp;
                return res;
            }
        }
    }

    T access(T t) {
        T last = NULL;
        while (t != NULL) {
            T[] ss = split(t);
            t = ss[0].pre;
            last = merge(ss[0], last);
        }
        last.pre = NULL;
        return last;
    }

    T makeRoot(T t) {
        return access(t).setRev();
    }

    T getRoot(T t) {
        t = access(t);
        while (t.push().left != NULL) t = t.left;
        return t;
    }
    void link(T x, T y) {
        makeRoot(x).pre = y;
    }

    void cut(T x, T y) {
        makeRoot(y);
        access(y);
        while (x.pre.left == x || x.pre.right == x) x = x.pre;
        x.pre = NULL;
    }

    private void pushDownAllMark(T t) {
        if (t.pre.left == t || t.pre.right == t) pushDownAllMark(t.pre);
        t.push();
    }

    T NULL = new T(0);

}
