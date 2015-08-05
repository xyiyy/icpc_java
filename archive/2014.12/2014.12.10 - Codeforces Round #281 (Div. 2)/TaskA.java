package main;

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
        run();
    }

    void run() {
        String h = in.next();
        String a = in.next();
        int n = in.nextInt();
        Item[] is = new Item[n];
        for (int i = 0; i < n; i++) {
            is[i] = new Item(in.nextInt(), in.next().charAt(0), in.nextInt(), in.next().charAt(0));
        }
        Arrays.sort(is);
        int max = 0;
        for (Item i : is) max = Math.max(max, i.id);
        int[][] cnt = new int[2][max + 1];
        boolean[][] gv = new boolean[2][max + 1];
        for (Item i : is) {
            if (i.color == 'y') cnt[i.h == 'h' ? 0 : 1][i.id]++;
            else cnt[i.h == 'h' ? 0 : 1][i.id] += 2;
            if (cnt[i.h == 'h' ? 0 : 1][i.id] >= 2 && !gv[i.h == 'h' ? 0 : 1][i.id]) {
                out.println((i.h == 'h' ? h : a) + " " + i.id + " " + i.time);
                gv[i.h == 'h' ? 0 : 1][i.id] = true;
            }
        }
    }
    class Item implements Comparable<Item> {
        int time;
        char h;
        int id;
        char color;

        public Item(int time, char h, int id, char color) {
            this.time = time;
            this.h = h;
            this.id = id;
            this.color = color;
        }

        public int compareTo(Item o) {
            return time - o.time;
        }
    }
}
