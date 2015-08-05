package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        String[] ss = new String[n];
        for (int i = 0; i < n; i++) {
            ss[i] = in.next();
        }
        boolean[][] less = new boolean[26][26];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int u = ss[i].length(), v = ss[j].length();
                int k = 0;
                while (k < u && k < v && ss[i].charAt(k) == ss[j].charAt(k)) k++;
                if (k == v) {
                    out.println("Impossible");
                    return ;
                }
                if (k != u) {
                    less[ss[i].charAt(k) - 'a'][ss[j].charAt(k) - 'a'] = true;
                }
            }
        }
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    less[i][j] |= less[i][k] && less[k][j];
                }
            }
        }
        for (int i = 0; i < 26; i++) {
            if (less[i][i]) {
                out.println("Impossible");
                return ;
            }
        }
        int[] d = new int[26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (less[i][j]) d[j]++;
            }
        }
        for (int i = 0; i < 26; i++) {
            for (int c = 0; c < 26; c++) {
                if (d[c] == 0) {
                    out.print((char) (c + 'a'));
                    d[c]--;
                    for (int j = 0; j < 26; j++) {
                        if (less[c][j]) {
                            d[j]--;
                        }
                    }
                    break;
                }
            }
        }
        out.println();
    }
}
