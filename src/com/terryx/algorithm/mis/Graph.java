package com.terryx.algorithm.mis;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author taoranxue on 11/27/16 4:51 PM.
 */
public class Graph implements Serializable {
    private long serialVersionUID = -409295718270442039L;
    private int V;
    private int E;
    private boolean[] visit;
    private Set<Integer> vertices;
    private Set<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        this.visit = new boolean[V];
        this.vertices = new HashSet<>();
        this.adj = new HashSet[V];
        for (int i = 0; i < V; ++i) vertices.add(i);
        adj = (HashSet<Integer>[]) new HashSet[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new HashSet<>();
        }
    }

    private Graph(int V, Set<Integer> vs) {
        this.V = V;
        this.E = 0;
        this.visit = new boolean[V];
        this.vertices = vs;
        this.adj = new HashSet[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new HashSet<>();
        }
    }

    public void addEdge(int v, int w) {
        if (v == w) return;
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
                    if (vs.contains(w))
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
        set.remove(v);
        set.removeAll(_set);
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

    @Override
    public String toString() {
        String str = "";
        for (int v : getVertices()) {
            str += String.format("%d ", v);
        }
        str += "\n";
        for (int v : getVertices()) {
            for (int w : adj[v]) {
                if (v < w) {
                    String tmp = String.format("%d/%d,", v, w);
                    str += tmp;
                }
            }
        }
        str += "\n";
        return str;
    }
}
