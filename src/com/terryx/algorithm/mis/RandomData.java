package com.terryx.algorithm.mis;

import java.util.Random;

/**
 * @author taoranxue on 11/28/16 5:20 PM.
 */
public class RandomData {
    public static Graph generateGraph(int V) {
        Random random = new Random();
        Graph graph = new Graph(V);
        for (int u = 0; u < V; ++ u) {
            for (int w = 0; w < V; ++ w) {
                if (w != u) {
                    int flag = random.nextInt(20);
                    if (flag == 0) {
                        graph.addEdge(u, w);
                    }
                }
            }
        }
        return graph;
    }
}
