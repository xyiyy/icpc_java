package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class C {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            out.printf("Case #%d: ", i + 1);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int k = in.nextInt();
        Dic dic = new Dic(k);
        for (int i = 0; i < n; i++) {
            dic.insert(in.next());
        }
        out.println(dic.go());
    }
    class Dic {
        int k;
        Node root;

        public Dic(int k) {
            this.k = k;
            root = new Node();
        }

        void insert(String s) {
            Node crt = root;
            for (char c : s.toCharArray()) {
                int i = c - 'a';
                if (crt.ns[i] == null) crt.ns[i] = new Node();
                crt = crt.ns[i];
            }
            crt.ac = true;
        }

        int go() {
            dfs(root);
            return root.dp[k];
        }

        void dfs(Node crt) {
            Arrays.fill(crt.dp, INF);
            crt.dp[0] = 0;
            if (crt.ac) {
                crt.dp[1] = 0;
                crt.cnt++;
            }
            int[] dp = new int[k + 1];
            for (Node n : crt.ns) if (n != null) {
                dfs(n);
                crt.cnt += n.cnt;
                Arrays.fill(dp, INF);
                dp[0] = 0;
                for (int i = 0; i <= k; i++) if (n.dp[i] != INF) {
                    for (int j = 0; i + j <= k; j++) {
                        dp[i + j] = Math.min(dp[i + j], crt.dp[j] + n.dp[i] + i);
                        if (i == 1) dp[i + j] = Math.min(dp[i + j], crt.dp[j]);
                    }
                }
                for (int i = 0; i <= k; i++) {
                    crt.dp[i] = dp[i];
                }
            }
            if (crt.ac || crt == root) {
                for (int i = 0; i <= k; i++) {
                    dp[i] += i;
                }
            }
            Algo.debug(crt.dp);
        }

        class Node {
            Node[] ns = new Node[26];
            int[] dp = new int[k + 1];
            int cnt = 0;
            boolean ac = false;
        }
    }
    int INF = 20001;
}
