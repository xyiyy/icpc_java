package main;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaroJiroGrid {

    private String white;
    private String black;

    public int getNumber(String[] grid) {
        int ans = 2;
        int n = grid.length;
        char[] cs = new char[n];
        for (int i = 0; i < n; i++) cs[i] = 'W';
        white = String.valueOf(cs);
        for (int i = 0; i < n; i++) cs[i] = 'B';
        black = String.valueOf(cs);

        for (int i = 0; i < n; i++) {
            if (fit(grid, n, i)) ans = 1;
        }
        if (fit(grid)) ans = 0;
        return ans;
    }


    private boolean fit(String[] grid, int n, int i) {
        String t = grid[i];
        grid[i] = white;
        if (fit(grid)) {
            grid[i] = t;
            return true;
        }
        grid[i] = black;
        if (fit(grid)) {
            grid[i] = t;
            return true;
        }
        grid[i] = t;
        return false;
    }

    private boolean fit(String[] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            int s = 0;
            int c = 1;
            for (int j = 1; j < n; j++) {
                if (grid[j].charAt(i) == grid[j - 1].charAt(i)) {
                    c++;
                } else {
                    s = Math.max(s, c);
                    c = 1;
                }
            }
            s = Math.max(s, c);
            if (s > n / 2) return false;
        }
        return true;
    }
}
