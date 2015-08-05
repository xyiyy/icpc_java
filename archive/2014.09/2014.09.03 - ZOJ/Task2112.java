package main;

import com.shu_mj.ds.MatSum;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task2112 {
    Scanner in;
    PrintWriter out;

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
        int m = in.nextInt();
        int[] ns = new int[n];
        int[] is = new int[n + m];
        for (int i = 0; i < n; i++) {
            ns[i] = is[i] = in.nextInt();
        }
        int[] ss = new int[m];
        int[] ts = new int[m];
        int[] ks = new int[m];
        char[] cs = new char[m];
        for (int i = 0; i < m; i++) {
            cs[i] = in.next().charAt(0);
            ss[i] = in.nextInt();
            ts[i] = in.nextInt();
            if (cs[i] == 'Q') {
                ks[i] = in.nextInt();
            } else {
                is[i + n] = ts[i];
            }
        }
        Arrays.sort(is);
        is = Algo.unique(is);
        Mat mat = new Mat(n, is.length);
        for (int i = 0; i < n; i++) {
            mat.insert(i, ns[i] = Algo.lowerBound(is, ns[i]));
        }
        for (int i = 0; i < m; i++) {
            if (cs[i] == 'Q') {
                int s = ss[i] - 1;
                int t = ts[i];
                int k = ks[i];
                int l = 0, r = is.length;
                // x 是第 k 小: <= x 的数目 >= k
                while (l < r) {
                    int mid = (l + r) / 2;
                    if (mat.query(s, t, mid + 1) < k) l = mid + 1;
                    else r = mid;
                }
                out.println(is[l]);
            } else {
                int k = ss[i] - 1;
                int a = Algo.lowerBound(is, ts[i]);
                mat.remove(k, ns[k]);
                mat.insert(k, ns[k] = a);
            }
        }
    }

    class M {
        M X0;
        M X1;
        M Y0;
        M Y1;
        int x0;
        int x1;
        int y0;
        int y1;
        int cnt;

        M(M xx0, M xx1, M yy0, M yy1, int x01, int x11, int y01, int y11, int cnt) {
            X0 = xx0;
            X1 = xx1;
            Y0 = yy0;
            Y1 = yy1;
            x0 = x01;
            x1 = x11;
            y0 = y01;
            y1 = y11;
            this.cnt = cnt;
        }
        M(int x0, int x1, int y0, int y1) {
            this(null, null, null, null, x0, x1, y0, y1, 0);
        }

        @Override
        public String toString() {
            return "M{" +
                    ", x0=" + x0 +
                    ", x1=" + x1 +
                    ", y0=" + y0 +
                    ", y1=" + y1 +
                    ", cnt=" + cnt +
                    '}';
        }
    }
    class Mat {
        T[] ts;

        Mat(int x, int y) {
            ts = new T[x + 1];
            Arrays.fill(ts, NULL);
        }

        void insert(int x, int y) {
            for (int i = x + 1; i < ts.length; i += i & -i) {
                T[] ss = splitKey(ts[i], y);
                ts[i] = merge(merge(ss[0], new T(y, 0)), ss[1]);
            }
        }

        void remove(int x, int y) {
            for (int i = x + 1; i < ts.length; i += i & -i) {
                T[] ss = splitKey(ts[i], y);
                T[] ss0 = splitSize(ss[0], ss[0].size - 1);
                ts[i] = merge(ss0[0], ss[1]);
            }
        }

        int query(int s, int t, int y) {
            if (s > 0) return query(0, t, y) - query(0, s, y);
            int res = 0;
            for (int i = t; i > 0; i -= i & -i) {
                T[] ss = splitKey(ts[i], y - 1);
                res += ss[0].size;
                ts[i] = merge(ss[0], ss[1]);
            }
            return res;
        }
    }
    class Mat4 {
        M root;
        int N;
        Mat4(int x, int y) {
            N = Integer.highestOneBit(Math.max(x, y)) << 1;
            root = new M(0, N, 0, N);
        }

        void insert(int x, int y) {
            M m = root;
            for (;;) {
                m.cnt++;
                if (m.x0 + 1 == m.x1 && m.y0 + 1 == m.y1) break;
                int x2 = (m.x0 + m.x1) / 2;
                int y2 = (m.y0 + m.y1) / 2;
                if (x < x2 && y < y2) {
                    if (m.X0 == null) m.X0 = new M(m.x0, x2, m.y0, y2);
                    m = m.X0;
                } else if (x >= x2 && y < y2) {
                    if (m.X1 == null) m.X1 = new M(x2, m.x1, m.y0, y2);
                    m = m.X1;
                } else if (x >= x2 && y >= y2) {
                    if (m.Y1 == null) m.Y1 = new M(x2, m.x1, y2, m.y1);
                    m = m.Y1;
                } else if (x < x2 && y >= y2) {
                    if (m.Y0 == null) m.Y0 = new M(m.x0, x2, y2, m.y1);
                    m = m.Y0;
                } else {
                    throw new RuntimeException();
                }
            }
        }

        void remove(int x, int y) {
            M m = root;
            for (;;) {
                m.cnt--;
                if (m.x0 + 1 == m.x1 && m.y0 + 1 == m.y1) break;
                int x2 = (m.x0 + m.x1) / 2;
                int y2 = (m.y0 + m.y1) / 2;
                if (x < x2 && y < y2) {
                    m = m.X0;
                } else if (x >= x2 && y < y2) {
                    m = m.X1;
                } else if (x >= x2 && y >= y2) {
                    m = m.Y1;
                } else if (x < x2 && y >= y2) {
                    m = m.Y0;
                } else {
                    throw new RuntimeException();
                }
            }
        }

        int query(int s, int t, int y) {
            return query(root, t, y) - query(root, s, y);
        }

        int query(M m, int x, int y) {
            if (m == null) return 0;
            if (m.x1 <= x && m.y1 <= y) return m.cnt;
            if (m.x0 >= x || m.y0 >= y) return 0;
            return query(m.X0, x, y) +
                    query(m.X1, x, y) +
                    query(m.Y0, x, y) +
                    query(m.Y1, x, y);
        }
    }

    class Mat3 {
        int B = 500;
        int W;
        int H;
        MatSum cnt;
        List<P>[][] mat;

        Mat3(int x, int y) {
            W = (x + B - 1) / B;
            H = (y + B - 1) / B;
            cnt = new MatSum(W, H);
            mat = new List[W][H];
        }
        void insert(int x, int y) {
            cnt.add(x / B, y / B, 1);
            if (mat[x / B][y / B] == null) mat[x / B][y / B] = new ArrayList<P>();
            mat[x / B][y / B].add(new P(x, y));
        }
        void remove(int x, int y) {
            cnt.add(x / B, y / B, -1);
            for (int j = 0; j < H; j++) {
                int i = x / B;
                for (int k = 0; mat[i][j] != null && k < mat[i][j].size(); k++) {
                    if (mat[i][j].get(k).x == x) {
                        mat[i][j].remove(k);
                        return ;
                    }
                }
            }
            throw new RuntimeException();
        }
        int query(int s, int t, int y) {
            if (s > 0) return query(0, t, y) - query(0, s, y);
            int x = t;
            int res = cnt.sum(0, 0, x / B, y / B);
            for (int i = 0; i < x / B; i++) {
                int j = y / B;
                if (mat[i][j] != null) {
                    for (P p : mat[i][j]) {
                        if (p.y < y) res++;
                    }
                }
            }
            for (int j = 0; j < y / B; j++) {
                int i = x / B;
                if (mat[i][j] != null) {
                    for (P p : mat[i][j]) {
                        if (p.x < x) res++;
                    }
                }
            }
            int i = x / B, j = y / B;
            if (mat[i][j] != null) {
                for (P p : mat[i][j]) {
                    if (p.x < x && p.y < y) res++;
                }
            }
            return res;
        }
    }

    class P {
        int x;
        int y;

        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    class Mat2 {
        int N;
        int B = 1000;
        int BS;
        T[] ts;
        Mat2(int n) {
            this.N = n;
            BS = (N + B - 1) / B;
            ts = new T[BS];
            Arrays.fill(ts, NULL);
        }
        void remove(int x) {
            int xi = x / B;
            id = 0;
            T[] ss = new T[ts[xi].size];
            split(ts[xi], ss);
            ts[xi] = NULL;
            for (T t : ss) {
                if (t.val == x) continue;
                ts[xi] = merge(ts[xi], t);
            }
        }
        void insert(int x, int y) {
            int xi = x / B;
            T[] ss = splitKey(ts[xi], y);
            ts[xi] = merge(ss[0], merge(new T(y, x), ss[1]));
        }

        int query(int s, int t, int y) {
            if (s > 0) return query(0, t, y) - query(0, s, y);
            int res = 0;
            for (int i = 0; i < t / B; i++) {
                T[] ss = splitKey(ts[i], y);
                res += ss[0].size;
                ts[i] = merge(ss[0], ss[1]);
            }
            T[] ss = splitKey(ts[t / B], y);
            res += dfs(ss[0], t);
            ts[t / B] = merge(ss[0], ss[1]);
            return res;
        }

    }

    int id;
    private void split(T t, T[] ss) {
        if (t == NULL) return ;
        split(t.left, ss);
        T right = t.right;
        ss[id++] = t.change(NULL, NULL);
        split(right, ss);
    }

    private int dfs(T t, int x) {
        if (t == NULL) return 0;
        int res = 0;
        if (t.val < x) res++;
        res += dfs(t.left, x);
        res += dfs(t.right, x);
        return res;
    }

    public static class T {
        public int key, val, size;
        public double p;
        public T left, right;

        public T(int key, int val, int size, double p, T left, T right) {
            this.key = key;
            this.val = val;
            this.size = size;
            this.p = p;
            this.left = left;
            this.right = right;
        }

        public T(int key, int val) {
            this(key, val, 1, Math.random(), NULL, NULL);
        }

        public T change(T left, T right) {
            size = left.size + right.size + 1;
            this.left = left;
            this.right = right;
            return this;
        }
        public T push() {
            if (this != NULL) {

            }
            return this;
        }
    }

    public static final T NULL = new T(0, 0, 0, 0, null, null);

    public static T[] splitSize(T t, int size) {
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

    public static T[] splitKey(T t, int key) {
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

    public static void print(T t, String indent) {
        if (t != NULL) {
            print(t.push().right, indent + "      ");
            System.err.printf("%s%3d%3d%n", indent, t.key, t.size);
            print(t.left, indent + "      ");
        }
        if (indent.length() == 0)
            System.err.println("----------------------------------");
    }

    public static T merge(T t1, T t2) {
        if (t1 == NULL) return t2;
        if (t2 == NULL) return t1;
        if (t1.p < t2.p) return t1.push().change(t1.left, merge(t1.right, t2));
        return t2.push().change(merge(t1, t2.left), t2.right);
    }

}
