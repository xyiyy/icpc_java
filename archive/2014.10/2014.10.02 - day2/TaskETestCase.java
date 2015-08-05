package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskETestCase {

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
        List<Integer> ls = new ArrayList<Integer>();
        for (int i = 0; i < 11; i++) ls.add(i);
        Collections.shuffle(ls);
        input.append(n + ln);
        for (int i = 0; i < n; i++) {
            input.append(ls.get(i) + " " + in.nextInt(21) + ln);
        }
        input.append(ln + "0" + ln);
        return new Test(input.toString(), output.toString());
    }
}
