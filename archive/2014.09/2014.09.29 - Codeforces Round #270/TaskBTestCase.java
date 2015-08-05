package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskBTestCase {

    Random in = new Random();
    String ln = System.lineSeparator();

    @TestCase
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<Test>();
        for (int i = 0; i < 100; i++) {
            list.add(gen());
        }
        return list;
    }

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int n = in.nextInt(200) + 1;
        int k = in.nextInt(20) + 1;
        int[] is = new int[n];
        input.append(n + " " + k + ln);
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt(200) + 1;
            input.append(is[i] + " ");
        }
        input.append(ln);
        Arrays.sort(is);
        int ans = 0;
        for (int i = 0; i < n; i++) is[i]--;
        ans += is[n - 1] * 2;
        for (int i = n - k - 1; i >= 0; i -= k) ans += is[i] * 2;
        output.append(ans + ln);
        return new Test(input.toString(), output.toString());
    }
}
