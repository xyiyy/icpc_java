package main;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class GreaterGame {
    public double calc(int[] hand, int[] sothe) {
        int n = hand.length;
        TreeSet<Integer> set = new TreeSet<Integer>();
        for (int i : hand) set.add(i);
        int[] use = new int[n];
        for (int i = 0; i < n; i++) {
            if (sothe[i] != -1) {
                if (set.higher(sothe[i]) == null) {
                    use[i] = set.first();
                } else {
                    use[i] = set.higher(sothe[i]);
                }
                set.remove(use[i]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (sothe[i] == -1) {
                use[i] = set.first();
                set.remove(use[i]);
            }
        }
        Set<Integer> notUse = new HashSet<Integer>();
        for (int i = 1; i <= n * 2; i++) notUse.add(i);
        for (int i : hand) notUse.remove(i);
        for (int i : sothe) if (i != -1) notUse.remove(i);
        double[][] dp = new double[n][n * 2 + 1];
        Algo.fill(dp, -12341234);
        if (sothe[0] == -1) {
            for (int i : notUse) dp[0][i] = use[0] > i ? 1 : 0;
        } else {
            dp[0][sothe[0]] = use[0] > sothe[0] ? 1 : 0;
        }
        int[] s = new int[n * 2 + 1];
        for (int i : notUse) s[i]++;
        for (int i = 1; i <= n * 2; i++) s[i] += s[i - 1];
        double ans = 0;
        for (int i = 0; i < n; i++) {
            if (sothe[i] == -1) {
                ans += s[use[i]] * 1.0 / s[n * 2];
            } else {
                ans += use[i] > sothe[i] ? 1 : 0;
            }
        }
        return ans;
    }
}
