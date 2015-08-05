package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Algo;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskETestCase {

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
        int m = in.nextInt(4) + 1;
        int n = in.nextInt(4) + 1;
        String[] ss = new String[m];
        input.append(m + " " + n + ln);
        for (int i = 0; i < m; i++) {
            int len = in.nextInt(4) + 1;
            String s = "";
            for (int j = 0; j < len; j++) {
                s += "AGCT".charAt(in.nextInt(4));
            }
            ss[i] = s;
            input.append(s + ln);
        }
        long mod = 100000;
        List<String> pfx = new ArrayList<String>();
        for (String s : ss) {
            for (int j = 0; j <= s.length(); j++) {
                pfx.add(s.substring(0, j));
            }
        }
        Collections.sort(pfx);
        String[] pf = Algo.unique(pfx.toArray(new String[0]));
        int K = pf.length;
        boolean[] ng = new boolean[K];
        int[][] next = new int[K][4];
        for (int i = 0; i < K; i++) {
            ng[i] = false;
            for (int j = 0; j < m; j++) {
                ng[i] |= ss[j].length() <= pf[i].length()
                        && pf[i].substring(pf[i].length() - ss[j].length(), pf[i].length()).equals(ss[j]);
            }
            for (int j = 0; j < 4; j++) {
                String s = pf[i] + "AGCT".charAt(j);
                int k;
                for (;;) {
                    k = Algo.lowerBound(pf, s);
                    if (k < K && pf[k].equals(s)) break;
                    s = s.substring(0, s.length() - 1);
                }
                next[i][j] = k;
            }
        }
        int[][] dp = new int[n + 1][K];
        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < K; j++) {
                for (int c = 0; c < 4; c++) {
                    if (!ng[next[j][c]]) dp[i + 1][next[j][c]] += dp[i][j];
                }
            }
        }
        output.append(Algo.sum(dp[n]) + ln);
        return new Test(input.toString(), output.toString());
    }
}
