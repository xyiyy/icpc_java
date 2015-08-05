package com.shu_mj.ds;

/**
 * Created by Jun on 8/8/2014.
 */
public class LCT {

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

    void pushDownAllMark(T t) {
        if (t.pre.left == t || t.pre.right == t) pushDownAllMark(t.pre);
        t.push();
    }

    T NULL = new T(0);

}
