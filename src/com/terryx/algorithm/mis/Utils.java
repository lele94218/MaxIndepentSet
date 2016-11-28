package com.terryx.algorithm.mis;

import java.util.HashSet;
import java.util.Set;

/**
 * @author taoranxue on 11/27/16 5:49 PM.
 */
public final class Utils {
    private Utils() {

    }

    public static Set<Integer> setSubtract(Set<Integer> s1, Set<Integer> s2) {
        for (Integer v : s2) {
            if (s1.contains(v)) {
                s1.remove(v);
            }
        }
        return s1;
    }

    public static Set<Integer> setAdd(Set<Integer> s1, Set<Integer> s2) {
        Set<Integer> set = new HashSet<>();
        set.addAll(s1);
        set.addAll(s2);
        return set;
    }

    public static boolean setSubset(Set<Integer> s1, Set<Integer> s2) {
        for (int s : s1) {
            if (!s2.contains(s)) return false;
        }
        return true;
    }

    public static Set<Integer> setCap(Set<Integer> s1, Set<Integer> s2) {
        Set<Integer> set = new HashSet<>();
        for (int a : s1) {
            for (int b : s2) {
                if (a == b) {
                    set.add(a);
                }
            }
        }
        return set;
    }
}
