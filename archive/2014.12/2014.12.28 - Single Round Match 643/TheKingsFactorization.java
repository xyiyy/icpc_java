package main;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TheKingsFactorization {
    public long[] getVector(long N, long[] primes) {
        List<Long> ls = new ArrayList<Long>();
        for (long l : primes) {
            ls.add(l);
            N /= l;
        }
        for (long i = 2; i * i <= N && i < 1e6; i++) {
            while (N % i == 0) {
                ls.add(i);
                N /= i;
            }
        }
        if (N != 1) ls.add(N);
        Collections.sort(ls);
        return Algo.unBox(ls.toArray(new Long[0]));
    }
}
