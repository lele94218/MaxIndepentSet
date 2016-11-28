package com.terryx.algorithm.mis;

import javax.rmi.CORBA.Util;
import java.util.HashSet;
import java.util.Set;

/**
 * @author taoranxue on 11/27/16 4:45 PM.
 */
public class MaximumSet {
    private Graph G;

    public MaximumSet(Graph G) {
        this.G = G;
    }

    public int ms(Graph G) {
        if (G.getVertices().size() <= 1) return 1;
        Set<Integer> C = G.isConnected();
        if (!C.equals(G.getVertices())) {
            return ms(G.subGraph(Utils.setSubtract(G.getVertices(), C))) + ms(G.subGraph(C));
        }

        int A = G.minDegree();
        int B = G.maxAdjacentDegree(A);

        if (G.degree(A) == 1) {
            return 1 + ms(G.subGraph(Utils.setSubtract(G.getVertices(), G.barNeighbour(A))));
        }

        if (G.degree(A) == 2) {
            Set<Integer> set = G.neighbour(A);
            set.remove(B);
            int _B = set.iterator().next();

            if (G.edge(B, _B)) {
                return 1 + ms(G.subGraph(Utils.setSubtract(G.getVertices(), G.barNeighbour(A))));
            }

            return Math.max(
                    2 + ms(G.subGraph(Utils.setSubtract(Utils.setSubtract(G.getVertices(), G.barNeighbour(B)), G.barNeighbour(_B)))),
                    1 + ms2(G.subGraph(Utils.setSubtract(G.getVertices(), G.barNeighbour(A))), G.neighbour2(A))
            );
        }

        if (G.degree(A) == 3) {
            Set<Integer> set = G.getVertices();
            set.remove(A);
            return Math.max(ms2(G.subGraph(set), G.neighbour(A)), 1 + ms(G.subGraph(Utils.setSubtract(G.getVertices(), G.barNeighbour(A)))));
        }


        return -1;

    }

    public int ms2(Graph G, Set<Integer> vs) {
        return -1;
    }

    public static void main(String args[]) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(4);
        System.out.println(set.iterator().next());
    }
}
