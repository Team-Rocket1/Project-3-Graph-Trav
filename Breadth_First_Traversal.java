import java.util.*;
public class Breadth_First_Traversal {

    /*Establish Graph Class: Adjacency Matrix Representation*/
    public static class Graph<E> {
        private boolean[][] edges;           /*2D array to represent edges. E.g., edges[i][j] is true if there is an edge from vertex i to vertex j*/
        private E[] labels;                 //Use E to represent labels of vertices.

        @SuppressWarnings("unchecked")  
        public Graph(int n) {               //Constructor to initialize graph with n vertices. n is the number of vertices.
            edges = new boolean[n][n];      //Creates a 2D boolean array to represent edges.
            labels = (E[]) new Object[n];   //Creates an array, (generic type "E") to hold labels of vertices.

        }
    }    
}
