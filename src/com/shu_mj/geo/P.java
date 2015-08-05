package com.shu_mj.geo;

/**
 * Created by Jun on 6/7/2014.
 */

import com.shu_mj.tpl.Algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class P implements Comparable<P> {
    public static final double EPS = 1e-9;
    public static double add(double a, double b) {
        if (Math.abs(a + b) < EPS * (Math.abs(a) + Math.abs(b))) return 0;
        return a + b;
    }

    public final double x, y;

    public P(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public P sub(P p) {
        return new P(add(x, -p.x), add(y, -p.y));
    }

    public P add(P p) {
        return new P(add(x, p.x), add(y, p.y));
    }

    public P mul(double k) {
        return new P(x * k, y * k);
    }

    public P div(double k) {
        return new P(x / k, y / k);
    }

    public double det(P p) {
        return add(x * p.y, -y * p.x);
    }

    public double dot(P p) {
        return add(x * p.x, y * p.y);
    }

    public double abs() {
        return Math.sqrt(abs2());
    }

    public double abs2() {
        return dot(this);
    }

    //饶原点旋转角度B（弧度值）产生的新点
    public P rot(double rad) {
        return new P(add(x * Math.cos(rad), -y * Math.sin(rad)), add(x * Math.sin(rad), y * Math.cos(rad)));
    }

    public P rot90() {
        return new P(-y, x);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return compareTo((P) obj) == 0;
    }

    @Override
    public int compareTo(P p) {
        int b = sig(x - p.x);
        if (b != 0) return b;
        return sig(y - p.y);
    }

    public static int sig(double x) {
        if (Math.abs(x) < EPS) return 0;
        return x < 0 ? -1 : 1;
    }

    //线段相交判定
    public static boolean crsSS(P p1, P p2, P q1, P q2) {
        if (Math.max(p1.x, p2.x) + EPS < Math.min(q1.x, q2.x)) return false;
        if (Math.max(q1.x, q2.x) + EPS < Math.min(p1.x, p2.x)) return false;
        if (Math.max(p1.y, p2.y) + EPS < Math.min(q1.y, q2.y)) return false;
        if (Math.max(q1.y, q2.y) + EPS < Math.min(p1.y, p2.y)) return false;
        return p2.sub(p1).det(q1.sub(p1)) * p2.sub(p1).det(q2.sub(p1)) <= 0
                && q2.sub(q1).det(p1.sub(q1)) * q2.sub(q1).det(p2.sub(q1)) <= 0;
    }

    //直线和线段的相交判定
    public static boolean crsLS(P l1, P l2, P s1, P s2) {
        return s1.sub(l2).det(l1.sub(l2)) * s2.sub(l2).det(l1.sub(l2)) <= 0;
    }

    //直线相交判定
    //返回-1表示重合，为0表示平行，为1表示相交
    public static int crsLL(P p1, P p2, P q1, P q2) {
        if (sig(p1.sub(p2).det(q1.sub(q2))) != 0) return 1;
        if (sig(p1.sub(q2).det(q1.sub(p2))) != 0) return 0;
        return -1;
    }

    //直线和直线的交点
    public static P isLL(P p1, P p2, P q1, P q2) {
        double d = q2.sub(q1).det(p2.sub(p1));
        if (sig(d) == 0) return null;
        return p1.add(p2.sub(p1).mul(q2.sub(q1).det(q1.sub(p1)) / d));
    }

    //点到直线的垂足
    public static P proj(P p1, P p2, P q) {
        return p1.add(p2.sub(p1).mul(p2.sub(p1).dot(q.sub(p1)) / p2.sub(p1).abs2()));
    }

    //计算多边形的有向面积
    //点不需要有顺序
    public static double directedArea(P... ps) {
        double res = 0;
        for (int i = 0; i < ps.length; i++) {
            res += ps[i].det(ps[(i + 1) % ps.length]);
        }
        return res / 2;
    }
    //计算多边形的面积
    //点不需要有顺序
    public static double area(P... ps) {
        return Math.abs(directedArea(ps));
    }

    //线段到点的距离
    public static double disSP(P p1, P p2, P q) {
        if (p2.sub(p1).dot(q.sub(p1)) <= 0) return q.sub(p1).abs();
        if (p1.sub(p2).dot(q.sub(p2)) <= 0) return q.sub(p2).abs();
        return disLP(p1, p2, q);
    }

    //直线到点的距离
    public static double disLP(P p1, P p2, P q) {
        return Math.abs(p2.sub(p1).det(q.sub(p1))) / p2.sub(p1).abs();
    }

    //圆和线段的相交判定
    public static boolean crsCS(P c, double r, P p1, P p2) {
        return disSP(p1, p2, c) < r + EPS &&
                (r < c.sub(p1).abs() + EPS || r < c.sub(p2).abs() + EPS);
    }

    //圆和圆的相交判定
    public static boolean crsCC(P c1, double r1, P c2, double r2) {
        double dis = c1.sub(c2).abs();
        return dis < r1 + r2 + EPS && Math.abs(r1 - r2) < dis + EPS;
    }

    //四点共圆判定
    public static boolean onC(P p1, P p2, P p3, P p4) {
        P c = CCenter(p1, p2, p3);
        if (c == null) return false; //有三点共线，返回false
        return add(c.sub(p1).abs2(), -c.sub(p4).abs2()) == 0;
    }

    //三点共圆的圆心
    public static P CCenter(P p1, P p2, P p3) {
        if (disLP(p1, p2, p3) < EPS) return null; // 三点共线
        P q1 = p1.add(p2).mul(0.5);
        P q2 = q1.add(p1.sub(p2).rot90());
        P s1 = p3.add(p2).mul(0.5);
        P s2 = s1.add(p3.sub(p2).rot90());
        return isLL(q1, q2, s1, s2);
    }

    //直线和圆的交点
    public static P[] isCL(P c, double r, P p1, P p2) {
        double x = p1.sub(c).dot(p2.sub(p1));
        double y = p2.sub(p1).abs2();
        double d = add(x * x, -y * (add(p1.sub(c).abs2(), -r * r)));
        if (d < -EPS) return new P[0];
        if (d < 0) d = 0;
        P q1 = p1.sub(p2.sub(p1).mul(x / y));
        P q2 = p2.sub(p1).mul(Math.sqrt(d) / y);
        return new P[]{q1.sub(q2), q1.add(q2)};
    }

    //两圆的交点
    public static P[] isCC(P c1, double r1, P c2, double r2) {
        double x = c1.sub(c2).abs2();
        double y = (add(r1 * r1, -r2 * r2) / x + 1) / 2;
        double d = add(r1 * r1 / x, -y * y);
        if (d < -EPS) return new P[0];
        if (d < 0) d = 0;
        P q1 = c1.add(c2.sub(c1).mul(y));
        P q2 = c2.sub(c1).mul(Math.sqrt(d)).rot90();
        return new P[]{q1.sub(q2), q1.add(q2)};
    }

    //点和圆的两个切点
    public static P[] tanCP(P c, double r, P p) {
        double x = p.sub(c).abs2();
        double d = add(x, -r * r);
        if (d < -EPS) return new P[0];
        if (d < 0) d = 0;
        P q1 = p.sub(c).mul(r * r / x);
        P q2 = p.sub(c).mul(-r * Math.sqrt(d) / x).rot90();
        return new P[]{c.add(q1.sub(q2)), c.add(q1.add(q2))};
    }

    //两圆的公切线
    //返回的是切点对
    public static P[][] tanCC(P c1, double r1, P c2, double r2) {
        List<P[]> list = new ArrayList<P[]>();
        if (Math.abs(r1 - r2) < EPS) {
            P dir = c2.sub(c1);
            dir = dir.mul(r1 / dir.abs()).rot90();
            list.add(new P[]{c1.add(dir), c2.add(dir)});
            list.add(new P[]{c1.sub(dir), c2.sub(dir)});
        } else {
            P p = c1.mul(-r2).add(c2.mul(r1)).div(r1 - r2);
            P[] ps = tanCP(c1, r1, p);
            P[] qs = tanCP(c2, r2, p);
            for (int i = 0; i < ps.length && i < qs.length; i++) {
                list.add(new P[]{ps[i], qs[i]});
            }
        }
        P p = c1.mul(r2).add(c2.mul(r1)).div(r1 + r2);
        P[] ps = tanCP(c1, r1, p);
        P[] qs = tanCP(c2, r2, p);
        for (int i = 0; i < ps.length && i < qs.length; i++) {
            list.add(new P[]{ps[i], qs[i]});
        }
        return list.toArray(new P[0][]);
    }

    //两圆公共部分的面积
    public static double areaCC(P c1, double r1, P c2, double r2) {
        double d = c1.sub(c2).abs();
        if (r1 + r2 < d + EPS) return 0;
        if (d < Math.abs(r1 - r2) + EPS) {
            double r = Math.min(r1, r2);
            return r * r * Math.PI;
        }
        double x = (d * d + r1 * r1 - r2 * r2) / (2 * d);
        double t1 = Math.acos(x / r1);
        double t2 = Math.acos((d - x) / r2);
        return r1 * r1 * t1 + r2 * r2 * t2 - d * r1 * Math.sin(t1);
    }

    //以r为半径的圆O与三角形Op1p2的公共面积
    //O为坐标原点
    //注意返回值可能为负
    public static double areaCT(double r, P p1, P p2) {
        P[] qs = isCL(new P(0, 0), r, p1, p2);
        if (qs.length == 0) return r * r * rad(p1, p2) / 2;
        boolean b1 = p1.abs() > r + EPS, b2 = p2.abs() > r + EPS;
        if (b1 && b2) {
            if (p1.sub(qs[0]).dot(p2.sub(qs[0])) < EPS &&
                    p1.sub(qs[1]).dot(p2.sub(qs[1])) < EPS) {
                return (r * r * (rad(p1, p2) - rad(qs[0], qs[1])) + qs[0].det(qs[1])) / 2;
            } else {
                return r * r * rad(p1, p2) / 2;
            }
        } else if (b1) {
            return (r * r * rad(p1, qs[0]) + qs[0].det(p2)) / 2;
        } else if (b2) {
            return (r * r * rad(qs[1], p2) + p1.det(qs[1])) / 2;
        } else {
            return p1.det(p2) / 2;
        }
    }

    //返回两点和原点形成的夹角
    //注意这两点都不能为原点
    public static double rad(P p1, P p2) {
        return Math.acos(p1.dot(p2) / p1.abs() / p2.abs());
    }

    //返回 0~2PI 的角
    public static double rad2(P p1, P p2) {
        double r = (p1.det(p2) < -EPS ? -1 : 1) * rad(p1, p2);
        if (r < 0) r += 2 * Math.PI;
        return r;
    }

    //凸包
    //逆时针 不包含线上的点
    //如果需要包含线上的点 将 <= 0 改成 < 0
    //但是需要注意此时不能有重点
    public static P[] convexHull(P[] ps) {
        int n = ps.length, k = 0;
        if (n <= 1) return ps;
        Arrays.sort(ps);
        P[] qs = new P[n * 2];
        for (int i = 0; i < n; qs[k++] = ps[i++]) {
            while (k > 1 && qs[k - 1].sub(qs[k - 2]).det(ps[i].sub(qs[k - 1])) < EPS) k--;
        }
        for (int i = n - 2, t = k; i >= 0; qs[k++] = ps[i--]) {
            while (k > t && qs[k - 1].sub(qs[k - 2]).det(ps[i].sub(qs[k - 1])) < EPS) k--;
        }
        P[] res = new P[k - 1];
        System.arraycopy(qs, 0, res, 0, k - 1);
        return res;
    }

    // 按相对于 p0 的极角逆时针排序
    // 角度相同，则离 p0 距离更近的放在前面
    public static class CmpByAngle implements Comparator<P> {
        P p0;

        CmpByAngle(P p0) {
            this.p0 = p0;
        }

        @Override
        public int compare(P o1, P o2) {
            double det = o1.sub(p0).det(o2.sub(p0));
            if (det != 0) return det > 0 ? -1 : 1;
            double dis = add(o1.sub(p0).abs2(), -o2.sub(p0).abs2());
            if (dis != 0) return dis > 0 ? 1 : -1;
            return 0;
        }
    }

    public static P[] convexHullByAngle(P[] ps) {
        int n = ps.length, k = 0;
        if (n <= 1) return ps;
        for (int i = 1; i < n; i++) {
            if (ps[i].y < ps[0].y || ps[i].y == ps[0].y && ps[i].x < ps[0].x) {
                Algo.swap(ps, 0, i);
            }
        }
        Arrays.sort(ps, 1, n, new CmpByAngle(ps[0]));
        P[] qs = new P[n];
        for (int i = 0; i < n; qs[k++] = ps[i++]) {
            while (k > 1 && qs[k - 1].sub(qs[k - 2]).det(ps[i].sub(qs[k - 1])) < EPS) k--;
        }
        return Arrays.copyOf(qs, k);
    }
    //凸多边形的切断
    //返回 p1p2 左侧凸包
    public static P[] convexCut(P[] ps, P p1, P p2) {
        int n = ps.length;
        ArrayList<P> res = new ArrayList<P>();
        for (int i = 0; i < n; i++) {
            int d1 = sig(p2.sub(p1).det(ps[i].sub(p1)));
            int d2 = sig(p2.sub(p1).det(ps[(i + 1) % n].sub(p1)));
            if (d1 >= 0) res.add(ps[i]);
            if (d1 * d2 < 0) res.add(isLL(p1, p2, ps[i], ps[(i + 1) % n]));
        }
        return res.toArray(new P[0]);
    }

    //点在多边形内外的判定
    //内部返回1，边上返回0，外部返回-1
    public static int contains(P[] ps, P q) {
        int n = ps.length;
        int res = -1;
        for (int i = 0; i < n; i++) {
            P a = ps[i].sub(q), b = ps[(i + 1) % n].sub(q);
            if (a.y > b.y) { P t = a; a = b; b = t; }
            if (a.y < EPS && b.y > EPS && a.det(b) > EPS) {
                res = -res;
            }
            if (Math.abs(a.det(b)) < EPS && a.dot(b) < EPS) return 0;
        }
        return res;
    }

    //凸多边形与外部点的距离
    public static double disConvexP(P[] ps, P q) {
        int n = ps.length;
        int left = 0, right = n;
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (in(ps[(left + n - 1) % n], ps[left], ps[mid], ps[(mid + 1) % n], q)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return disSP(ps[left], ps[right % n], q);
    }

    public static boolean in(P p1, P p2, P p3, P p4, P q) {
        P o12 = p1.sub(p2).rot90();
        P o23 = p2.sub(p3).rot90();
        P o34 = p3.sub(p4).rot90();
        return in(o12, o23, q.sub(p2)) || in(o23, o34, q.sub(p3))
                || in(o23, p3.sub(p2), q.sub(p2)) && in(p2.sub(p3), o23, q.sub(p3));
    }

    public static boolean in(P p1, P p2, P q) {
        return p1.det(q) > -EPS && p2.det(q) < EPS;
    }

    //凸多边形的直径
    //凸多边形上最远点的距离
    //O(n)
    public static double convexDiameter(P[] ps) {
        int n = ps.length;
        int is = 0, js = 0;
        for (int i = 1; i < n; i++) {
            if (ps[i].x > ps[is].x) is = i;
            if (ps[i].x < ps[js].x) js = i;
        }
        double maxD = ps[is].sub(ps[js]).abs();
        int i = is, j = js;
        do {
            if (ps[(i + 1) % n].sub(ps[i]).det(ps[(j + 1) % n].sub(ps[j])) >= 0) {
                j = (j + 1) % n;
            } else {
                i = (i + 1) % n;
            }
            maxD = Math.max(maxD, ps[i].sub(ps[j]).abs());
        } while (i != is || j != js);
        return maxD;
    }
}
