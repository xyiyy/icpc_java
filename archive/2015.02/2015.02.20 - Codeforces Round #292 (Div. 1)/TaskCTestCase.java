package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskCTestCase {

    Random in = new Random(2);
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
        int n = 4;
        int m = 1;
        input.append(n + " " + m + ln);
        int[] ds = new int[n];
        int[] hs = new int[n];
        for (int i = 0; i < n; i++) {
            ds[i] = in.nextInt(10) + 1;
            hs[i] = in.nextInt(10) + 1;
        }
        for (int i = 0; i < n; i++) {
            input.append(ds[i] + " ");
        }
        input.append(ln);
        for (int i = 0; i < n; i++) {
            input.append(hs[i] + " ");
        }
        input.append(ln);
        int l = in.nextInt(n) + 1;
        int r = in.nextInt(n) + 1;
        while (l > r && l + (n - r + 1) > n - 2 || l < r && r - l + 1 > n - 2) {
            l = in.nextInt(n) + 1;
            r = in.nextInt(n) + 1;
        }
        input.append(l + " " + r + ln);
        int res = 0;
        l--;
        r--;
        if (l <= r) l += n;
        r++;
        for (int i = r; i < l; i++) {
            for (int j = i + 1; j < l; j++) {
                int s = 2 * (hs[i < n ? i : i - n] + hs[j < n ? j : j - n]);
                for (int k = i; k < j; k++) {
                    s += ds[k < n ? k : k - n];
                }
                res = Math.max(res, s);
            }
        }
        output.append(res + ln);
        return new Test(input.toString(), output.toString());
    }
}
