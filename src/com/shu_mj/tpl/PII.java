package com.shu_mj.tpl;

/**
 * Created by Jun on 12/3/2014.
 */
public class PII implements Comparable<PII> {
    public int x;
    public int y;

    public PII(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(PII o) {
        if (x != o.x) return x - o.x;
        return y - o.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PII)) return false;

        PII pii = (PII) o;

        if (x != pii.x) return false;
        if (y != pii.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
