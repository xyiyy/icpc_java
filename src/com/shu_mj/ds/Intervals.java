package com.shu_mj.ds;

import java.util.*;

/**
 * Created by Jun on 6/6/2014.
 */
public class Intervals<K, V> {
    public TreeMap<K, V> map = new TreeMap<K, V>();

    public Intervals(K min, K max, V ini) {
        map.put(min, ini);
        map.put(max, ini);
    }

    public void paint(K s, K t, V c) {
        V p = get(t);
        map.subMap(s, t).clear();
        if (!get(s).equals(c)) map.put(s, c);
        if (!get(t).equals(p)) map.put(t, p);
        if (p.equals(c)) map.remove(t);
    }

    public V get(K k) {
        return map.floorEntry(k).getValue();
    }

}
