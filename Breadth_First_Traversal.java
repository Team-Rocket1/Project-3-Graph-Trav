import java.util.*;
public class Breadth_First_Traversal {

    /*Establish Graph Class: Adjacency Matrix Representation*/
    public static class Graph<E> {
        private boolean[][] edges;      /*2D array to represent edges. E.g., edges[i][j] is true if there is an edge from vertex i to vertex j*/
        private E[] labels;             //Use E to represent labels of vertices.

        @SuppressWarnings("unchecked")  
        public Graph(int n) {                   //Constructor to initialize graph with n vertices. n is number of vertices.
            edges = new boolean[n][n];          //Creates a 2D boolean array to represent edges.
            labels = (E[]) new Object[n];       //Creates an array, (generic type "E") to hold labels of vertices.

        }
    }    

    /*Graph Class Methods*/
    public int size() {                                 //Returns the number of vertices in the graph.  
        return labels.length;
        }

    public void setLabel(int vertex, E newLabel) {      //Sets the label of a vertex to newLabel.
        labels[vertex] = newLabel;
        }

    public E getLabel(int vertex) {                     //Returns the label of a vertex.
        return labels[vertex];
        }

    public void addEdge(int source, int target) {       //Adds an edge from source vertex to target vertex.
        edges[source][target] = true;
        }

    public boolean isEdge(int source, int target) {     //Returns true if there is an edge from source vertex to target vertex; otherwise, returns false.
        return edges[source][target];
        }

    /*
    * Returns an array of integers representing the neighboring vertices of the given vertex. 
    * A neighbor is defined as a vertex that is directly connected to the given vertex by an edge.
    * For vertex v, we look at row v of the adjacency matrix.
    * If edges[v][i] is true, then vertex i is a neighbor of vertex v.
    */
    public int[] neighbors(int vertex) {
        int count = 0;

        for (int i = 0; i < size(); i++) {      //First pass: count how many neighbors.
            if (edges[vertex][i]) {
                    count++;
                }
            }

        int[] result = new int[count];          //Second pass: collect the neighbors.
        int idx = 0;
            for (int i = 0; i < size(); i++) {
                if (edges[vertex][i]) {
                    result[idx++] = i;
                }
            }

            return result;
        }
    /*Breadth_First_Traversal Method*/
    public List<E> breadthFirstTraversal(int origin) {
        boolean[] visited = new boolean[size()];            //Array to keep track of visited vertices. Prevents infinite loops.
        Queue<Integer> vertexQueue = new LinkedList<>();    //Queue. FIFO structure to manage the order of vertex exploration.
        List<E> traversalOrder = new ArrayList<>();         //List to store the order of traversal.

    /*
    * Mark the origin vertex as visited.
    * Enqueue the origin vertex.
    * Add the label of the origin vertex to the traversal order.
    */
        visited[origin] = true;
        vertexQueue.add(origin);
        traversalOrder.add(getLabel(origin));
    /*
    * Dequeue a vertex from the front of the queue.
    * Get its neighbors.
    * If the neighbor has not been visited:
    * Mark it as visited.
    * Enqueue the neighbor.
    * Add the label of the neighbor to the traversal order.
    */
    while (!vertexQueue.isEmpty()) {
        int front = vertexQueue.remove();
        int[] nbrs = neighbors(front);
            for (int n : nbrs) {
                if (!visited[n]) {
                    visited[n] = true;
                    vertexQueue.add(n);
                    traversalOrder.add(getLabel(n));
                }
            }
        }
            return traversalOrder;
    }
}
