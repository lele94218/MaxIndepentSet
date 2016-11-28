package com.terryx.algorithm.mis;

import java.util.HashSet;
import java.util.Iterator;
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

        if (G.isDominate(A, B)) {
            Set<Integer> set = G.getVertices();
            set.remove(B);
            return ms(G.subGraph(set));
        }


        Set<Integer> set = G.getVertices();
        set.remove(B);
        return Math.max(ms(G.subGraph(set)), 1 + ms(G.subGraph(Utils.setSubtract(G.getVertices(), G.barNeighbour(B)))));

    }

    /**
     * This is only called with S a two element subset of the vertices
     * of G. It returns the size of an independent set of G at least as
     * large as the largest such set which contains an element of S.
     * <p>
     * The elements of S are s1 and s2 with d(s1) <= d(s2)
     *
     * @param G  Graph G.
     * @param vs set S.
     * @return size of an independent set of G
     */
    public int ms1(Graph G, Set<Integer> vs) {
        Iterator<Integer> it = vs.iterator();
        int s1 = it.next();
        int s2 = it.next();

        if (G.degree(s1) > G.degree(s2)) {
            int tmp = s1;
            s1 = s2;
            s2 = tmp;
        }

        if (G.degree(s1) <= 1) {
            return ms(G);
        }

        if (G.edge(s1, s2)) {
            if (G.degree(s1) <= 3) {
                return ms(G);
            }

            return Math.max(ms(G.subGraph(Utils.setSubtract(G.getVertices(), G.barNeighbour(s1)))),
                    ms(G.subGraph(Utils.setSubtract(G.getVertices(), G.barNeighbour(s2))))) + 1;
        }

        if (Utils.setCap(G.neighbour(s1), G.neighbour(s2)).size() == 0) {
            return ms1(G.subGraph(Utils.setSubtract(G.getVertices(), Utils.setCap(G.neighbour(s1), G.neighbour(s2)))), vs);
        }

        if (G.degree(s2) == 2) {
            Set<Integer> set = G.neighbour(s1);

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
