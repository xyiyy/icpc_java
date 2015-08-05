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
        for (;;) {
            String s = in.nextLine();
            if (s == null) break;
            for (int i = 0; i < s.length(); i++) {
                if (i + 6 <= s.length()) {
                    if (s.substring(i, i + 6).equals("iPhone")) {
                        out.println("MAI MAI MAI!");
                    }
                }
                if (i + 5 <= s.length()) {
                    if (s.substring(i, i + 5).equals("Apple")) {
                        out.println("MAI MAI MAI!");
                    }
                }
                if (i + 4 <= s.length()) {
                    String ss = s.substring(i, i + 4);
                    if (ss.equals("iPod") || ss.equals("iPad")) {
                        out.println("MAI MAI MAI!");
                    }
                }
                if (i + 4 <= s.length()) {
                    if (s.substring(i, i + 4).equals("Sony")) {
                        out.println("SONY DAFA IS GOOD!");
                    }
                }
            }
        }
    }
}
