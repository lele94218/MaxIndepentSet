package com.terryx.algorithm.mis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author taoranxue on 11/28/16 6:29 PM.
 */
public class MaximumSetBruteForce {
    private List<Integer> list;
    private Graph G;
    private int ans;

    public MaximumSetBruteForce(Graph G) {
        this.G = G;
        this.ans = 0;
    }

    public int ms() {
        Set<Integer> set = G.getVertices();
        int size = set.size();
        list = new ArrayList<>(set);
        dfs(0, size, new ArrayList<Integer>());
        return ans;
    }

    private void dfs(int curr, int size, List<Integer> ret) {
        if (curr >= size) {
            for (int v : ret) {
                for (int w : ret) {
                    if (v != w && G.edge(v, w)) {
                        return;
                    }
                }
            }
            ans = Math.max(ret.size(), ans);
            return;
        }
        ret.add(list.get(curr));
        dfs(curr + 1, size, ret);
        ret.remove(ret.size() - 1);
        dfs(curr + 1, size, ret);

    }


}
