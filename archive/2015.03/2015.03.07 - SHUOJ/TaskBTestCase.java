package main;

import com.shu_mj.tpl.Algo;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskBTestCase {

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
        input.append("1" + ln);
        int n = in.nextInt(100) + 1;
        int k = in.nextInt(100);
        input.append(n + " " + k + ln);
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt(100);
            input.append(is[i] + " ");
        }
        input.append(ln);
        output.append("Case #1: ");
        output.append(fit(n, k, is) ? "Yes." : "No.");
        output.append(ln);
        return new Test(input.toString(), output.toString());
    }

    private boolean fit(int n, int k, int[] is) {
        for (int i = 0; i < n; i++) {
            for (int j = i, o = 1, s = 0; j < n; j++, o *= -1) {
                s += o * is[j];
                if (s == k) {
                    return true;
                }
            }
        }
        return false;
    }

}
