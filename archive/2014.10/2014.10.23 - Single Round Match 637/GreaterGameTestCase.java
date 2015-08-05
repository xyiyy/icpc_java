package main;

import com.shu_mj.tpl.Algo;
import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class GreaterGameTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        List<NewTopCoderTest> res = new LinkedList<NewTopCoderTest>();
        for (int i = 0; i < 100; i++) {
            res.add(gen());
        }
        return res;
    }

    private NewTopCoderTest gen() {
        List<Integer> list = new ArrayList<Integer>();
        Random r = new Random();
        int n = r.nextInt(7) + 1;
        for (int i = 1; i <= n * 2; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        int[] A = new int[n];
        for (int i = 0; i < n; i++) A[i] = list.get(i);
        int[] B = new int[n];
        for (int j = 0; j < n; j++) {
            B[j] = r.nextBoolean() ? list.get(j + n) : -1;
        }
        double ans = 0;
        Arrays.sort(A);
        do {
            ans = Math.max(ans, solve(A, B));
        } while (Algo.nextPermutation(A));
        NewTopCoderTest res = new NewTopCoderTest(new Object[] {A, B}, ans);
        return res;
    }

    private double solve(int[] a, int[] b) {
        int n = a.length;
        List<Integer> notUse = new LinkedList<Integer>();
        for (int i = 1; i <= n * 2; i++) notUse.add(i);
        for (int i : a) notUse.remove((Integer) i);
        for (int i : b) if (i != -1) notUse.remove((Integer) i);
        int[] is = Algo.unBox(notUse.toArray(new Integer[0]));
        Arrays.sort(is);
        double sum = 0;
        int cnt = 0;
        do {
            int k = 0;
            for (int i = 0; i < n; i++) {
                if (b[i] == -1) {
                    sum += a[i] > is[k++] ? 1 : 0;
                } else {
                    sum += a[i] > b[i] ? 1 : 0;
                }
            }
            cnt++;
        } while (Algo.nextPermutation(is));
        return sum / cnt;
    }
}
