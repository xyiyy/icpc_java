package main;
import java.util.*;
import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.PII;

public class JanuszTheBusinessman {
    public int makeGuestsReturn(int[] arrivals, int[] departures) {
        int n = arrivals.length;
        PII[] ps = new PII[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new PII(arrivals[i], departures[i]);
        }
        Arrays.sort(ps, new Comparator<PII>() {
            @Override
            public int compare(PII o1, PII o2) {
                return o1.y - o2.y;
            }
        });
        int res = 0;
        for (int i = 0; i < n; ) {
            res++;
            int x = ps[i].y;
            int r = ps[i].y;
            for (int j = i; j < n; j++) {
                if (ps[j].x <= x) r = Math.max(r, ps[j].y);
            }
            while (i < n && ps[i].x <= r) i++;
        }
        return res;
    }

}
