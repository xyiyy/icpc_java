package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskMatTestCase {

    Random in = new Random();
    String ln = System.lineSeparator();

    @TestCase
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<Test>();
        for (int i = 0; i < 1; i++) {
            list.add(gen());
        }
        return list;
    }

    final int MAX = 1000;
    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        input.append(1 + ln);
        int n = in.nextInt(MAX) + 1;
        int m = in.nextInt(MAX) + 1;
        int q = in.nextInt(MAX) + 1;
        n = MAX;
        m = MAX;
        q = MAX;
        input.append(n + " " + m + " " + q + ln);
//        long[][] mat = new long[n][m];

        for (int i = 0; i < q; i++) {
            int x0 = in.nextInt(n) + 1;
            int x1 = in.nextInt(n) + 1;
            int y0 = in.nextInt(m) + 1;
            int y1 = in.nextInt(m) + 1;
            int val = in.nextInt(MAX) + 1;
            if (x0 > x1) { int t = x0; x0 = x1; x1 = t; }
            if (y0 > y1) { int t = y0; y0 = y1; y1 = t; }
            if (in.nextBoolean()) { // sum
                input.append("Sum " + x0 + " " + y0 + " " + x1 + " " + y1 + ln);
//                output.append(sum(mat, x0, y0, x1, y1) + ln);
            } else { // add
                input.append("Add " + x0 + " " + y0 + " " + x1 + " " + y1 + " " + val + ln);
//                add(mat, x0, y0, x1, y1, val);
            }
        }
//        return new Test(input.toString(), output.toString());
        return new Test(input.toString());
    }

    private void add(long[][] mat, int x0, int y0, int x1, int y1, int val) {
        x0--;
        y0--;
        for (int i = x0; i < x1; i++) {
            for (int j = y0; j < y1; j++) {
                mat[i][j] += val;
            }
        }
    }

    private long sum(long[][] mat, int x0, int y0, int x1, int y1) {
        x0--;
        y0--;
        long res = 0;
        for (int i = x0; i < x1; i++) {
            for (int j = y0; j < y1; j++) {
                res += mat[i][j];
            }
        }
        return res;
    }
}
