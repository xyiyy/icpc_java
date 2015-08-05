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
            String line = in.nextLine();
            if (line == null) break;
            out.println(line.replaceAll("(?i)marshtomp", "fjxmlhx"));
        }
    }
}
