package main;

import com.shu_mj.tpl.PII;
import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class JanuszTheBusinessmanTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        List<NewTopCoderTest> list = new ArrayList<NewTopCoderTest>();
        for (int i = 0; i < 5; i++) {
            list.add(gen());
        }
        return list;
    }

    Random r = new Random();
    private NewTopCoderTest gen() {
        int n = 50;
        Set<PII> set = new HashSet<PII>();
        int[] fr = new int[n];
        int[] to = new int[n];
        int D = 15;
        for (int i = 0; i < n; i++) {
            for (;;) {
                int x = r.nextInt(365 - D * 2) + 1 + D;
                int y = r.nextInt(D * 2) + 1 - D + x;
                if (y == x) y = x + 1;
                if (y < x) { int t = x; x = y; y = t; }
                PII p = new PII(x, y);
                if (x >= 1 && x <= 365 && y >= 1 && y <= 365 && !set.contains(p)) {
                    set.add(p);
                    fr[i] = x;
                    to[i] = y;
                    break;
                }
            }
        }
        int res = 0;
        return new NewTopCoderTest(new Object[] { fr, to }, res);
    }
}
