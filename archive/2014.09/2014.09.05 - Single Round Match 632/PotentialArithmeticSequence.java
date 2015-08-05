package main;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class PotentialArithmeticSequence {

    private int[] tp;

    public int numberOfSubsequences(int[] d) {
        int cnt = 0;
        int n = d.length;
        tp = new int[55];
        for (int i = 0; i < 55; i++) {
            tp[i] = Integer.numberOfTrailingZeros((i + 1));
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (fit(d, i, j)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private boolean fit(int[] d, int b, int e) {
        int n = e - b + 1;
        int[] is = new int[n];
        for (int i = b; i <= e; i++) is[i - b] = d[i];
        int maxId = Algo.maxIndex(is);
        if (Algo.count(is, is[maxId]) > 1) return false;
        int[] left = new int[maxId];
        int[] right = new int[n - maxId - 1];
        for (int i = 0; i < maxId; i++) left[i] = is[i];
        for (int i = maxId + 1; i < n; i++) right[i - maxId - 1] = is[i];
        Algo.reverse(left);
        return fit(left) && fit(right);
    }

    private boolean fit(int[] is) {
        int n = is.length;
        if (n == 0) return true;
        for (int i = 0; i < n; i++) {
            if (is[i] != tp[i]) return false;
        }
        return true;
    }
}
