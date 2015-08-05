package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskFTestCase {

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
        int n = in.nextInt(10) + 1;
        int k = in.nextInt(n) + 1;
        int[] is = new int[n];
        for (int i = 0; i < n; i++) is[i] = in.nextInt(10);
        input.append(n + " " + k + ln);
        for (int i = 0; i < n; i++) {
            input.append(is[i] + " " );
        }
        input.append(ln);
        getMin(n, k, is, 1, output);
        for (int i = 0; i < n; i++) is[i] = -is[i];
        getMin(n, k, is, -1, output);
        return new Test(input.toString(), output.toString());
    }

    private void getMin(int n, int k, int[] is, int x, StringBuilder out) {
        int[] deq = new int[n];
        int s = 0, t = 0;
        for (int i = 0; i < n; i++) {
            while (t - s > 0 && is[i] <= deq[t - 1]) t--;
            deq[t++] = is[i];
            if (i >= k - 1) {
                if (i == k - 1) out.append(deq[s] * x);
                else out.append(" " + deq[s] * x);
                if (deq[s] == is[i - k + 1]) s++;
            }
        }
        out.append(ln);
    }

}
