package main;

import com.shu_mj.math.Num;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskDTestCase {

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

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        input.append("1" + ln);
        int n = in.nextInt(30000) + 1;
        int m = in.nextInt(100) + 1;
        int[] is = new int[n];
        input.append(n + " " + m + ln);
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt(30000) + 1;
            input.append(is[i] + " ");
        }
        input.append(ln);
        for (int i = 0; i < m; i++) {
            int l = in.nextInt(n) + 1;
            int r = in.nextInt(n) + 1;
            if (l > r) { int t = l; l = r; r = t; }
            input.append(l + " " + r + ln);
            output.append(calc(is, l - 1, r) + ln);
        }
        return new Test(input.toString(), output.toString());
    }

    final int M = 1000000007;
    private long calc(int[] is, int l, int r) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = l; i < r; i++) {
            Num.inc(map, is[i]);
        }
        long res = Num.factorial(r - l, M);
        for (int i : map.values()) {
            res *= Num.inv(Num.factorial(i, M), M);
            res %= M;
        }
        return res;
    }
}
