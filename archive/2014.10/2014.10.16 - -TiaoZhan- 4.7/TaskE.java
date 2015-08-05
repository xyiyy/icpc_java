package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        Set<String> all = new HashSet<String>();
        Set<String> finish = new HashSet<String>();
        all.add("");
        int maxLength = 1;
        for (int i = 0; i < m; i++) {
            String s = in.next();
            maxLength = Math.max(maxLength, s.length());
            finish.add(s);
            for (int j = 0; j < s.length(); j++) {
                all.add(s.substring(0, j + 1));
            }
        }
        int last = 0;
        Map<String, Integer> index = new HashMap<String, Integer>();
        for (String s : all) {
            index.put(s, last++);
        }
        int[] isFinish = new int[last];
        for (String s : all) {
            for (String t : finish) {
                if (s.endsWith(t)) {
                    isFinish[index.get(s)] = Math.max(isFinish[index.get(s)], t.length());
                }
            }
        }
        int[][] next = new int[last][4];
        for (String s : all) {
            int start = index.get(s);
            for (int ch = 0; ch < 4; ch++) {
                String t = s + "AGCT".charAt(ch);
                while (!all.contains(t)) {
                    t = t.substring(1);
                }
                next[start][ch] = index.get(t);
            }
        }
        int[][] cnt = new int[last][maxLength];
        cnt[index.get("")][0] = 1;
        for (int len = 0; len < n; len++) {
            cnt = oneStep(cnt, maxLength, last, isFinish, next);
        }
        int res = 0;
        for (int state = 0; state < last; state++) {
            res += cnt[state][0];
            if (res >= M) res -= M;
        }
        out.println(res);
    }

    private int[][] oneStep(int[][] cnt, int maxLength, int last, int[] finish, int[][] next) {
        int[][] newCnt = new int[last][maxLength];
        for (int oldState = 0; oldState < last; oldState++) {
            for (int oldNeed = 0; oldNeed < maxLength; oldNeed++) {
                for (int ch = 0; ch < 4; ch++) {
                    int newState = next[oldState][ch];
                    int newNeed = oldNeed + 1;
                    if (newNeed <= finish[newState]) newNeed = 0;
                    if (newNeed >= maxLength) continue;
                    newCnt[newState][newNeed] += cnt[oldState][oldNeed];
                    if (newCnt[newState][newNeed] >= M) newCnt[newState][newNeed] -= M;
                }
            }
        }
        return newCnt;
    }

    int M = 1000000009;
}
