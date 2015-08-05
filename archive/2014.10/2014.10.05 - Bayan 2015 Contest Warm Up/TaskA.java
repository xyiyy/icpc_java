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

    String[] ss = {
            "+------------------------+",
            "|#.#.#.#.#.#.#.#.#.#.#.|D|)",
            "|#.#.#.#.#.#.#.#.#.#.#.|.|",
            "|#.......................|",
            "|#.#.#.#.#.#.#.#.#.#.#.|.|)",
            "+------------------------+"
    };
    void run() {
        int k = in.nextInt();
        char[][] css = new char[6][];
        for (int i = 0; i < 6; i++) css[i] = ss[i].toCharArray();
        outer : for (int j = 0, c = 0; ; j++) {
            if (c == k) break;
            for (int i = 0; i < 6; i++) {
                if (css[i][j] == '#') {
                    css[i][j] = 'O';
                    c++;
                }
                if (c == k) break outer;
            }
        }
        for (char[] cs : css) out.println(cs);
    }
}
