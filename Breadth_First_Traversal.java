import java.util.*;
/**
 * Example driver class demonstrating a breadth-first traversal on a directed graph
 * implemented using an adjacency matrix representation.
 *
 * <p>The nested {@code Graph<E>} type is a small generic graph implementation used in
 * the example. This class builds a sample graph, performs a breadth-first traversal
 * from a designated origin vertex, and prints the traversal order.</p>
 */
public class Breadth_First_Traversal {

    /**
     * Simple generic graph implementation using an adjacency matrix.
     *
     * <p>Vertices are referred to by their integer indices in the range [0, {@link
     * #size()} - 1]. Edges are stored in a square boolean matrix where {@code
     * edges[i][j] == true} indicates a directed edge from vertex {@code i} to
     * vertex {@code j}. Each vertex may also have a label of generic type {@code E}.</p>
     *
     * @param <E> vertex label type
     */
    public static class Graph<E> {
        /** Adjacency matrix where edges[i][j] is {@code true} if there is an edge from i to j. */
        private boolean[][] edges;

        /** Labels for each vertex. The array length is the number of vertices. */
        private E[] labels;             //Use E to represent labels of vertices.

        @SuppressWarnings("unchecked")  
        /**
         * Construct a graph with {@code n} vertices and no edges.
         *
         * @param n the number of vertices in the graph (must be non-negative)
         * @throws NegativeArraySizeException if {@code n} is negative
         */
        public Graph(int n) {                   //Constructor to initialize graph with n vertices. n is number of vertices.
            edges = new boolean[n][n];          //Creates a 2D boolean array to represent edges.
            labels = (E[]) new Object[n];       //Creates an array, (generic type "E") to hold labels of vertices.

        }

    /*Graph Class Methods*/
    /**
     * Return the number of vertices in this graph.
     *
     * @return the number of vertices
     */
    public int size() {                                 //Returns the number of vertices in the graph.  
        return labels.length;
        }

    /**
     * Set the label for a given vertex index.
     *
     * @param vertex the index of the vertex to label
     * @param newLabel the new label to set
     * @throws ArrayIndexOutOfBoundsException if {@code vertex} is out of bounds
     */
    public void setLabel(int vertex, E newLabel) {      //Sets the label of a vertex to newLabel.
        labels[vertex] = newLabel;
        }

    /**
     * Return the label for the given vertex index.
     *
     * @param vertex the index of the vertex
     * @return the label at the vertex (may be {@code null} if no label is set)
     * @throws ArrayIndexOutOfBoundsException if {@code vertex} is out of bounds
     */
    public E getLabel(int vertex) {                     //Returns the label of a vertex.
        return labels[vertex];
        }

    /**
     * Add a directed edge from {@code source} to {@code target}.
     *
     * @param source the source vertex index
     * @param target the target vertex index
     * @throws ArrayIndexOutOfBoundsException if {@code source} or {@code target} are out of bounds
     */
    public void addEdge(int source, int target) {       //Adds an edge from source vertex to target vertex.
        edges[source][target] = true;
        }

    /**
     * Return {@code true} if there is a directed edge from {@code source} to {@code target}.
     *
     * @param source the source vertex index
     * @param target the target vertex index
     * @return {@code true} if an edge exists, otherwise {@code false}
     * @throws ArrayIndexOutOfBoundsException if {@code source} or {@code target} are out of bounds
     */
    public boolean isEdge(int source, int target) {     //Returns true if there is an edge from source vertex to target vertex; otherwise, returns false.
        return edges[source][target];
        }
        

    /*
    * Returns an array of integers representing the neighboring vertices of the given vertex. 
    * A neighbor is defined as a vertex that is directly connected to the given vertex by an edge.
    * For vertex v, we look at row v of the adjacency matrix.
    * If edges[v][i] is true, then vertex i is a neighbor of vertex v.
    */
    /**
     * Return the neighbors (outgoing adjacent vertices) of a vertex.
     *
     * <p>The method iterates over the row {@code edges[vertex]} and collects
     * indices {@code i} such that {@code edges[vertex][i] == true}.</p>
     *
     * @param vertex the vertex whose neighbors are being requested
     * @return an array of indices representing neighbors of {@code vertex}
     * @throws ArrayIndexOutOfBoundsException if {@code vertex} is out of bounds
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
    /**
     * Perform a breadth-first traversal starting at {@code origin} and
     * return the list of vertex labels in the order they were visited.
     *
     * <p>Vertices are visited in breadth-first order using a queue and a
     * visited set to prevent revisiting vertices.</p>
     *
     * @param origin the index of the starting vertex
     * @return a list of labels describing the BFS visit order
     * @throws ArrayIndexOutOfBoundsException if {@code origin} is out of bounds
     */
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
    /*Building Graph*/
    /**
     * Build a sample graph using letters 'A'..'I' as labels and perform a
     * breadth-first traversal starting from vertex 'A'. Output is printed to
     * standard output.
     *
     * @param args command-line args (unused)
     */
    public static void main(String[] args) {
        Graph<Character> g = new Graph<>(9);    //Create a graph with 9 vertices.

        char[] labels = {'A','B','C','D','E','F','G','H','I'};  //Labels for the vertices (characters A to I represent the vertices 0 to 8).
        for (int i = 0; i < labels.length; i++) {
            g.setLabel(i, labels[i]);
        }

    /*
    Map to associate each label with its corresponding vertex index. 
    Allows easy edge addition using letters instead of numbers. 
    */
        Map<Character,Integer> indexOf = new HashMap<>();
        for (int i = 0; i < labels.length; i++) {
            indexOf.put(labels[i], i);
        }

        addEdge(g, indexOf, 'A', 'B');
        addEdge(g, indexOf, 'A', 'D');
        addEdge(g, indexOf, 'A', 'E');
        addEdge(g, indexOf, 'B', 'E');
        addEdge(g, indexOf, 'D', 'G');
        addEdge(g, indexOf, 'E', 'F');
        addEdge(g, indexOf, 'E', 'H');
        addEdge(g, indexOf, 'G', 'H');
        addEdge(g, indexOf, 'F', 'C');
        addEdge(g, indexOf, 'F', 'H');
        addEdge(g, indexOf, 'H', 'I');
        addEdge(g, indexOf, 'C', 'B');
        addEdge(g, indexOf, 'I', 'F');

    /*Performing BreadthFirstTraversal*/
        int origin = indexOf.get('A');
        List<Character> order = g.breadthFirstTraversal(origin);

        System.out.print("Breadth-first traversal starting at A: ");
        for (Character c : order) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    /**
     * Small helper to add an edge by label characters instead of indices.
     *
     * @param g target graph
     * @param indexOf mapping from vertex label to index
     * @param from label of the source vertex
     * @param to label of the target vertex
     */
    private static void addEdge(Graph<Character> g,
        Map<Character,Integer> indexOf,
        char from, char to) {
            g.addEdge(indexOf.get(from), indexOf.get(to));
    }
}
