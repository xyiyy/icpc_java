package main;

import com.shu_mj.tpl.Algo;
import net.egork.chelper.tester.Verdict;
import net.egork.chelper.checkers.Checker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskEChecker implements Checker {

    private String ln = System.lineSeparator();

    public TaskEChecker(String parameters) {
    }

    public Verdict check(String input, String expectedOutput, String actualOutput) {
        Scanner in = new Scanner(input);
        Scanner out = new Scanner(actualOutput);
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            if (!ok(in, n, out)) return Verdict.WA;
        }
        return Verdict.OK;
    }

    private boolean ok(Scanner in, int n, Scanner out) {
        int[] v = new int[n];
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = in.nextInt();
            r[i] = in.nextInt();
        }
        StringBuilder sb = new StringBuilder();
        for (;;) {
            String line = out.nextLine();
            sb.append(line + ln);
            if (line.equals("END")) break;
        }
        out.nextLine();
        sb.append(n + ln);
        for (int i = 0; i < n; i++) {
            sb.append(v[i] + ln);
        }
        sb.append(ln + "QUIT" + ln);
        return Arrays.equals(r, calc(sb.toString()));
    }

    private int[] calc(String input) {
        Scanner in = new Scanner(input);
        List<String> ss = new ArrayList<String>();
        String s = in.next();
        if (!s.equals("NUM")) ss.add(s);
        if (!s.equals("END")) for (;;) {
            s = in.next();
            if (!s.equals("NUM")) ss.add(s);
            if (s.equals("END")) break;
        }
        return solve(in, ss);
    }

    private int[] solve(Scanner in, List<String> ss) {
        int n = in.nextInt();
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            long x = in.nextInt();
            is[i] = calc(ss, x);
        }
        return is;
    }

    private int calc(List<String> ss, long x) {
        long[] st = new long[1000];
        int top = 0;
        st[top++] = x;
        try {
            for (String s : ss) {
                if (s.equals("END")) break;
                if (s.equals("POP")) {
                    top--;
                } else if (s.equals("INV")) {
                    st[top - 1] *= -1;
                } else if (s.equals("DUP")) {
                    st[top] = st[top - 1];
                    top++;
                } else if (s.equals("SWP")) {
                    long t = st[top - 2];
                    st[top - 2] = st[top - 1];
                    st[top - 1] = t;
                } else if (s.equals("ADD")) {
                    long a = 0;
                    a += st[--top];
                    a += st[--top];
                    st[top++] = a;
                } else if (s.equals("SUB")) {
                    long b = st[--top];
                    long a = st[--top];
                    a -= b;
                    st[top++] = a;
                } else if (s.equals("MUL")) {
                    long b = st[--top];
                    long a = st[--top];
                    a *= b;
                    st[top++] = a;
                } else if (s.equals("DIV")) {
                    long b = st[--top];
                    long a = st[--top];
                    a /= b;
                    st[top++] = a;
                } else if (s.equals("MOD")) {
                    long b = st[--top];
                    long a = st[--top];
                    a %= b;
                    st[top++] = a;
                } else { // NUM
                    st[top++] = Long.parseLong(s);
                }
                if (top > 0 && Math.abs(st[top - 1]) > (long) (1e9))
                    throw new IndexOutOfBoundsException();
            }
            if (top != 1) throw new IndexOutOfBoundsException();
            return (int) st[0];
        } catch (Throwable e) {
            return -1;
        }
    }
}
