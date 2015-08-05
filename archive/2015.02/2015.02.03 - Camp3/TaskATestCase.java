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
        for (int i = 0; i < 10; i++) {
            list.add(gen());
        }
        return list;
    }

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int n = in.nextInt(1000) + 1;
        int q = in.nextInt(1000) + 1;
        input.append(n + " " + q + ln);
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt(10000) + 1;
            input.append(is[i] + " ");
        }
        input.append(ln);
        while (q-- > 0) {
            int t = in.nextInt(4) + 1;
            int l = in.nextInt(n) + 1;
            int r = in.nextInt(n) + 1;
            if (l > r) { int u = l; l = r; r = u; }
            input.append(t + " " + l + " " + r);
            if (t == 4) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for (int i = l - 1; i < r; i++) {
                    min = Math.min(min, is[i]);
                    max = Math.max(max, is[i]);
                }
                output.append(min + " " + max + ln);
            } else {
                int c = in.nextInt(2000);
                input.append(" " + c);
                for (int i = l - 1; i < r; i++) {
                    if (t == 1) {
                        is[i] += c;
                    } else if (t == 2) {
                        is[i] = Math.min(is[i], c);
                    } else {
                        is[i] = Math.max(is[i], c);
                    }
                }
            }
            input.append(ln);
        }
        return new Test(input.toString(), output.toString());
    }
}
