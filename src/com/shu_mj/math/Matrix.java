package com.shu_mj.math;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by Jun on 6/7/2014.
 */
public class Matrix {

    public static long[][] mul(long[][] a, long[][] b) {
        int n = a.length;
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) if (a[i][k] != 0) {
                for (int j = 0; j < n; j++) {
                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    public static long[][] pow(long[][] a, long b) {
        int n = a.length;
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++)
            c[i][i] = 1;
        while (b > 0) {
            if ((b & 1) != 0)
                c = mul(c, a);
            a = mul(a, a);
            b >>>= 1;
        }
        return c;
    }
    public static double[][] mul(double[][] a, double[][] b) {
        int n = a.length;
        double[][] c = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) if (a[i][k] != 0) {
                for (int j = 0; j < n; j++) {
                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    public static double[][] pow(double[][] a, long b) {
        int n = a.length;
        double[][] c = new double[n][n];
        for (int i = 0; i < n; i++)
            c[i][i] = 1;
        while (b > 0) {
            if ((b & 1) != 0)
                c = mul(c, a);
            a = mul(a, a);
            b >>>= 1;
        }
        return c;
    }

    public static long[][] mul(long[][] a, long[][] b, long mod) {
        int n = a.length;
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) if (a[i][k] != 0) {
                for (int j = 0; j < n; j++) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % mod;
                }
            }
        }
        return c;
    }

    public static long[][] pow(long[][] a, long b, long mod) {
        int n = a.length;
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++)
            c[i][i] = 1;
        while (b > 0) {
            if ((b & 1) != 0)
                c = mul(c, a, mod);
            a = mul(a, a, mod);
            b >>>= 1;
        }
        return c;
    }
    public static int[][] mul(int[][] a, int[][] b) {
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) if (a[i][k] != 0) {
                for (int j = 0; j < n; j++) {
                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    public static int[][] pow(int[][] a, int b) {
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++)
            c[i][i] = 1;
        while (b > 0) {
            if ((b & 1) != 0)
                c = mul(c, a);
            a = mul(a, a);
            b >>>= 1;
        }
        return c;
    }

    public static int[][] mul(int[][] a, int[][] b, int mod) {
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) if (a[i][k] != 0) {
                for (int j = 0; j < n; j++) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % mod;
                }
            }
        }
        return c;
    }

    public static int[][] pow(int[][] a, int b, int mod) {
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++)
            c[i][i] = 1;
        while (b > 0) {
            if ((b & 1) != 0)
                c = mul(c, a, mod);
            a = mul(a, a, mod);
            b >>>= 1;
        }
        return c;
    }

    public static final double EPS = 1e-8;

    public static double[][] solutionSpace(double[][] A, double[] b) {
        int n = A.length, m = A[0].length;
        double[][] a = new double[n][m + 1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, a[i], 0, m);
            a[i][m] = b[i];
        }
        int[] id = new int[n + 1]; // 第 i 行的第一个非零元 1 所在的位置是 id[i]
        Arrays.fill(id, -1);
        int pi = 0; // 矩阵 A 的秩
        for (int pj = 0; pi < n && pj < m; pj++) {
            for (int i = pi + 1; i < n; i++) {
                if (Math.abs(a[i][pj]) > Math.abs(a[pi][pj])) {
                    double[] t = a[i];
                    a[i] = a[pi];
                    a[pi] = t;
                }
            }
            if (Math.abs(a[pi][pj]) < EPS) // 当前列已经全零
                continue;
            double inv = 1 / a[pi][pj]; // 如果取模运算，可以用大数模逆
            for (int j = 0; j <= m; j++) // 化主元为 1，可以优化
                a[pi][j] *= inv;
            for (int i = 0; i < n; i++)
                if (i != pi) {
                    double d = a[i][pj];
                    for (int j = 0; j <= m; j++) // 化当前列为 0，可以优化
                        a[i][j] -= d * a[pi][j];
                }
            id[pi++] = pj;
        }
        for (int i = pi; i < n; i++)
            if (Math.abs(a[i][m]) > EPS) // 增广矩阵的秩更大，无解
                return null;
        double[][] X = new double[1 + m - pi][m];
        for (int j = 0, k = 0; j < m; j++) {
            if (id[k] == j)
                X[0][j] = a[k++][m];
            else {
                for (int i = 0; i < k; i++)
                    X[1 + j - k][id[i]] = -a[i][j];
                X[1 + j - k][j] = 1;
            }
        }
        return X;
    }

    public static long[][] solutionSpace(long[][] A, long[] b, long mod) {
        int n = A.length, m = A[0].length;
        BigInteger MOD = BigInteger.valueOf(mod);
        long[][] a = new long[n][m + 1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, a[i], 0, m);
            a[i][m] = b[i];
        }
        int[] id = new int[n + 1]; // 第 i 行的第一个非零元 1 所在的位置是 id[i]
        Arrays.fill(id, -1);
        int pi = 0; // 矩阵 A 的秩
        for (int pj = 0; pi < n && pj < m; pj++) {
            for (int i = pi + 1; i < n; i++) {
                if (Math.abs(a[i][pj]) > Math.abs(a[pi][pj])) {
                    long[] t = a[i];
                    a[i] = a[pi];
                    a[pi] = t;
                }
            }
            if (Math.abs(a[pi][pj]) < EPS) // 当前列已经全零
                continue;
            //double inv = 1 / a[pi][pj]; // 如果取模运算，可以用大数模逆
            long inv = BigInteger.valueOf(a[pi][pj]).modInverse(MOD).longValue();
            for (int j = 0; j <= m; j++) // 化主元为 1，可以优化
                a[pi][j] = (a[pi][j] * inv) % mod;
            for (int i = 0; i < n; i++)
                if (i != pi) {
                    long d = a[i][pj];
                    for (int j = 0; j <= m; j++) // 化当前列为 0，可以优化
                        a[i][j] = (a[i][j] - d * a[pi][j] % mod) % mod;
                }
            id[pi++] = pj;
        }
        for (int i = pi; i < n; i++)
            if (Math.abs(a[i][m]) > EPS) // 增广矩阵的秩更大，无解
                return null;
        long[][] X = new long[1 + m - pi][m];
        for (int j = 0, k = 0; j < m; j++) {
            if (id[k] == j)
                X[0][j] = a[k++][m];
            else {
                for (int i = 0; i < k; i++)
                    X[1 + j - k][id[i]] = -a[i][j];
                X[1 + j - k][j] = 1;
            }
        }
        return X;
    }

    public static boolean[][] solutionSpace(boolean[][] A, boolean[] b) {
        int n = A.length, m = A[0].length;
        boolean[][] a = new boolean[n][m + 1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, a[i], 0, m);
            a[i][m] = b[i];
        }
        int[] id = new int[n + 1]; // 第 i 行的第一个非零元 1 所在的位置是 id[i]
        Arrays.fill(id, -1);
        int pi = 0; // 矩阵 A 的秩
        for (int pj = 0; pi < n && pj < m; pj++) {
            for (int i = pi + 1; i < n; i++) {
                if (a[i][pj] && !a[pi][pj]) {
                    boolean[] t = a[i];
                    a[i] = a[pi];
                    a[pi] = t;
                }
            }
            if (!a[pi][pj]) // 当前列已经全零
                continue;
            for (int i = 0; i < n; i++)
                if (i != pi) {
                    boolean d = a[i][pj];
                    for (int j = 0; j <= m; j++) // 化当前列为 0，可以优化
                        a[i][j] ^= d & a[pi][j];
                }
            id[pi++] = pj;
        }
        for (int i = pi; i < n; i++)
            if (a[i][m]) // 增广矩阵的秩更大，无解
                return null;
        boolean[][] X = new boolean[1 + m - pi][m];
        for (int j = 0, k = 0; j < m; j++) {
            if (id[k] == j)
                X[0][j] = a[k++][m];
            else {
                for (int i = 0; i < k; i++)
                    X[1 + j - k][id[i]] = a[i][j];
                X[1 + j - k][j] = true;
            }
        }
        return X;
    }

    // max{cx | Ax <= b, x >= 0}
    public static double[] simplex(double[][] A, double[] b, double[] c) {
        int n = A.length, m = A[0].length + 1, r = n, s = m - 1;
        double[][] D = new double[n + 2][m + 1];
        int[] ix = new int[n + m];
        for (int i = 0; i < n + m; i++) ix[i] = i;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m - 1; j++)
                D[i][j] = -A[i][j];
            D[i][m - 1] = 1;
            D[i][m] = b[i];
            if (D[r][m] > D[i][m]) r = i;
        }
        for (int j = 0; j < m - 1; j++) D[n][j] = c[j];
        D[n + 1][m - 1] = -1;
        for (double d;;) {
            if (r < n) {
                int t = ix[s]; ix[s] = ix[r + m]; ix[r + m] = t;
                D[r][s] = 1.0 / D[r][s];
                for (int j = 0; j <= m; j++) if (j != s) D[r][j] *= -D[r][s];
                for (int i = 0; i <= n + 1; i++) if (i != r) {
                        for (int j = 0; j <= m; j++) if (j != s)
                                D[i][j] += D[r][j] * D[i][s];
                        D[i][s] *= D[r][s];
                    }
            }
            r = -1; s = -1;
            for (int j = 0; j < m; j++)
                if (s < 0 || ix[s] > ix[j]) {
                    if (D[n + 1][j] > EPS || D[n + 1][j] > -EPS && D[n][j] > EPS) s = j;
                }
            if (s < 0) break;
            for (int i = 0; i < n; i++) if (D[i][s] < -EPS) {
                    if (r < 0 || (d = D[r][m] / D[r][s] - D[i][m] / D[i][s]) < -EPS
                            || d < EPS && ix[r + m] > ix[i + m]) r = i;
                }
            if (r < 0) return null;
        }
        if (D[n + 1][m] < -EPS) return null;
        double[] x = new double[m - 1];
        for (int i = m; i < n + m; i++) if (ix[i] < m - 1) x[ix[i]] = D[i - m][m];
        return x;
    }
}
