package main;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class PeriodicJumping {
    public int minimalTime(int x, int[] is) {
        int n = is.length;
        long all = Algo.sum(is);
        x = Math.abs(x);
        long k = x / all;
        x %= all;
        if (k != 0) {
            long sum = 0;
            for (int i = 0; ; i++) {
                if (sum >= x) return (int) (k * n + i);
                sum += is[i];
            }
        }
        if (x == 0) return 0;
        for (int i = 1; ; i++) {
            if (fit(x, Arrays.copyOf(is, i))) return i;
            if (i % n == 0) {
                long sum = 0;
                for (int j = 0; ; j++) {
                    if (sum >= x) return n + j;
                    sum += is[j];
                }
            }
        }
    }

    private boolean fit(int x, int[] is) {
        Arrays.sort(is);
        int l = 0;
        int r = 0;
        int n = is.length;
        for (int i = 0; i < n - 1; i++) {
            l -= is[i];
            r += is[i];
        }
        l += is[n - 1];
        r += is[n - 1];
        return l <= x && x <= r;
    }
}
