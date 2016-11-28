package com.terryx.algorithm.mis;

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
}
