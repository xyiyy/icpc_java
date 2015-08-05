package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskATestCase {

    Random in = new Random();
    String ln = System.lineSeparator();

    @TestCase
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<Test>();
        for (int i = 0; i < 1000; i++) {
            list.add(gen());
        }
        return list;
    }

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int n = in.nextInt(5) + 1;
        String[] ss = new String[n];
        input.append(n + ln);
        for (int i = 0; i < n; i++) {
            int m = in.nextInt(5) + 1;
            char[] cs = new char[m];
            for (int j = 0; j < m; j++) {
                cs[j] = "abc".charAt(in.nextInt(3));
            }
            ss[i] = String.valueOf(cs);
            input.append(ss[i] + ln);
        }
        output.append(solve(n, ss) + ln);
        return new Test(input.toString(), output.toString());
    }

    String solve(int n, String[] ss) {
        boolean[][] less = new boolean[26][26];
        for (int i = 0; i + 1 < n; i++) {
            int j = i + 1;
            int u = ss[i].length(), v = ss[j].length();
            int k = 0;
            while (k < u && k < v && ss[i].charAt(k) == ss[j].charAt(k)) k++;
            if (k == v) {
                return "Impossible";
            }
            if (k != u) {
                less[ss[i].charAt(k) - 'a'][ss[j].charAt(k) - 'a'] = true;
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
                return "Impossible";
            }
        }
        int[] d = new int[26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (less[i][j]) d[j]++;
            }
        }
        StringBuilder sb = new StringBuilder(26);
        for (int i = 0; i < 26; i++) {
            for (int c = 0; c < 26; c++) {
                if (d[c] == 0) {
                    sb.append((char) (c + 'a'));
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
        return sb.toString();
    }
}
