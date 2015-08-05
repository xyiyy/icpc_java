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
        for (int i = 0; i < 0; i++) {
            list.add(gen());
        }
        return list;
    }

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int n = in.nextInt(1000000) + 1;
        int m = in.nextInt(13) + 1;
        int[] is = new int[m];
        for (int i = 0; i < m; i++) is[i] = in.nextInt(19) + 1;
        input.append(n + " " + m + ln);
        for (int i = 0; i < m; i++) {
            input.append(is[i] + " ");
        }
        input.append(ln);

        return new Test(input.toString(), output.toString());
    }
}
