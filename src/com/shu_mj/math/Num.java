package com.shu_mj.math;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by Jun on 6/7/2014.
 */
public class Num {
    public static Random rnd;
    public static List<Integer> primes;
    public static boolean[] isPrime;

    public static boolean millerRabin(BigInteger n, int times) {
        n = n.abs();
        if (n.compareTo(BigInteger.valueOf(2)) < 0) return false;
        if (n.equals(BigInteger.valueOf(2))) return true;
        if (!n.testBit(0)) return false;
        BigInteger q = n.subtract(BigInteger.ONE);
        int k = 0;
        while (q.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            k++;
            q = q.shiftRight(1);
        }
        for (int i = 0; i < times; i++) {
            BigInteger a = new BigInteger(n.bitLength(), rnd == null ? rnd = new Random() : rnd).abs().mod(n.subtract(BigInteger.ONE)).add(BigInteger.ONE);
            BigInteger x = a.modPow(q, n);
            if (x.equals(BigInteger.ONE)) continue;
            boolean found = false;
            for (int j = 0; j < k; j++) {
                if (x.equals(n.subtract(BigInteger.ONE))) {
                    found = true;
                    break;
                }
                x = x.multiply(x).mod(n);
            }
            if (found) continue;
            return false;
        }
        return true;
    }
    public static BigInteger pollardRho(BigInteger n, BigInteger c) {
        BigInteger x = BigInteger.valueOf(2);
        BigInteger y = BigInteger.valueOf(2);
        BigInteger d = BigInteger.ONE;
        while (d.equals(BigInteger.ONE)) {
            x = x.multiply(x).mod(n).add(c);
            y = y.multiply(y).mod(n).add(c);
            y = y.multiply(y).mod(n).add(c);
            d = x.subtract(y).abs().gcd(n);
        }
        if (d.equals(n)) return pollardRho(n, c.add(BigInteger.ONE));
        return d;
    }

    public static boolean isPrime(BigInteger n) {
        if (isPrime != null && n.compareTo(BigInteger.valueOf(isPrime.length)) < 0) return isPrime[n.intValue()];
        return millerRabin(n, 10);
    }

    public static boolean isPrime(long n) {
        if (isPrime != null && n < isPrime.length) return isPrime[(int) n];
        return millerRabin(BigInteger.valueOf(n), 10);
    }

    public static void factorize(BigInteger n, Map<BigInteger, Integer> factors) {
        if (isPrime(n)) {
            Num.inc(factors, n);
        } else {
            for (Integer prime : primes) {
                BigInteger p = BigInteger.valueOf(prime);
                while (n.mod(p).equals(BigInteger.ZERO)) {
                    Num.inc(factors, p);
                    n = n.divide(p);
                }
            }
            if (!n.equals(BigInteger.ONE)) {
                if (isPrime(n)) {
                    Num.inc(factors, n);
                } else {
                    BigInteger d = pollardRho(n, BigInteger.ONE);
                    factorize(d, factors);
                    factorize(n.divide(d), factors);
                }
            }
        }
    }

    public static boolean[] primeTable(int n, List<Integer> primes) {
        Num.primes = primes;
        isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        /*
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) primes.R(i);
            for (int p : primes) {
                if (i > n / p) break;
                isPrime[i * p] = false;
                if (i % p == 0) break;
            }
        }*/
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
                for (int j = i + i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }

    public static long phi(long n) {
        long ans = n;
        for (long i : primes) {
            if (i * i > n) break;
            if (n % i == 0) {
                ans = ans / i * (i - 1);
                while (n % i == 0) n /= i;
            }
        }
        if (n > 1) ans = ans / n * (n - 1);
        return ans;
    }

    public static int[] phiTable(int n) {
        int[] phi = new int[n + 1];
        phi[1] = 1;
        for (int i = 2; i <= n; i++)
            if (phi[i] == 0) {
                for (int j = i; j <= n; j += i) {
                    if (phi[j] == 0) phi[j] = j;
                    phi[j] = phi[j] / i * (i - 1);
                }
            }
        return phi;
    }

    public static long combination(int n, int m, long mod) {
        if (m < 0 || m > n)
            return 0;
        if (2 * m > n)
            m = n - m;
        long res = 1;
        for (int i = n - m + 1; i <= n; i++)
            res = res * i % mod;
        return res * BigInteger.valueOf(factorial(m, mod)).modInverse(BigInteger.valueOf(mod)).longValue() % mod;
    }

    public static long[][] combinationTable(int n) {
        long[][] res = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            res[i][0] = 1;
            for (int j = 1; j <= i; j++)
                res[i][j] = res[i - 1][j - 1] + res[i - 1][j];
        }
        return res;
    }

    public static double[][] doubleCombinationTable(int n) {
        double[][] res = new double[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            res[i][0] = 1;
            for (int j = 1; j <= i; j++)
                res[i][j] = res[i - 1][j - 1] + res[i - 1][j];
        }
        return res;
    }

    public static long[][] combinationTable(int n, long mod) {
        long[][] res = new long[n + 1][n + 1];
        if (mod == 1)
            return res;
        for (int i = 0; i <= n; i++) {
            res[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                res[i][j] = res[i - 1][j - 1] + res[i - 1][j];
                if (res[i][j] >= mod)
                    res[i][j] -= mod;
            }
        }
        return res;
    }

    public static long[] combinationRowTable(int n, long mod) {
        long[] res = invTable(n, mod);
        res[0] = 1;
        for (int i = 1; i <= n; i++) {
            res[i] = res[i - 1] * (n - i + 1) % mod * res[i] % mod;
        }
        return res;
    }
    public static double[] doubleCombinationRowTable(int n) {
        double[] res = new double[n + 1];
        res[0] = 1;
        for (int i = 1; i <= n; i++) {
            res[i] = res[i - 1] * (n - i + 1) / i;
        }
        return res;
    }

    public static double[] doubleLogCombinationRowTable(int n) {
        double[] res = new double[n + 1];
        res[0] = Math.log(1);
        for (int i = 1; i <= n; i++) {
            res[i] = res[i - 1] + Math.log(n - i + 1) - Math.log(i);
        }
        return res;
    }

    public static long[] invTable(int n, long mod) {
        long[] res = new long[n + 1];
        if (n >= 1) res[1] = 1;
        for (int i = 2; i <= n; i++)
            res[i] = (mod - mod / i * res[(int) (mod % i)] % mod) % mod;
        return res;
    }

    public static long factorial(int n) {
        long res = 1;
        for (int i = 2; i <= n; i++)
            res *= i;
        return res;
    }

    public static long factorial(int n, long mod) {
        long res = 1;
        for (int i = 2; i <= n; i++)
            res = res * i % mod;
        return res % mod;
    }

    public static long[] factorialTable(int n, long mod) {
        long[] A = new long[n + 1];
        A[0] = 1;
        for (int i = 1; i <= n; i++) {
            A[i] = A[i - 1] * i % mod;
        }
        return A;
    }

    public static long pow(long p, int e) {
        long res = 1;
        while (e != 0) {
            if ((e & 1L) != 0) res = res * p;
            p = p * p;
            e >>= 1;
        }
        return res;
    }

    public static long powR(long p, long e, long mod) {
        long res = 1;
        while (e != 0) {
            if ((e & 1L) != 0) res = mul(res, p, mod);
            p = mul(p, p, mod);
            e >>= 1;
        }
        return res;
    }

    public static long pow(long p, long e, long mod) {
        long res = 1;
        while (e != 0) {
            if ((e & 1l) != 0) res = res * p % mod;
            p = p * p % mod;
            e >>= 1;
        }
        return res;
    }

    public static long[] powTable(long p, int n, long mod) {
        long[] res = new long[n + 1];
        res[0] = 1;
        for (int i = 1; i <= n; i++) {
            res[i] = res[i - 1] * p % mod;
        }
        return res;
    }

    public static long mul(long a, long b, long mod) {
        return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).mod(BigInteger.valueOf(mod)).longValue();
    }

    public static long invS(long a, long mod) {
        if (a == 1) return 1;
        return invS(mod % a, mod) * (mod - mod / a) % mod;
    }

    public static long inv(long a, long mod) {
        return BigInteger.valueOf(a).modInverse(BigInteger.valueOf(mod)).longValue();
    }

    public static long gcd(long a, long b) {
        while (b != 0) {
            long c = a;
            a = b;
            b = c % b;
        }
        if (a < 0) a = -a;
        return a;
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int c = a;
            a = b;
            b = c % b;
        }
        if (a < 0) a = -a;
        return a;
    }

    public static int[] exGcd(int x, int y) {
        int a0 = 1, a1 = 0, b0 = 0, b1 = 1, t;
        while (y != 0) {
            t = a0 - x / y * a1; a0 = a1; a1 = t;
            t = b0 - x / y * b1; b0 = b1; b1 = t;
            t = x % y; x = y; y = t;
        }
        if (x < 0) { a0 = -a0; b0 = -b0; x = -x; }
        return new int[]{a0, b0, x};
    }

    public static long[] exGcd(long x, long y) {
        long a0 = 1, a1 = 0, b0 = 0, b1 = 1, t;
        while (y != 0) {
            t = a0 - x / y * a1; a0 = a1; a1 = t;
            t = b0 - x / y * b1; b0 = b1; b1 = t;
            t = x % y; x = y; y = t;
        }
        if (x < 0) { a0 = -a0; b0 = -b0; x = -x; }
        return new long[]{a0, b0, x};
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public static long lcm(int a, int b) {
        return (long) a / gcd(a, b) * b;
    }

    public static Map<Long, Integer> factorize(long n) {
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        for (long i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                inc(map, i);
                n /= i;
            }
        }
        if (n > 1) {
            if (!map.containsKey(n)) map.put(n, 1);
            else map.put(n, map.get(n) + 1);
        }
        return map;
    }

    public static List<Long> primeFactors(long n) {
        List<Long> ps = new ArrayList<Long>();
        for (int i : primes) {
            if ((long) i * i > n) break;
            if (n % i == 0) {
                ps.add((long) i);
                while (n % i == 0) n /= i;
            }
        }
        if (n != 1) ps.add(n);
        return ps;
    }

    public static List<Long> factors(long n) {
        List<Long> res = new ArrayList<Long>();
        for (long i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                res.add(i);
                if (i * i != n) res.add(n / i);
            }
        }
        return res;
    }

    //把 n 的约数的莫比乌斯值用 map 形式的返回。O(sqrt n)
    public static Map<Long, Integer> moebius(long n) {
        Map<Long, Integer> res = new TreeMap<Long, Integer>();
        List<Long> primes = primeFactors(n);
        int m = primes.size();
        for (int i = 0; i < (1 << m); i++) {
            int mu = 1;
            long d = 1;
            for (int j = 0; j < m; j++) {
                if ((i & (1 << j)) != 0) {
                    mu *= -1;
                    d *= primes.get(j);
                }
            }
            res.put(d, mu);
        }
        return res;
    }

    public static int[] moebiusTable(int n) {
        boolean[] check = new boolean[n + 1];
        List<Integer> primes = new ArrayList<Integer>();
        int[] mu = new int[n + 1];
        mu[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (!check[i]) {
                primes.add(i);
                mu[i] = -1;
            }
            for (int p : primes) {
                if (i * p > n) break;
                check[i * p] = true;
                if (i % p == 0) {
                    mu[i * p] = 0;
                    break;
                } else {
                    mu[i * p] = -mu[i];
                }
            }
        }
        return mu;
    }
    public static BigInteger sqrt(String theNumber) {
        int length = theNumber.length(), i;
        BigInteger res = BigInteger.ZERO;
        BigInteger twenty = BigInteger.valueOf(20);
        BigInteger t, x = BigInteger.ZERO, v, few = BigInteger.ZERO;
        BigInteger hg = BigInteger.valueOf(100);
        int pos = 2 - length % 2;
        String tmpString = theNumber.substring(0, pos);
        while (true) {
            v = few.multiply(hg).add(BigInteger.valueOf(Integer.parseInt(tmpString)));
            if (res.compareTo(BigInteger.ZERO) == 0) i = 9;
            else i = v.divide(res.multiply(twenty)).intValue();
            for (; i >= 0; i--) {
                t = res.multiply(twenty).add(BigInteger.valueOf(i)).multiply(BigInteger.valueOf(i));
                if (t.compareTo(v) <= 0) {
                    x = t;
                    break;
                }
            }
            res = res.multiply(BigInteger.TEN).add(BigInteger.valueOf(i));
            few = v.subtract(x);
            pos++;
            if (pos > length) break;
            tmpString = theNumber.substring(pos - 1, ++pos);
        }
        return res;
    }

    public static <K> void inc(Map<K, Integer> map, K k) {
        if (!map.containsKey(k)) map.put(k, 1);
        else map.put(k, map.get(k) + 1);
    }
    public static <K> void dec(Map<K, Integer> map, K k) {
        if (map.get(k) == 1) map.remove(k);
        else map.put(k, map.get(k) - 1);
    }

   // Ax = B (mod M)
    public static BigInteger[] congruence(BigInteger[] A, BigInteger[] B, BigInteger[] M) {
        BigInteger x = BigInteger.ZERO, m = BigInteger.ONE;
        for (int i = 0; i < A.length; i++) {
            BigInteger a = A[i].multiply(m), b = B[i].subtract(A[i].multiply(x)), d = a.gcd(M[i]);
            if (!b.mod(d).equals(BigInteger.ZERO)) return null;
            x = x.add(m.multiply(b.divide(d).multiply(a.divide(d).modInverse(M[i].divide(d))).mod(M[i].divide(d))));
            m = m.multiply(M[i].divide(d));
        }
        return new BigInteger[] { x.mod(m), m };
    }

    public static long[] congruence(long[] A, long[] B, long[] M) {
        long x = 0, m = 1;
        for (int i = 0; i < A.length; i++) {
            long a = A[i] * m, b = B[i] - A[i] * x, d = gcd(a, M[i]);
            if (b % d != 0) return null;
            x += m * (b / d * inv(a / d, M[i] / d) % (M[i] / d));
            m *= M[i] / d;
        }
        return new long[] { x % m, m };
    }

    public static long[] congruence(long A, long B, long M) {
        long x = 0, m = 1;
        long a = A * m, b = B - A * x, d = gcd(a, M);
        if (b % d != 0) return null;
        x += m * (b / d * inv(a / d, M / d) % (M / d));
        m *= M / d;
        return new long[] { x % m, m };
    }

    // a^x = b (mod m) return min(x)
    // return -1 if no solution
    public static long modLog(long a, long b, long m) {
        if (b % gcd(a, m) != 0) return -1;
        if (m == 0) return 0;
        long n = (long) Math.sqrt(m) + 1;
        Map<Long, Long> map = new HashMap<Long, Long>();
        long an = 1;
        for (long j = 0; j < n; j++) {
            if (!map.containsKey(an)) map.put(an, j);
            an = an * a % m;
        }
        long ain = 1, res = Long.MAX_VALUE;
        for (long i = 0; i < n; i++) {
            long[] is = congruence(ain, b, m);
            for (long aj = is[0]; aj < m; aj += is[1]) if (map.containsKey(aj)) {
                long j = map.get(aj);
                res = Math.min(res, i * n + j);
            }
            if (res < Long.MAX_VALUE) return res;
            ain = ain * an % m;
        }
        return -1;
    }

    public static Map<Integer, int[]> fact;
    public static int e;
    public static int[] modFact(int n, int p) {
        return new int[] { modFactRec(n, p), e };
    }
    public static int modFactRec(int n, int p) {
        e = 0;
        if (n == 0) return 1;
        int res = modFactRec(n / p, p);
        e += n / p;
        if (n / p % 2 != 0) return res * (p - fact(n % p, p)) % p;
        return res * fact(n % p, p) % p;
    }
    public static int fact(int n, int p) {
        if (fact == null) fact = new HashMap<Integer, int[]>();
        if (!fact.containsKey(p)) {
            int[] f = new int[p];
            f[0] = 1;
            for (int i = 1; i < p; i++) f[i] = (int) ((long) f[i - 1] * i % p);
            fact.put(p, f);
        }
        return fact.get(p)[n];
    }

    // C(n, k) % p
    public static int modComb(int n, int k, int p) {
        if (n < 0 || k < 0 || n < k) return 0;
        int[] a1 = modFact(n, p), a2 = modFact(k, p), a3 = modFact(n - k, p);
        if (a1[1] > a2[1] + a3[1]) return 0;
        return a1[0] * (int) inv(a2[0] * a3[0] % p, p) % p;
    }

}
