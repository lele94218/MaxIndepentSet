package com.terryx.algorithm.mis;

/**
 * @author taoranxue on 11/28/16 5:02 AM.
 */
public class Main {
    public static void main(String args[]) {
        Graph G = new Graph(11);
        G.addEdge(0, 1);
        G.addEdge(1, 2);
        G.addEdge(2, 3);
        G.addEdge(3, 6);
        G.addEdge(6, 5);
        G.addEdge(5, 4);
        G.addEdge(4, 8);
        G.addEdge(5, 8);
        G.addEdge(5, 10);
        G.addEdge(5, 2);
        G.addEdge(0, 4);
        G.addEdge(1, 5);
        G.addEdge(9, 10);
        G.addEdge(8, 9);
        G.addEdge(7, 9);
        G.addEdge(7, 4);
        G.addEdge(1, 4);
        G.addEdge(10, 6);

        MaximumSet maximumSet = new MaximumSet();
        System.out.println(maximumSet.ms(G));
    }
}
