package main;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class CatsOnTheLineDiv1 {
    public int getNumber(int[] position, int[] count, int time) {
        int n = position.length;
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) ps[i] = new P(position[i], count[i]);
        Arrays.sort(ps, new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                return o1.pos - o2.pos;
            }
        });
        return 0;
    }

    private boolean fit(P[] ps, int time, int m, int n) {
        int rightPos = -Integer.MAX_VALUE;
        int gap = 0;

        for (P p : ps) {
            int crtGap = m - p.cnt % m;
            int left = p.pos - time;
            int right = p.pos + time;
            if (left <= rightPos) {
                crtGap = Math.max(0, crtGap + gap - m);
            } else {
                rightPos = left - 1;
            }
            int crtRight = rightPos + (p.cnt + m - 1) / m;
            if (crtGap == 0) crtRight--;
            if (crtRight > right) return false;
            gap = crtGap;
            rightPos = crtRight;
        }
        return true;
    }

    class P {
        int pos;
        int cnt;

        P(int pos, int cnt) {
            this.pos = pos;
            this.cnt = cnt;
        }
    }
}
