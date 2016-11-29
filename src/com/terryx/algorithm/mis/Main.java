package com.terryx.algorithm.mis;

import java.io.*;

/**
 * @author taoranxue on 11/28/16 5:02 AM.
 */
public class Main {
    public static void main(String args[]) {
//        Graph G = new Graph(11);
//        G.addEdge(0, 1);
//        G.addEdge(1, 2);
//        G.addEdge(2, 3);
//        G.addEdge(3, 6);
//        G.addEdge(6, 5);
//        G.addEdge(5, 4);
//        G.addEdge(4, 8);
//        G.addEdge(5, 8);
//        G.addEdge(5, 10);
//        G.addEdge(5, 2);
//        G.addEdge(0, 4);
//        G.addEdge(1, 5);
//        G.addEdge(9, 10);
//        G.addEdge(8, 9);
//        G.addEdge(7, 9);
//        G.addEdge(7, 4);
//        G.addEdge(1, 4);
//        G.addEdge(10, 6);
//        G.addEdge(0, 5);
//        G.addEdge(3, 5);
//        G.addEdge(7, 8);
//
//       Graph G = RandomData.generateGraph(30);

//        try {
//            ObjectOutputStream os = new ObjectOutputStream(
//                    new FileOutputStream("Graph2.txt"));
//            os.writeObject(G);
//            os.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Graph G = null;
//
//        try {
//            ObjectInputStream is = new ObjectInputStream(new FileInputStream(
//                    "Graph2.txt"));
//            G = (Graph) is.readObject();
//            is.close();
//        } catch (ClassNotFoundException | IOException e) {
//            e.printStackTrace();
//        }

        MaximumSet maximumSet = new MaximumSet();
        int t = 1;
        while (t-- > 0) {
            Graph G = RandomData.generateGraph(110);
            long t1 = System.currentTimeMillis();
            int res = maximumSet.ms(G);
            long t2 = System.currentTimeMillis();
            System.out.println(t2 - t1);
//
//            MaximumSetBruteForce maximumSetBruteForce = new MaximumSetBruteForce(G);
//            t1 = System.currentTimeMillis();
//            res = maximumSetBruteForce.ms();
//            t2 = System.currentTimeMillis();
//            System.out.println(t2 - t1);

        }

    }
}
