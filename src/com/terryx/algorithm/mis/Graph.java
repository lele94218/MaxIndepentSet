package com.terryx.algorithm.mis;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author taoranxue on 11/27/16 4:51 PM.
 */
public class Graph {
    private int V;
    private int E;
    private boolean[] visit;
    private Set<Integer> vertices;
    private LinkedList<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<>();
        }
    }

    public Graph(int V, Set<Integer> vs) {
        this(V);
        this.vertices = vs;
    }

    public void addEdge(int v, int w) {
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }

    public int degree(int v) {
        return adj[v].size();
    }

    public Graph subGraph(Set<Integer> vs) {
        Graph graph = new Graph(V, vs);
        for (int v = 0; v < V; ++v) {
            if (vs.contains(v)) {
                for (Integer w : adj[v]) {
                    graph.addEdge(v, w);
                }
            }
        }
        return graph;
    }

    public Set<Integer> getVertices() {
        return vertices;
    }

    public Set<Integer> isConnected() {
        Set<Integer> set = new HashSet<>();
        int v = vertices.iterator().next();
        dfs(v, set);
        return set;
    }

    private void dfs(int v, Set<Integer> set) {
        set.add(v);
        visit[v] = true;
        for (int w : adj[v]) {
            if (!visit[w]) {
                dfs(w, set);
            }
        }
    }

    public int minDegree() {
        int retV = Integer.MAX_VALUE;
        int _v = 0;
        for (int v : vertices) {
            if (degree(v) < retV) {
                retV = degree(v);
                _v = v;
            }
        }
        return _v;
    }

    public int maxAdjacentDegree(int v) {
        int retW = Integer.MIN_VALUE;
        int _w = 0;
        for (int w : adj[v]) {
            if (degree(w) > retW) {
                retW = degree(w);
                _w = w;
            }
        }
        return _w;
    }

    public Set<Integer> neighbour(int v) {
        Set<Integer> set = new HashSet<>();
        set.addAll((adj[v]));
        return set;
    }

    public Set<Integer> neighbour2(int v) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> _set = neighbour(v);
        for (int w : _set) {
            set.addAll(neighbour(w));
        }
        return set;
    }

    public Set<Integer> barNeighbour(int v) {
        Set<Integer> set = neighbour(v);
        set.add(v);
        return set;
    }

    public boolean edge(int v, int w) {
        return adj[v].contains(w);
    }

    public boolean isDominate(int a, int b) {
        return Utils.setSubset(barNeighbour(a), barNeighbour(b));
    }

    public boolean hasDegreeLess(int d) {
        for (int v : vertices) {
            if (degree(v) <= d) return true;
        }
        return false;
    }
}
