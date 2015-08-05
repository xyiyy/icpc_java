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
        for (int i = 0; i < 10; i++) {
            list.add(gen());
        }
        return list;
    }

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int n = in.nextInt(10) + 5;
        int m = in.nextInt(10) + 5;
        int t = in.nextInt(10) + 1;
        int p = in.nextInt(n / 2) + 1;
        int q = in.nextInt(m / 2) + 1;
        input.append(n + " " + m + " " + t + " " + p + " " + q + ln);
        char[][] maps = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maps[i][j] = "*0".charAt(in.nextInt(2));
                input.append(maps[i][j]);
            }
            input.append(ln);
        }
        input.append(ln);
        char[][][] csss = new char[t][p][q];
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < q; k++) {
                    csss[i][j][k] = "*0".charAt(in.nextInt(2));
                    input.append(csss[i][j][k]);
                }
                input.append(ln);
            }
            input.append(ln);
        }
        input.append("0 0 0 0 0");
        int cnt = 0;
        if (p <= n && q <= m)
        outer : for (char[][] css : csss) {
            for (int i = p - 1; i < n; i++) {
                for (int j = q - 1; j < m; j++) {
                    if (fit(maps, i, j, p, q, css)) {
                        cnt++;
                        continue outer;
                    }
                }
            }
        }
        output.append("Case 1: " + cnt + ln);
        return new Test(input.toString(), output.toString());
    }

    private boolean fit(char[][] maps, int x, int y, int p, int q, char[][] css) {
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < q; j++) {
                if (maps[x - i][y - j] != css[p - i - 1][q - j - 1]) return false;
            }
        }
        return true;
    }
}
