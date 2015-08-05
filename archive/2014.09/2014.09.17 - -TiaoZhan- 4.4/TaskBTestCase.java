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
        input.append(1 + ln);
        int n = in.nextInt(10) + 1;
        int k = in.nextInt(n) + 1;
        input.append(n + " " + k + ln);
        int[] is = new int[n];
        for (int i = 0; i < n; i++) is[i] = in.nextInt(10);
        Arrays.sort(is);
        for (int i = 0; i < n; i++) input.append(is[i] + " ");
        input.append(ln);
        int[] S = new int[n + 1];
        for (int i = 0; i < n; i++) S[i + 1] = is[i] + S[i];
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = 12341234;
            for (int j = 0; j <= i - k; j++) {
                dp[i] = Math.min(dp[i], dp[j] + S[i] - S[j] - is[j] * (i - j));
            }
        }
        output.append(dp[n] + ln);
        return new Test(input.toString(), output.toString());
    }
}
