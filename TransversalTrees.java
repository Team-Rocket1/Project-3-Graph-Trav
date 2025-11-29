import java.util.*;

public class TransversalTrees {
    public static void main(String[] args) {
        Graph<String> g = new Graph<>();
        int start = 0; // Vertex at "A"

        // Adding vertices from intial graph
        String[] vertices = {"A", "B", "C", "D","E","F","G","H","I"};
        for (int i = 0; i < vertices.length; i++) {
            g.setVertex(i, vertices[i]);

        //Adding egdes from intial graph
        int [][] edges = {
            {0,1}, // A-B
            {0,3}, //A-D
            {0,4}, //A-E
            {1,4}, //B-E
            {3,6}, //D-G
            {4,5}, //E-F
            {4,7}, //E-H
            {6,7}, //G-H
            {5,2}, //F-C
            {5,7}, //F-H
            {7,8}, //H-I
            {2,1}, //C-B
            {8,5}, //I-F
        };
        for (int[] edge : edges) {
            g.addEdge(edge[0], edge[1]);

        }

        System.out.println("Graph:");
        g.printGraph();
        System.out.println("\nTransversal Trees starting from vertex A:");
        g.printTransversalTrees(start);
        


        }
    }
}