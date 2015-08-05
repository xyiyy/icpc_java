package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;

public class TaskA {
    Scanner in;
    PrintWriter out;
    private boolean[][] row;
    private boolean[][] col;
    private boolean[][] block;
    private int[] rowCnt;
    private int[] colCnt;
    private int[] blkCnt;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        boolean f = true;
        while (in.hasNext()) {
            if (f) f = false; else out.println();
            solve();
        }
    }

    void solve() {
        char[][] maps = new char[16][];
        for (int i = 0; i < 16; i++) {
            maps[i] = in.next().toCharArray();
        }
        initCrashTable(maps);
        dfs(maps);
    }

    private void initCrashTable(char[][] maps) {
        row = new boolean[16][16];
        col = new boolean[16][16];
        block = new boolean[16][16];
        rowCnt = new int[16];
        colCnt = new int[16];
        blkCnt = new int[16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (maps[i][j] != '-') {
                    int d = maps[i][j] - 'A';
                    row[i][d] = true;
                    col[j][d] = true;
                    block[i / 4 * 4 + j / 4][d] = true;
                    rowCnt[i]++;
                    colCnt[j]++;
                    blkCnt[i / 4 * 4 + j / 4]++;
                }
            }
        }
    }

    private boolean dfs(char[][] maps) {
        for (int i = 0; i < 16; i++) {
            for (int d = 0; d < 16; d++) if (!row[i][d]) {
                int c = 0;
                for (int j = 0; j < 16; j++) {
                    if (maps[i][j] == '-' && !col[j][d] && !block[i / 4 * 4 + j / 4][d]) c++;
                }
                if (c == 0) return false;
                if (c == 1) {
                    for (int j = 0; j < 16; j++) {
                        if (maps[i][j] == '-' && !col[j][d] && !block[i / 4 * 4 + j / 4][d]) {
                            return fill(maps, i, j, d);
                        }
                    }
                }
            }
        }
        for (int j = 0; j < 16; j++) {
            for (int d = 0; d < 16; d++) if (!col[j][d]) {
                int c = 0;
                for (int i = 0; i < 16; i++) {
                    if (maps[i][j] == '-' && !row[i][d] && !block[i / 4 * 4 + j / 4][d]) c++;
                }
                if (c == 0) return false;
                if (c == 1) {
                    for (int i = 0; i < 16; i++) {
                        if (maps[i][j] == '-' && !row[i][d] && !block[i / 4 * 4 + j / 4][d]) {
                            return fill(maps, i, j, d);
                        }
                    }
                }
            }
        }
        for (int b = 0; b < 16; b++) {
            for (int d = 0; d < 16; d++) if (!block[b][d]) {
                int c = 0;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        int ii = b / 4 * 4 + i;
                        int jj = b % 4 * 4 + j;
                        if (maps[ii][jj] == '-' && !row[ii][d] && !col[jj][d]) c++;
                    }
                }
                if (c == 0) return false;
                if (c == 1) {
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            int ii = b / 4 * 4 + i;
                            int jj = b % 4 * 4 + j;
                            if (maps[ii][jj] == '-' && !row[ii][d] && !col[jj][d]) return fill(maps, ii, jj, d);
                        }
                    }
                }
            }
        }

        int x = -1, y = -1;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                int b = i / 4 * 4 + j / 4;
                int bxy = x / 4 * 4 + y / 4;
                if (maps[i][j] == '-' && (x == -1 ||
                        rowCnt[i] >= rowCnt[x] && rowCnt[i] >= colCnt[y] && rowCnt[i] >= blkCnt[bxy] ||
                        colCnt[j] >= rowCnt[x] && colCnt[j] >= colCnt[y] && colCnt[j] >= blkCnt[bxy] ||
                        blkCnt[b] >= rowCnt[x] && blkCnt[b] >= colCnt[y] && blkCnt[b] >= blkCnt[bxy])) {
                    x = i;
                    y = j;
                }
                if (maps[i][j] == '-') {
                    int c = 0;
                    for (int d = 0; d < 16; d++) {
                        if (!row[i][d] && !col[j][d] && !block[b][d]) c++;
                    }
                    if (c == 0) return false;
                    if (c == 1) {
                        for (int d = 0; d < 16; d++) {
                            if (!row[i][d] && !col[j][d] && !block[b][d]) {
                                return fill(maps, i, j, d);
                            }
                        }
                    }
                }
            }
        }
        if (x == -1) {
            for (char[] cs : maps) {
                out.println(cs);
            }
            return true;
        }
        for (int i = 0; i < 16; i++) if (!row[x][i] && !col[y][i] && !block[x / 4 * 4 + y / 4][i]) {
            if (fill(maps, x, y, i)) return true;
        }
        return false;
    }

    boolean fill(char[][] maps, int x, int y, int i) {
        rowCnt[x]++;
        colCnt[y]++;
        blkCnt[x / 4 * 4 + y / 4]++;
        row[x][i] = col[y][i] = block[x / 4 * 4 + y / 4][i] = true;
        maps[x][y] = (char) ('A' + i);
        if (dfs(maps)) return true;
        maps[x][y] = '-';
        row[x][i] = col[y][i] = block[x / 4 * 4 + y / 4][i] = false;
        rowCnt[x]--;
        colCnt[y]--;
        blkCnt[x / 4 * 4 + y / 4]--;
        return false;
    }

}
