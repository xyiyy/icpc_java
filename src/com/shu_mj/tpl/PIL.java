package com.shu_mj.tpl;

/**
 * Created by Jun on 12/3/2014.
 */
public class PIL implements Comparable<PIL> {
    public int x;
    public long y;

    public PIL(int x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(PIL o) {
        if (x != o.x) return x - o.x;
        if (y != o.y) return y < o.y ? -1 : 1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PIL)) return false;

        PIL pil = (PIL) o;

        if (x != pil.x) return false;
        if (y != pil.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + (int) (y ^ (y >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
