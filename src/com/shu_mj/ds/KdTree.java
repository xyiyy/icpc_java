package com.shu_mj.ds;

import com.shu_mj.tpl.Algo;

import java.util.Random;

/**
 * Created by Jun on 3/19/2015.
 */
public class KdTree {

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int[] tx;
    int[] ty;

    public KdTree(Point[] points) {
        int n = points.length;
        tx = new int[n];
        ty = new int[n];
        build(0, n, true, points);
    }

    void build(int low, int high, boolean divX, Point[] points) {
        if (low >= high)
            return;
        int mid = (low + high) >>> 1;
        nth_element(points, low, high, mid, divX);

        tx[mid] = points[mid].x;
        ty[mid] = points[mid].y;

        build(low, mid, !divX, points);
        build(mid + 1, high, !divX, points);
    }

    // See: http://www.cplusplus.com/reference/algorithm/nth_element
    static void nth_element(Point[] a, int low, int high, int n, boolean divX) {
        while (true) {
            int k = randomizedPartition(a, low, high, divX);
            if (n < k)
                high = k;
            else if (n > k)
                low = k + 1;
            else
                return;
        }
    }

    static final Random rnd = new Random();

    static int randomizedPartition(Point[] a, int low, int high, boolean divX) {
        Algo.swap(a, low + rnd.nextInt(high - low), high - 1);
        int v = divX ? a[high - 1].x : a[high - 1].y;
        int i = low - 1;
        for (int j = low; j < high; j++)
            if (divX ? a[j].x <= v : a[j].y <= v)
                Algo.swap(a, ++i, j);
        return i;
    }

    long bestDist;
    int bestNode;

    public int findNearestNeighbour(int x, int y) {
        bestDist = Long.MAX_VALUE;
        findNearestNeighbour(0, tx.length, x, y, true);
        return bestNode;
    }

    void findNearestNeighbour(int low, int high, int x, int y, boolean divX) {
        if (low >= high)
            return;
        int mid = (low + high) >>> 1;
        long dx = x - tx[mid];
        long dy = y - ty[mid];
        long dist = dx * dx + dy * dy;
        if (bestDist > dist) {
            bestDist = dist;
            bestNode = mid;
        }
        long delta = divX ? dx : dy;
        long delta2 = delta * delta;

        if (delta <= 0) {
            findNearestNeighbour(low, mid, x, y, !divX);
            if (delta2 < bestDist)
                findNearestNeighbour(mid + 1, high, x, y, !divX);
        } else {
            findNearestNeighbour(mid + 1, high, x, y, !divX);
            if (delta2 < bestDist)
                findNearestNeighbour(low, mid, x, y, !divX);
        }
    }
}
