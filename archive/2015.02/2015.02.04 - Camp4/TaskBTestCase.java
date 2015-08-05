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
        for (int i = 0; i < 0; i++) {
            list.add(gen());
        }
        return list;
    }

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int n = in.nextInt(1000) + 1;
        int m = in.nextInt(1000) + 1;
        int q = in.nextInt(10) + 1;
        input.append(n + " " + m + " " + q + ln);
        for (int i = 0; i < n; i++) {

        }
        return new Test(input.toString(), output.toString());
    }
}
