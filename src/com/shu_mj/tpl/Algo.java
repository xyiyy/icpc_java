package com.shu_mj.tpl;

import com.shu_mj.geo.P;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Created by Jun on 6/6/2014.
 */
public class Algo {

    public static void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }
    public static void debugTable(Object[] os) {
        for (Object o : os) debug(o);
        debug("-------------------------------------");
    }

    public static void debugEqual(boolean[] bs, boolean b) {
        for (int i = 0; i < bs.length; i++) if (bs[i] == b) {
            System.err.print(i + " ");
        }
        System.err.println();
    }
    public static void debugEqual(char[] cs, char v) {
        for (int i = 0; i < cs.length; i++) if (cs[i] == v) {
            System.err.print(i + " ");
        }
        System.err.println();
    }
    public static void debugEqual(double[] ds, double v) {
        for (int i = 0; i < ds.length; i++) if (ds[i] == v) {
            System.err.print(i + " ");
        }
        System.err.println();
    }
    public static void debugEqual(int[] is, int v) {
        for (int i = 0; i < is.length; i++) if (is[i] == v) {
            System.err.print(i + " ");
        }
        System.err.println();
    }
    public static void debugEqual(long[] ls, long v) {
        for (int i = 0; i < ls.length; i++) if (ls[i] == v) {
            System.err.print(i + " ");
        }
        System.err.println();
    }


    public static void debugNotEqual(boolean[] bs, boolean v) {
        for (int i = 0; i < bs.length; i++) if (bs[i] != v) {
            System.err.print(i + " ");
        }
        System.err.println();
    }
    public static void debugNotEqual(char[] cs, char v) {
        for (int i = 0; i < cs.length; i++) if (cs[i] != v) {
            System.err.print(i + " ");
        }
        System.err.println();
    }
    public static void debugNotEqual(double[] ds, double v) {
        for (int i = 0; i < ds.length; i++) if (ds[i] != v) {
            System.err.print(i + " ");
        }
        System.err.println();
    }
    public static void debugNotEqual(int[] is, int v) {
        for (int i = 0; i < is.length; i++) if (is[i] != v) {
            System.err.print(i + " ");
        }
        System.err.println();
    }
    public static void debugNotEqual(long[] ls, long v) {
        for (int i = 0; i < ls.length; i++) if (ls[i] != v) {
            System.err.print(i + " ");
        }
        System.err.println();
    }

    public static double sum(double[] ds) {
        double res = 0;
        for (int i = 0; i < ds.length; i++) {
            res += ds[i];
        }
        return res;
    }
    public static long sum(int[] is) {
        long res = 0;
        for (int i = 0; i < is.length; i++) {
            res += is[i];
        }
        return res;
    }
    public static long sum(long[] ls) {
        long res = 0;
        for (int i = 0; i < ls.length; i++) {
            res += ls[i];
        }
        return res;
    }
    public static char min(char[] cs) {
        char res = Character.MAX_VALUE;
        for (int i = 0; i < cs.length; i++) {
            res = (char) Math.min(res, cs[i]);
        }
        return res;
    }

    public static double min(double[] ds) {
        double res = Double.MAX_VALUE;
        for (int i = 0; i < ds.length; i++) {
            res = Math.min(res, ds[i]);
        }
        return res;
    }
    public static int min(int[] is) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < is.length; i++) {
            res = Math.min(res, is[i]);
        }
        return res;
    }
    public static long min(long[] ls) {
        long res = Long.MAX_VALUE;
        for (int i = 0; i < ls.length; i++) {
            res = Math.min(res, ls[i]);
        }
        return res;
    }
    public static <T extends Comparable<T>> T min(T[] ts) {
        T res = null;
        for (T t : ts) {
            if (res == null || res.compareTo(t) > 0) {
                res = t;
            }
        }
        return res;
    }

    public static int minIndex(char[] cs) {
        int index = -1;
        for (int i = 0; i < cs.length; i++) {
            if (index == -1 || cs[index] > cs[i]) {
                index = i;
            }
        }
        return index;
    }

    public static int minIndex(double[] ds) {
        int index = -1;
        for (int i = 0; i < ds.length; i++) {
            if (index == -1 || ds[index] > ds[i]) {
                index = i;
            }
        }
        return index;
    }
    public static int minIndex(int[] is) {
        int index = -1;
        for (int i = 0; i < is.length; i++) {
            if (index == -1 || is[index] > is[i]) {
                index = i;
            }
        }
        return index;
    }
    public static int minIndex(long[] ls) {
        int index = -1;
        for (int i = 0; i < ls.length; i++) {
            if (index == -1 || ls[index] > ls[i]) {
                index = i;
            }
        }
        return index;
    }
    public static <T extends Comparable<T>> int minIndex(T[] ts) {
        int index = -1;
        for (int i = 0; i < ts.length; i++) {
            if (index == -1 || ts[index].compareTo(ts[i]) > 0) {
                index = i;
            }
        }
        return index;
    }

    public static char max(char[] cs) {
        char res = Character.MIN_VALUE;
        for (int i = 0; i < cs.length; i++) {
            res = (char) Math.max(res, cs[i]);
        }
        return res;
    }
    public static double max(double[] ds) {
        double res = -Double.MAX_VALUE;
        for (int i = 0; i < ds.length; i++) {
            res = Math.max(res, ds[i]);
        }
        return res;
    }
    public static int max(int[] is) {
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < is.length; i++) {
            res = Math.max(res, is[i]);
        }
        return res;
    }
    public static long max(long[] ls) {
        long res = Long.MIN_VALUE;
        for (int i = 0; i < ls.length; i++) {
            res = Math.max(res, ls[i]);
        }
        return res;
    }
    public static <T extends Comparable<T>> T max(T[] ts) {
        T res = null;
        for (T t : ts) {
            if (res == null || res.compareTo(t) < 0) {
                res = t;
            }
        }
        return res;
    }

    public static int maxIndex(char[] cs) {
        int index = -1;
        for (int i = 0; i < cs.length; i++) {
            if (index == -1 || cs[index] < cs[i]) {
                index = i;
            }
        }
        return index;
    }
    public static int maxIndex(double[] ds) {
        int index = -1;
        for (int i = 0; i < ds.length; i++) {
            if (index == -1 || ds[index] < ds[i]) {
                index = i;
            }
        }
        return index;
    }
    public static int maxIndex(int[] is) {
        int index = -1;
        for (int i = 0; i < is.length; i++) {
            if (index == -1 || is[index] < is[i]) {
                index = i;
            }
        }
        return index;
    }
    public static int maxIndex(long[] ls) {
        int index = -1;
        for (int i = 0; i < ls.length; i++) {
            if (index == -1 || ls[index] < ls[i]) {
                index = i;
            }
        }
        return index;
    }
    public static <T extends Comparable<T>> int maxIndex(T[] ts) {
        int index = -1;
        for (int i = 0; i < ts.length; i++) {
            if (index == -1 || ts[index].compareTo(ts[i]) < 0) {
                index = i;
            }
        }
        return index;
    }

    public static int count(boolean[] bs, boolean val) {
        int res = 0;
        for (int i = 0; i < bs.length; i++) {
            if (bs[i] == val) res++;
        }
        return res;
    }
    public static int count(char[] cs, char val) {
        int res = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == val) res++;
        }
        return res;
    }
    public static int count(int[] is, int val) {
        int res = 0;
        for (int i = 0; i < is.length; i++) {
            if (is[i] == val) res++;
        }
        return res;
    }
    public static int count(long[] ls, long val) {
        int res = 0;
        for (int i = 0; i < ls.length; i++) {
            if (ls[i] == val) res++;
        }
        return res;
    }
    public static <T> int count(T[] ts, T val) {
        int res = 0;
        for (int i = 0; i < ts.length; i++) {
            if (ts[i].equals(val)) res++;
        }
        return res;
    }

    public static int log2(int b) {
        return 31 - Integer.numberOfLeadingZeros(b);
    }
    public static int log2(long b) {
        return 63 - Long.numberOfLeadingZeros(b);
    }

    public static void swap(char[] cs, int i, int j) {
        char t = cs[i]; cs[i] = cs[j]; cs[j] = t;
    }
    public static void swap(double[] ds, int i, int j) {
        double t = ds[i]; ds[i] = ds[j]; ds[j] = t;
    }
    public static void swap(int[] is, int i, int j) {
        int t = is[i]; is[i] = is[j]; is[j] = t;
    }
    public static void swap(long[] ls, int i, int j) {
        long t = ls[i]; ls[i] = ls[j]; ls[j] = t;
    }
    private static <T> void swap(List<T> tl, int i, int j) {
        T t = tl.get(i); tl.set(i, tl.get(j)); tl.set(j, t);
    }
    public static<T> void swap(T[] ts, int i, int j) {
        T t = ts[i]; ts[i] = ts[j]; ts[j] = t;
    }

    public static void reverse(char[] cs) {
        reverse(cs, 0, cs.length);
    }
    public static void reverse(double[] ds) {
        reverse(ds, 0, ds.length);
    }
    public static void reverse(int[] is) {
        reverse(is, 0, is.length);
    }
    public static void reverse(long[] ls) {
        reverse(ls, 0, ls.length);
    }
    public static <T> void reverse(List<T> tl) {
        reverse(tl, 0, tl.size());
    }
    public static <T> void reverse(T[] ts) {
        reverse(ts, 0, ts.length);
    }

    public static void reverse(char[] cs, int b, int e) {
        while (b < e) swap(cs, b++, --e);
    }
    public static void reverse(double[] ds, int b, int e) {
        while (b < e) swap(ds, b++, --e);
    }
    public static void reverse(int[] is, int b, int e) {
        while (b < e) swap(is, b++, --e);
    }
    public static void reverse(long[] ls, int b, int e) {
        while (b < e) swap(ls, b++, --e);
    }
    private static <T> void reverse(List<T> tl, int b, int e) {
        while (b < e) swap(tl, b++, --e);
    }
    public static<T> void reverse(T[] ts, int b, int e) {
        while (b < e) swap(ts, b++, --e);
    }

    public static boolean nextPermutation(int[] is) {
        int n = is.length;
        for (int i = n - 1; i > 0; i--) {
            if (is[i - 1] < is[i]) {
                int j = n;
                while (is[i - 1] >= is[--j]) ;
                swap(is, i - 1, j);
                reverse(is, i, n);
                return true;
            }
        }
        reverse(is, 0, n);
        return false;
    }
    public static boolean nextPermutation(long[] ls) {
        int n = ls.length;
        for (int i = n - 1; i > 0; i--) {
            if (ls[i - 1] < ls[i]) {
                int j = n;
                while (ls[i - 1] >= ls[--j]) ;
                swap(ls, i - 1, j);
                reverse(ls, i, n);
                return true;
            }
        }
        reverse(ls, 0, n);
        return false;
    }
    public static boolean nextPermutation(char[] cs) {
        int n = cs.length;
        for (int i = n - 1; i > 0; i--) {
            if (cs[i - 1] < cs[i]) {
                int j = n;
                while (cs[i - 1] >= cs[--j]) ;
                swap(cs, i - 1, j);
                reverse(cs, i, n);
                return true;
            }
        }
        reverse(cs, 0, n);
        return false;
    }
    public static <T extends Comparable<T>> boolean nextPermutation(T[] ts) {
        int n = ts.length;
        for (int i = n - 1; i > 0; i--) {
            if (ts[i - 1].compareTo(ts[i]) < 0) {
                int j = n;
                while (ts[i - 1].compareTo(ts[--j]) >= 0) ;
                swap(ts, i - 1, j);
                reverse(ts, i, n);
                return true;
            }
        }
        reverse(ts, 0, n);
        return false;
    }
    public static<T extends Comparable<T>> boolean nextPermutation(List<T> tl) {
        int n = tl.size();
        for (int i = n - 1; i > 0; i--) {
            if (tl.get(i - 1).compareTo(tl.get(i)) < 0) {
                int j = n;
                while (tl.get(i - 1).compareTo(tl.get(--j)) >= 0) ;
                swap(tl, i - 1, j);
                reverse(tl, i, n);
                return true;
            }
        }
        reverse(tl, 0, n);
        return false;
    }

    public static int[] unique(int[] is) {
        return unique(is, 0, is.length);
    }
    public static long[] unique(long[] ls) {
        return unique(ls, 0, ls.length);
    }
    public static char[] unique(char[] cs) {
        return unique(cs, 0, cs.length);
    }
    public static <T extends Comparable<T>> T[] unique(T[] ts) {
        return unique(ts, 0, ts.length);
    }

    public static int[] unique(int[] is, int b, int e) {
        if (b == e)
            return new int[0];
        int count = 1;
        for (int i = b + 1; i < e; i++) {
            if (is[i] != is[i - 1])
                count++;
        }
        int[] res = new int[count];
        res[0] = is[b];
        int id = 1;
        for (int i = b + 1; i < e; i++) {
            if (is[i] != is[i - 1])
                res[id++] = is[i];
        }
        return res;
    }
    public static long[] unique(long[] ls, int b, int e) {
        if (b == e)
            return new long[0];
        int cnt = 1;
        for (int i = b + 1; i < e; i++) {
            if (ls[i] != ls[i - 1])
                cnt++;
        }
        long[] res = new long[cnt];
        res[0] = ls[b];
        int id = 1;
        for (int i = b + 1; i < e; i++) {
            if (ls[i] != ls[i - 1])
                res[id++] = ls[i];
        }
        return res;
    }
    public static char[] unique(char[] cs, int b, int e) {
        if (b == e)
            return new char[0];
        int cnt = 1;
        for (int i = b + 1; i < e; i++) {
            if (cs[i] != cs[i - 1])
                cnt++;
        }
        char[] res = new char[cnt];
        res[0] = cs[b];
        int id = 1;
        for (int i = b + 1; i < e; i++) {
            if (cs[i] != cs[i - 1])
                res[id++] = cs[i];
        }
        return res;
    }
    public static <T extends Comparable<T>> T[] unique(T[] ts, int b, int e) {
        if (b == e)
            return Arrays.copyOfRange(ts, b, e);
        int cnt = 1;
        for (int i = b + 1; i < e; i++) {
            if (ts[i].compareTo(ts[i - 1]) != 0)
                cnt++;
        }
        T[] res = Arrays.copyOf(ts, cnt);
        res[0] = ts[b];
        int id = 1;
        for (int i = b + 1; i < e; i++) {
            if (ts[i].compareTo(ts[i - 1]) != 0)
                res[id++] = ts[i];
        }
        return res;
    }

    public static int lowerBound(double[] ds, double v) {
        return lowerBound(ds, 0, ds.length, v);
    }
    public static int lowerBound(int[] is, int v) {
        return lowerBound(is, 0, is.length, v);
    }
    public static int lowerBound(long[] ls, long v) {
        return lowerBound(ls, 0, ls.length, v);
    }
    public static <T extends Comparable<T>> int lowerBound(T[] is, T v) {
        return lowerBound(is, 0, is.length, v);
    }
    public static <T extends Comparable<T>> int lowerBound(List<T> tl, T v) {
        return lowerBound(tl, 0, tl.size(), v);
    }

    public static int lowerBound(double[] ds, int l, int r, double v) {
        while (l < r) {
            int m = (l + r) >> 1;
            if (ds[m] >= v) r = m;
            else l = m + 1;
        }
        return l;
    }
    public static int lowerBound(int[] is, int l, int r, int v) {
        while (l < r) {
            int m = (l + r) >> 1;
            if (is[m] >= v) r = m;
            else l = m + 1;
        }
        return l;
    }
    public static int lowerBound(long[] ls, int l, int r, long v) {
        while (l < r) {
            int m = (l + r) >> 1;
            if (ls[m] >= v) r = m;
            else l = m + 1;
        }
        return l;
    }
    public static<T extends Comparable<T>> int lowerBound(T[] ts, int l, int r, T v) {
        while (l < r) {
            int m = (l + r) >> 1;
            if (ts[m].compareTo(v) >= 0) r = m;
            else l = m + 1;
        }
        return l;
    }
    public static<T extends Comparable<T>> int lowerBound(List<T> tl, int l, int r, T v) {
        while (l < r) {
            int m = (l + r) >> 1;
            if (tl.get(m).compareTo(v) >= 0) r = m;
            else l = m + 1;
        }
        return l;
    }

    public static int upperBound(double[] ds, double v) {
        return upperBound(ds, 0, ds.length, v);
    }
    public static int upperBound(int[] is, int v) {
        return upperBound(is, 0, is.length, v);
    }
    public static int upperBound(long[] ls, long v) {
        return upperBound(ls, 0, ls.length, v);
    }
    public static <T extends Comparable<T>> int upperBound(T[] is, T v) {
        return upperBound(is, 0, is.length, v);
    }
    public static <T extends Comparable<T>> int upperBound(List<T> tl, T v) {
        return upperBound(tl, 0, tl.size(), v);
    }

    public static int upperBound(double[] ds, int l, int r, double v) {
        while (l < r) {
            int m = (l + r) >> 1;
            if (ds[m] > v) r = m;
            else l = m + 1;
        }
        return l;
    }
    public static int upperBound(int[] is, int l, int r, int v) {
        while (l < r) {
            int m = (l + r) >> 1;
            if (is[m] > v) r = m;
            else l = m + 1;
        }
        return l;
    }
    public static int upperBound(long[] ls, int l, int r, long v) {
        while (l < r) {
            int m = (l + r) >> 1;
            if (ls[m] > v) r = m;
            else l = m + 1;
        }
        return l;
    }
    public static<T extends Comparable<T>> int upperBound(T[] ts, int l, int r, T v) {
        while (l < r) {
            int m = (l + r) >> 1;
            if (ts[m].compareTo(v) > 0) r = m;
            else l = m + 1;
        }
        return l;
    }
    public static<T extends Comparable<T>> int upperBound(List<T> tl, int l, int r, T v) {
        while (l < r) {
            int m = (l + r) >> 1;
            if (tl.get(m).compareTo(v) > 0) r = m;
            else l = m + 1;
        }
        return l;
    }

    public static char[] unBox(Character[] Cs) {
        int n = Cs.length;
        char[] cs = new char[n];
        for (int i = 0; i < n; i++) {
            cs[i] = Cs[i];
        }
        return cs;
    }
    public static double[] unBox(Double[] Ds) {
        int n = Ds.length;
        double[] ds = new double[n];
        for (int i = 0; i < n; i++) {
            ds[i] = Ds[i];
        }
        return ds;
    }
    public static int[] unBox(Integer[] Is) {
        int n = Is.length;
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = Is[i];
        }
        return is;
    }
    public static long[] unBox(Long[] Ls) {
        int n = Ls.length;
        long[] ls = new long[n];
        for (int i = 0; i < n; i++) {
            ls[i] = Ls[i];
        }
        return ls;
    }

    public static Character[] box(char[] cs) {
        int n = cs.length;
        Character[] Cs = new Character[n];
        for (int i = 0; i < n; i++) {
            Cs[i] = cs[i];
        }
        return Cs;
    }
    public static Double[] box(double[] ds) {
        int n = ds.length;
        Double[] Ds = new Double[n];
        for (int i = 0; i < n; i++) {
            Ds[i] = ds[i];
        }
        return Ds;
    }
    public static Integer[] box(int[] is) {
        int n = is.length;
        Integer[] Is = new Integer[n];
        for (int i = 0; i < n; i++) {
            Is[i] = is[i];
        }
        return Is;
    }
    public static Long[] box(long[] ls) {
        int n = ls.length;
        Long[] Ls = new Long[n];
        for (int i = 0; i < n; i++) {
            Ls[i] = ls[i];
        }
        return Ls;
    }

    public static void fill(boolean[][] bss, boolean b) {
        for (boolean[] bs : bss) Arrays.fill(bs, b);
    }
    public static void fill(char[][] css, char c) {
        for (char[] cs : css) Arrays.fill(cs, c);
    }
    public static void fill(int[][] iss, int v) {
        for (int[] is : iss) Arrays.fill(is, v);
    }
    public static void fill(long[][] lss, long v) {
        for (long[] ls : lss) Arrays.fill(ls, v);
    }
    public static void fill(double[][] dss, double d) {
        for (double[] ds : dss) Arrays.fill(ds, d);
    }
    public static void fill(boolean[][][] bsss, boolean b) {
        for (boolean[][] bss : bsss) Algo.fill(bss, b);
    }
    public static void fill(char[][][] csss, char c) {
        for (char[][] css : csss) Algo.fill(css, c);
    }
    public static void fill(int[][][] isss, int v) {
        for (int[][] iss : isss) Algo.fill(iss, v);
    }
    public static void fill(long[][][] lsss, long v) {
        for (long[][] lss : lsss) Algo.fill(lss, v);
    }
    public static void fill(double[][][] dsss, double d) {
        for (double[][] dss : dsss) Algo.fill(dss, d);
    }

    public static void sort(char[] cs) {
        sort(cs, 0, cs.length);
    }
    public static void sort(double[] ds) {
        sort(ds, 0, ds.length);
    }
    public static void sort(int[] is) {
        sort(is, 0, is.length);
    }
    public static void sort(long[] ls) {
        sort(ls, 0, ls.length);
    }
    public static <T extends Comparable<T>> void sort(T[] ts) {
        sort(ts, 0, ts.length);
    }
    public static <T> void sort(T[] ts, Comparator<? super T> cmp) {
        sort(ts, 0, ts.length, cmp);
    }
    public static void sort(char[] cs, int b, int e) {
        sort(cs, new char[cs.length], b, e);
    }
    public static void sort(double[] ds, int b, int e) {
        sort(ds, new double[ds.length], b, e);
    }
    public static void sort(int[] is, int b, int e) {
        sort(is, new int[is.length], b, e);
    }
    public static void sort(long[] ls, int b, int e) {
        sort(ls, new long[ls.length], b, e);
    }
    public static <T extends Comparable<T>> void sort(T[] ts, int b, int e) {
        sort(ts, ts.clone(), b, e);
    }
    public static <T> void sort(T[] ts, int b, int e, Comparator<? super T> cmp) {
        sort(ts, ts.clone(), b, e, cmp);
    }

    private static void sort(char[] cs, char[] ts, int b, int e) {
        if (e - b > 1) {
            int m = (b + e) / 2;
            sort(cs, ts, b, m);
            sort(cs, ts, m, e);
            for (int i = b, j = m, k = b; k < e; ) {
                if (j >= e || i < m && cs[i] < cs[j]) ts[k++] = cs[i++];
                else ts[k++] = cs[j++];
            }
            System.arraycopy(ts, b, cs, b, e - b);
        }
    }
    private static void sort(double[] ds, double[] ts, int b, int e) {
        if (e - b > 1) {
            int m = (b + e) / 2;
            sort(ds, ts, b, m);
            sort(ds, ts, m, e);
            for (int i = b, j = m, k = b; k < e; ) {
                if (j >= e || i < m && ds[i] < ds[j]) ts[k++] = ds[i++];
                else ts[k++] = ds[j++];
            }
            System.arraycopy(ts, b, ds, b, e - b);
        }
    }
    private static void sort(int[] is, int[] ts, int b, int e) {
        if (e - b > 1) {
            int m = (b + e) / 2;
            sort(is, ts, b, m);
            sort(is, ts, m, e);
            for (int i = b, j = m, k = b; k < e; ) {
                if (j >= e || i < m && is[i] < is[j]) ts[k++] = is[i++];
                else ts[k++] = is[j++];
            }
            System.arraycopy(ts, b, is, b, e - b);
        }
    }
    private static void sort(long[] ls, long[] ts, int b, int e) {
        if (e - b > 1) {
            int m = (b + e) / 2;
            sort(ls, ts, b, m);
            sort(ls, ts, m, e);
            for (int i = b, j = m, k = b; k < e; ) {
                if (j >= e || i < m && ls[i] < ls[j]) ts[k++] = ls[i++];
                else ts[k++] = ls[j++];
            }
            System.arraycopy(ts, b, ls, b, e - b);
        }
    }
    private static <T extends Comparable<T>> void sort(T[] ts, T[] cs, int b, int e) {
        if (e - b > 1) {
            int m = (b + e) / 2;
            sort(ts, cs, b, m);
            sort(ts, cs, m, e);
            for (int i = b, j = m, k = b; k < e; ) {
                if (j >= e || i < m && ts[i].compareTo(ts[j]) < 0) cs[k++] = ts[i++];
                else cs[k++] = ts[j++];
            }
            System.arraycopy(cs, b, ts, b, e - b);
        }
    }
    private static <T> void sort(T[] ts, T[] cs, int b, int e, Comparator<? super T> cmp) {
        if (e - b > 1) {
            int m = (b + e) / 2;
            sort(ts, cs, b, m, cmp);
            sort(ts, cs, m, e, cmp);
            for (int i = b, j = m, k = b; k < e; ) {
                if (j >= e || i < m && cmp.compare(ts[i], ts[j]) < 0) cs[k++] = ts[i++];
                else cs[k++] = ts[j++];
            }
            System.arraycopy(cs, b, ts, b, e - b);
        }
    }

    public static boolean[] merge(boolean[] is, boolean[] js) {
        int in = is.length;
        int jn = js.length;
        boolean[] rs = new boolean[in + jn];
        System.arraycopy(is, 0, rs, 0, in);
        System.arraycopy(js, 0, rs, in, jn);
        return rs;
    }

    public static char[] merge(char[] is, char[] js) {
        int in = is.length;
        int jn = js.length;
        char[] rs = new char[in + jn];
        System.arraycopy(is, 0, rs, 0, in);
        System.arraycopy(js, 0, rs, in, jn);
        return rs;
    }
    public static double[] merge(double[] is, double[] js) {
        int in = is.length;
        int jn = js.length;
        double[] rs = new double[in + jn];
        System.arraycopy(is, 0, rs, 0, in);
        System.arraycopy(js, 0, rs, in, jn);
        return rs;
    }
    public static int[] merge(int[] is, int[] js) {
        int in = is.length;
        int jn = js.length;
        int[] rs = new int[in + jn];
        System.arraycopy(is, 0, rs, 0, in);
        System.arraycopy(js, 0, rs, in, jn);
        return rs;
    }
    public static long[] merge(long[] is, long[] js) {
        int in = is.length;
        int jn = js.length;
        long[] rs = new long[in + jn];
        System.arraycopy(is, 0, rs, 0, in);
        System.arraycopy(js, 0, rs, in, jn);
        return rs;
    }

    public static boolean equalsOfRange(boolean[] is, int ib, int ie, boolean[] js, int jb, int je) {
        if (ie - ib != je - jb) return false;
        for (int i = ib, j= jb; i < ie; i++, j++) {
            if (is[i] != js[j]) return false;
        }
        return true;
    }
    public static boolean equalsOfRange(char[] is, int ib, int ie, char[] js, int jb, int je) {
        if (ie - ib != je - jb) return false;
        for (int i = ib, j= jb; i < ie; i++, j++) {
            if (is[i] != js[j]) return false;
        }
        return true;
    }
    public static boolean equalsOfRange(double[] is, int ib, int ie, double[] js, int jb, int je) {
        if (ie - ib != je - jb) return false;
        for (int i = ib, j= jb; i < ie; i++, j++) {
            if (is[i] != js[j]) return false;
        }
        return true;
    }
    public static boolean equalsOfRange(int[] is, int ib, int ie, int[] js, int jb, int je) {
        if (ie - ib != je - jb) return false;
        for (int i = ib, j= jb; i < ie; i++, j++) {
            if (is[i] != js[j]) return false;
        }
        return true;
    }
    public static boolean equalsOfRange(long[] is, int ib, int ie, long[] js, int jb, int je) {
        if (ie - ib != je - jb) return false;
        for (int i = ib, j= jb; i < ie; i++, j++) {
            if (is[i] != js[j]) return false;
        }
        return true;
    }
    public static boolean allTheSame(boolean[] is) {
        for (int i = 1; i < is.length; i++) {
            if (is[i] != is[0]) return false;
        }
        return true;
    }
    public static boolean allTheSame(char[] is) {
        for (int i = 1; i < is.length; i++) {
            if (is[i] != is[0]) return false;
        }
        return true;
    }
    public static boolean allTheSame(double[] is) {
        for (int i = 1; i < is.length; i++) {
            if (is[i] != is[0]) return false;
        }
        return true;
    }
    public static boolean allTheSame(int[] is) {
        for (int i = 1; i < is.length; i++) {
            if (is[i] != is[0]) return false;
        }
        return true;
    }
    public static boolean allTheSame(long[] is) {
        for (int i = 1; i < is.length; i++) {
            if (is[i] != is[0]) return false;
        }
        return true;
    }

    // sign = 1: encode;
    // sign = -1: decode
    // n = 2^k
    // use (int) (real[i] + 0.5) to convert to int
    // 1, 1, 1, 1, 1 * 1, 1, 1, 1, 1 = 1, 2, 3, 4, 5, 4, 3, 2, 1
    // fft(1, real, imag);
    // for (int i = 0; i < n; i++) {
    //     double re = real[i] * real[i] - imag[i] * imag[i];
    //     double im = real[i] * imag[i] * 2;
    //     real[i] = re;
    //     imag[i] = im;
    // }
    // fft(-1, real, imag);
    // real := 1, 2, 3, 4, 5, 4, 3, 2, 1, 0
    public static void fft(int sign, double[] real, double[] imag) {
        int n = real.length, d = Integer.numberOfLeadingZeros(n) + 1;
        double theta = sign * 2 * Math.PI / n;
        for (int m = n; m >= 2; m >>= 1, theta *= 2) {
            for (int i = 0, mh = m >> 1; i < mh; i++) {
                double wr = Math.cos(i * theta), wi = Math.sin(i * theta);
                for (int j = i; j < n; j += m) {
                    int k = j + mh;
                    double xr = real[j] - real[k], xi = imag[j] - imag[k];
                    real[j] += real[k];
                    imag[j] += imag[k];
                    real[k] = wr * xr - wi * xi;
                    imag[k] = wr * xi + wi * xr;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int j = Integer.reverse(i) >>> d;
            if (j < i) {
                double tr = real[i]; real[i] = real[j]; real[j] = tr;
                double ti = imag[i]; imag[i] = imag[j]; imag[j] = ti;
            }
        }
        if (sign == -1) for (int i = 0; i < n; i++) {
            real[i] /= n;
            imag[i] /= n;
        }
    }

    public static void radixSort(int[] a) {
        final int d = 8;
        final int w = 32;
        int[] t = new int[a.length];
        for (int p = 0; p < w / d; p++) {
            // counting-sort
            int[] cnt = new int[1 << d];
            for (int i = 0; i < a.length; i++)
                ++cnt[((a[i] ^ Integer.MIN_VALUE) >>> (d * p)) & ((1 << d) - 1)];
            for (int i = 1; i < cnt.length; i++)
                cnt[i] += cnt[i - 1];
            for (int i = a.length - 1; i >= 0; i--)
                t[--cnt[((a[i] ^ Integer.MIN_VALUE) >>> (d * p)) & ((1 << d) - 1)]] = a[i];
            System.arraycopy(t, 0, a, 0, a.length);
        }
    }

    // See: http://www.cplusplus.com/reference/algorithm/nth_element
    public static void nth_element(int[] a, int low, int high, int n) {
        while (true) {
            int k = randomizedPartition(a, low, high);
            if (n < k)
                high = k;
            else if (n > k)
                low = k + 1;
            else
                return;
        }
    }

    static int randomizedPartition(int[] a, int low, int high) {
        swap(a, low + (int) (Math.random() * (high - low)), high - 1);
        int separator = a[high - 1];
        int i = low - 1;
        for (int j = low; j < high; j++)
            if (a[j] <= separator)
                swap(a, ++i, j);
        return i;
    }

    public static void fill(int[][][][] issss, int v) {
        for (int[][][] isss : issss) fill(isss, v);
    }

}
