package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
//        int[] is = in.nextIntArray(n);
        Seg seg = new Seg(n);
        int q = in.nextInt();
        while (q-- != 0) {
            char t = in.next().charAt(0);
            int x = in.nextInt() - 1;
            int y = in.nextInt();
            if (t == 'C') {
//                is[x] = y;
                seg.update(x, y);
            } else {
                y--;
//                int crt = 0;
//                for (int i = x; i < y; i++) {
//                    if (crt % is[i] == 0) crt++;
//                    crt++;
//                }
//                out.println(crt);
                out.println(seg.query(x, y));
            }
        }
    }
    class Seg {
        int N;
        int[] as;
        int[] is;

        public Seg(int n) {
            N = Integer.highestOneBit(n) << 1;
            as = new int[N];
            for (int i = 0; i < N; i++) {
                if (i < n) as[i] = in.nextInt();
                else as[i] = 6;
            }
            is = new int[N * 2 * 60];
            for (int i = N * 2 - 1; i > 0; i--) {
                for (int j = 0; j < 60; j++) {
                    if (i < N) {
                        int l = is[i * 2 * 60 + j];
                        is[i * 60 + j] = l + is[(i * 2 + 1) * 60 + (l + j) % 60];
                    } else {
                        if (j % as[i - N] == 0) is[i * 60 + j] = 2;
                        else is[i * 60 + j] = 1;
                    }
                }
            }
        }
        void update(int a, int v) {
            as[a] = v;
            for (int i = a + N; i > 0; i >>= 1) {
                for (int j = 0; j < 60; j++) {
                    if (i < N) {
                        int l = is[i * 2 * 60 + j];
                        is[i * 60 + j] = l + is[(i * 2 + 1) * 60 + (j + l) % 60];
                    } else {
                        if (j % v == 0) is[i * 60 + j] = 2;
                        else is[i * 60 + j] = 1;
                    }
                }
            }
        }
        int[] os = new int[128];
        int query(int s, int t) {
            int l = 0;
            while (0 < s && s + (s & -s) <= t) {
                int i = (N + s) / (s & -s);
                os[l++] = i;
                s += s & -s;
            }
            int r = 128;
            while (s < t) {
                int i = (N + t) / (t & -t) - 1;
                os[--r] = i;
                t -= t & -t;
            }
            int res = 0;
            for (int i = 0; ; i++) {
                if (i == l) i = r;
                if (i >= 128) break;
                res += is[os[i] * 60 + res % 60];
            }
            return res;
        }
    }
}
