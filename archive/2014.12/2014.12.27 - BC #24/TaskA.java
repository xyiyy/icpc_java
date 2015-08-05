package main;



import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        while (in.hasNext())
            run();
    }

    boolean[] ps = Num.primeTable(1000, new ArrayList<Integer>());
    {
        ps[1] = true;
    }
    void run() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        int sum = 0;
        for (int i : is) if (ps[i]) sum += i;
        out.println(sum);
    }
}
