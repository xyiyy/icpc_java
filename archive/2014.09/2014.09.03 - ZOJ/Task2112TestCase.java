package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class Task2112TestCase {

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

    final int VALUE = 10;
    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        input.append(1 + ln);
        int n = in.nextInt(10) + 1;
        int m = in.nextInt(10) + 1;
        int[] is = new int[n];
        input.append(n + " " + m + ln);
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt(VALUE);
            input.append(is[i] + " ");
        }
        input.append(ln);

        while (m-- != 0) {
            char c = "QC".charAt(in.nextInt(2));
            input.append(c + " ");
            if (c == 'Q') {
                int s = in.nextInt(n) + 1;
                int t = in.nextInt(n) + 1;
                if (s > t) { int tmp = s; s = t; t = tmp; }
                int k = in.nextInt(t - s + 1) + 1;
                input.append(s + " " + t + " " + k + ln);
                s--;
                int[] ts = new int[t - s];
                for (int i = s; i < t; i++) ts[i - s] = is[i];
                Arrays.sort(ts);
                output.append(ts[k - 1] + ln);
            } else {
                int k = in.nextInt(n) + 1;
                int a = in.nextInt(VALUE);
                input.append(k + " " + a + ln);
                is[k - 1] = a;
            }
        }
        return new Test(input.toString(), output.toString());
    }
}
