package main;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class ShoppingSurveyDiv1 {
    public int minValue(int N, int K, int[] s) {
        int l = 0;
        int r = N + 2;
        while (l < r) {
            int m = (l + r) / 2;
            if (fit(N, K, s, m)) r = m;
            else l = m + 1;
        }
        return l;
    }

    private boolean fit(int n, int k, int[] s, int m) {
        s = s.clone();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < s.length; j++) if (s[j] > 0) s[j]--;
        }
        if (m >= n) {
            for (int i : s) if (i > 0) return false;
            return true;
        }
        int[] cnt = new int[n - m];
        Arrays.sort(s);
        int crt = 0;
        for (int i : s) {
            if (i > n - m) return false;
            while (i > 0) {
                cnt[crt++]++;
                i--;
                if (crt == n - m) crt = 0;
            }
        }
        for (int i : cnt) if (i >= k) return false;
        return true;
    }
}
