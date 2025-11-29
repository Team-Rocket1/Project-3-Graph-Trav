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
    
    /*Graph Class Methods*/
    public int size() {     //Returns the number of vertices in the graph.  
        return labels.length;
        }

    public void setLabel(int vertex, E newLabel) {      //Sets the label of a vertex to newLabel.
        labels[vertex] = newLabel;
        }

    public E getLabel(int vertex) {      //Returns the label of a vertex.
        return labels[vertex];
        }

    public void addEdge(int source, int target) {   //Adds an edge from source vertex to target vertex.
        edges[source][target] = true;
        }

    public boolean isEdge(int source, int target) {     //Returns true if there is an edge from source vertex to target vertex; otherwise, returns false.
        return edges[source][target];
        }
}

