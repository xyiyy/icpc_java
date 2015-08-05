package com.shu_mj.tpl;

/**
 * Created by Jun on 12/3/2014.
 */
public class PLL implements Comparable<PLL> {
    public long x;
    public long y;

    public PLL(long x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(PLL o) {
        if (x != o.x) return x < o.x ? -1 : 1;
        if (y != o.y) return y < o.y ? -1 : 1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PLL)) return false;

        PLL pll = (PLL) o;

        if (x != pll.x) return false;
        if (y != pll.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (x ^ (x >>> 32));
        result = 31 * result + (int) (y ^ (y >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
