package com.terryx.algorithm.mis;

import java.util.*;

import static com.terryx.algorithm.mis.Utils.*;

/**
 * @author taoranxue on 11/27/16 4:45 PM.
 */
public class MaximumSet {

    public int ms(Graph G) {
//        System.out.println("ms:" + G.getVertices().size());
        if (G.getVertices().size() <= 1) return G.getVertices().size();
        Set<Integer> C = G.isConnected();
        if (!C.equals(G.getVertices())) {
            return ms(G.subGraph(setSubtract(G.getVertices(), C))) + ms(G.subGraph(C));
        }

        int A = G.minDegree();
        int B = G.maxAdjacentDegree(A);

        if (G.degree(A) == 1) {
            return 1 + ms(G.subGraph(setSubtract(G.getVertices(), G.barNeighbour(A))));
        }

        if (G.degree(A) == 2) {
            Set<Integer> set = setSubtract(G.neighbour(A), B);
            int _B = set.iterator().next();

            if (G.edge(B, _B)) {
                return 1 + ms(G.subGraph(setSubtract(G.getVertices(), G.barNeighbour(A))));
            }

            return Math.max(
                    2 + ms(G.subGraph(setSubtract(setSubtract(G.getVertices(), G.barNeighbour(B)), G.barNeighbour(_B)))),
                    1 + ms2(G.subGraph(setSubtract(G.getVertices(), G.barNeighbour(A))), G.neighbour2(A))
            );
        }

        if (G.degree(A) == 3) {
            return Math.max(ms2(G.subGraph(setSubtract(G.getVertices(), A)), G.neighbour(A)), 1 + ms(G.subGraph(setSubtract(G.getVertices(), G.barNeighbour(A)))));
        }

        if (G.isDominate(A, B)) {
            return ms(G.subGraph(setSubtract(G.getVertices(), B)));
        }


        return Math.max(ms(G.subGraph(setSubtract(G.getVertices(), B))), 1 + ms(G.subGraph(setSubtract(G.getVertices(), G.barNeighbour(B)))));

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
//        System.out.println("ms1: " + G.getVertices().size());


//        if (G.getVertices().size() == 12) {
//            System.out.println(G.toString());
//            int breakpoint = 10;
//        }

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

            return Math.max(ms(G.subGraph(setSubtract(G.getVertices(), G.barNeighbour(s1)))),
                    ms(G.subGraph(setSubtract(G.getVertices(), G.barNeighbour(s2))))) + 1;
        }

        if (setCap(G.neighbour(s1), G.neighbour(s2)).size() > 0) {
            return ms1(G.subGraph(setSubtract(G.getVertices(), setCap(G.neighbour(s1), G.neighbour(s2)))), vs);
        }

        if (G.degree(s2) == 2) {
            Set<Integer> set = G.neighbour(s1);
            Iterator<Integer> _it = set.iterator();
            int E = _it.next();
            int F = _it.next();

            if (G.edge(E, F)) {
                return 1 + ms(G.subGraph(setSubtract(G.getVertices(), G.barNeighbour(s1))));
            }
            Set<Integer> addSet = setAdd(G.neighbour(E), G.neighbour(F));
            if (setSubset(setSubtract(addSet, s1), G.neighbour(s2))) {
                return 3 + ms(G.subGraph(setSubtract(setSubtract(G.getVertices(), G.barNeighbour(s1)), G.barNeighbour(s2))));
            }
            return Math.max(1 + ms(G.subGraph(setSubtract(G.getVertices(), G.barNeighbour(s1)))),
                    3 + ms(G.subGraph(setSubtract(setSubtract(setSubtract(G.getVertices(), G.barNeighbour(E)), G.barNeighbour(F)), G.barNeighbour(s2)))));
        }
        Set<Integer> set = setSubtract(G.getVertices(), G.barNeighbour(s1));
        return 1 + Math.max(ms(G.subGraph(setSubtract(G.getVertices(), G.barNeighbour(s2)))), ms2(G.subGraph(setSubtract(set, s2)), G.neighbour(s2)));
    }

    /**
     * Return the maximum size of an independent set of G containing
     * at least two elements of S.
     *
     * @param G  graph G
     * @param vs set S
     * @return the maximum size of IS.
     */
    public int ms2(Graph G, Set<Integer> vs) {
//        System.out.println("ms2");
        List<Integer> list = new ArrayList<>(vs);

        if (vs.size() <= 1) return 0;
        if (vs.size() == 2) {
            Iterator<Integer> it = vs.iterator();
            int s1 = it.next();
            int s2 = it.next();
            if (G.degree(s1) > G.degree(s2)) {
                int tmp = s1;
                s1 = s2;
                s2 = tmp;
            }

            if (G.edge(s1, s2)) {
                return 0;
            }

            return 2 + ms(G.subGraph(setSubtract(setSubtract(G.getVertices(), G.barNeighbour(s1)), G.barNeighbour(s2))));
        }
        if (vs.size() == 3) {
            Iterator<Integer> it = vs.iterator();

            int s1 = it.next();
            int s2 = it.next();
            int s3 = it.next();

            if (G.degree(s1) > G.degree(s2)) {
                int tmp = s1;
                s1 = s2;
                s2 = tmp;
            }

            if (G.degree(s2) > G.degree(s3)) {
                int tmp = s2;
                s2 = s3;
                s3 = tmp;
            }

            if (G.degree(s1) > G.degree(s2)) {
                int tmp = s1;
                s1 = s2;
                s2 = tmp;
            }

            if (G.degree(s1) == 0) {
                return 1 + ms1(G.subGraph(setSubtract(G.getVertices(), s1)), setSubtract(vs, s1));
            }

            if (G.edge(s1, s2) && G.edge(s2, s3) && G.edge(s3, s1)) {
                return 0;
            }

            int _s[] = new int[]{s1, s2, s3};

            for (int i = 0; i < _s.length; ++i) {
                for (int j = 0; j < _s.length; ++j) {
                    for (int k = 0; k < _s.length; ++k) {
                        if (i != j && j != k && k != i) {
                            if (G.edge(_s[i], _s[j]) && G.edge(_s[i], _s[k])) {
                                return 2 + ms(G.subGraph(setSubtract(setSubtract(G.getVertices(), G.barNeighbour(_s[j])), G.barNeighbour(_s[k]))));
                            }

                            if (G.edge(_s[i], _s[j])) {
                                Set<Integer> set = new HashSet<>();
                                set.add(_s[i]);
                                set.add(_s[j]);
                                return 1 + ms1(G.subGraph(setSubtract(G.getVertices(), G.barNeighbour(_s[k]))), set);
                            }
                        }
                    }
                    if (i != j) {
                        Set<Integer> set = setCap(G.neighbour(_s[i]), G.neighbour(_s[j]));
                        if (set.size() > 0) {
                            int v = set.iterator().next();
                            return ms2(G.subGraph(setSubtract(G.getVertices(), v)), setSubtract(vs, v));
                        }
                    }
                }
            }

            if (G.degree(s1) == 1) {
                return 1 + ms1(G.subGraph(setSubtract(G.getVertices(), G.barNeighbour(s1))), setSubtract(vs, s1));
            }

            return Math.max(1 + ms1(G.subGraph(setSubtract(G.getVertices(), G.barNeighbour(s1))), setSubtract(vs, s1)),
                    ms2(G.subGraph(setSubtract(setSubtract(setSubtract(G.getVertices(), G.barNeighbour(s2)), G.barNeighbour(s3)), s1)), G.neighbour(s1)));
        }

        if (vs.size() == 4) {
            int _d = Integer.MAX_VALUE;
            int s1 = 0;
            for (int s : vs) {
                if (G.degree(s) < _d) {
                    _d = G.degree(s);
                    s1 = s;
                }
            }
            if (G.hasDegreeLess(3)) {
                return ms(G);
            }

            return Math.max(1 + ms(G.subGraph(setSubtract(G.getVertices(), G.barNeighbour(s1)))), ms2(G.subGraph(setSubtract(G.getVertices(), s1)), setSubtract(vs, s1)));
        }
        return ms(G);
    }
}
