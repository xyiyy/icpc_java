package main;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskETestCase {

    Random in = new Random(151);
    String ln = System.lineSeparator();

    @TestCase
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<Test>();
        for (int i = 0; i < 1; i++) {
            list.add(gen());
        }
        return list;
    }

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int n = 10000;
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append("AB".charAt(in.nextInt(2)));
        }
        String s = sb.toString();
        input.append(s);
        output.append(solve(s));
        return new Test(input.toString(), output.toString());
    }
    public String solve(String s) {
        int len=s.length(),lim=(len+1)/2;
        int cnt[]=new int[len+1];
        for(int i=0;i<len;i++){
            cnt[i+1]=cnt[i];
            char ch=s.charAt(i);
            if(ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U' || ch=='Y')cnt[i+1]++;
        }
        double num=0,ans=0;
        for(int i=1;i<lim;i++){
            int x=len-i+1,y=i-1;
            num+=(cnt[x]-cnt[y]);
            ans+=(num/(double)(x))+(num/(double)(i));
        }
        num+=(cnt[len-lim+1]-cnt[lim-1]);
        if((len&1)==1){
            ans+=(num/(double)(len-lim+1));
        }
        else{
            ans+=(num/(double)(len-lim+1))+(num/(double)(lim));
        }
        return String.format("%.7f\n", ans);
    }
}
