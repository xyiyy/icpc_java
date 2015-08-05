package com.shu_mj.tpl;

/**
 * Created by Jun on 12/3/2014.
 */
public class PLI implements Comparable<PLI> {
    public long x;
    public int y;

    public PLI(long x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(PLI o) {
        if (x != o.x) return x < o.x ? -1 : 1;
        return y - o.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PLI)) return false;

        PLI pli = (PLI) o;

        if (x != pli.x) return false;
        if (y != pli.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (x ^ (x >>> 32));
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
