import java.util.*;

/**
 * Demonstrates a depth-first traversal on a directed graph implemented
 * using an adjacency list. Vertices are labeled with generic types.
 */
public class Depth_First_Traversal {

    /**
     * A directed graph using an adjacency-list representation.
     * @param <E> the type stored as the label of each vertex
     */
    public static class Graph<E> {
        private List<List<Integer>> edges;
        private E[] labels;

        /**
         * Constructs a directed graph with a specified number of vertices.
         *
         * @param n number of vertices
         */
        @SuppressWarnings("unchecked")
        public Graph(int n) {
            edges = new ArrayList<>(n);
            labels = (E[]) new Object[n];

            for (int i = 0; i < n; i++) {
                edges.add(new ArrayList<>());
            }
        }

        /**
         * Sets the label associated with the given vertex.
         *
         * @param vertex index of the vertex
         * @param newLabel label to assign to the vertex
         */
        public void setLabel(int vertex, E newLabel) {
            labels[vertex] = newLabel;
        }

        /**
         * Retrieves the label of a vertex.
         *
         * @param vertex index of the vertex
         * @return the label stored at the vertex
         */
        public E getLabel(int vertex) {
            return labels[vertex];
        }

        /**
         * Adds a directed edge from a source vertex to a target vertex.
         *
         * @param source the starting vertex
         * @param target the destination vertex
         */
        public void addEdge(int source, int target) {
            edges.get(source).add(target);
        }

        /**
         * Returns the total number of vertices in the graph.
         *
         * @return number of vertices
         */
        public int getSize() {
            return labels.length;
        }

        /**
         * Returns a list of neighbors (outgoing edges) of a given vertex.
         *
         * @param vertex the vertex whose adjacency list is requested
         * @return list of neighboring vertices
         */
        public List<Integer> getNeighbors(int vertex) {
            return edges.get(vertex);
        }

        /**
         * Performs an iterative depth-first traversal starting from a given vertex.
         *
         * @param origin index of the starting vertex
         * @return a list of visited labels in the order they were processed
         */
        public List<E> depthFirstTraversal(int origin) {
            boolean[] visited = new boolean[getSize()];
            Stack<Integer> stack = new Stack<>();
            List<E> order = new ArrayList<>();

            stack.push(origin);

            while (!stack.isEmpty()) {
                int v = stack.pop();

                if (!visited[v]) {
                    visited[v] = true;
                    order.add(getLabel(v));

                    // Push neighbors in reverse order for natural traversal order.
                    List<Integer> nbrs = getNeighbors(v);
                    for (int i = nbrs.size() - 1; i >= 0; i--) {
                        int n = nbrs.get(i);
                        if (!visited[n]) {
                            stack.push(n);
                        }
                    }
                }
            }

            return order;
        }
    }

    /**
     * Helper method to add an edge using character labels instead of numeric indices.
     *
     * @param depthGraph the graph instance
     * @param indexOf map from character labels to vertex indices
     * @param source source vertex label
     * @param target target vertex label
     */
    public static void addEdge(Graph<Character> depthGraph, Map<Character, Integer> indexOf,
                               char source, char target) {
        depthGraph.addEdge(indexOf.get(source), indexOf.get(target));
    }

    /**
     * Main method that builds a sample directed graph and runs a depth-first traversal.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        Graph<Character> depthGraph = new Graph<>(9);
        char[] labels = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I' };

        // Assign labels to vertices.
        for (int i = 0; i < labels.length; i++) {
            depthGraph.setLabel(i, labels[i]);
        }

        // Create a mapping from character labels to indices.
        Map<Character, Integer> indexOf = new HashMap<>();
        for (int i = 0; i < labels.length; i++) {
            indexOf.put(labels[i], i);
        }

        // Add directed edges.
        addEdge(depthGraph, indexOf, 'A', 'B');
        addEdge(depthGraph, indexOf, 'A', 'D');
        addEdge(depthGraph, indexOf, 'A', 'E');
        addEdge(depthGraph, indexOf, 'B', 'E');
        addEdge(depthGraph, indexOf, 'D', 'G');
        addEdge(depthGraph, indexOf, 'E', 'F');
        addEdge(depthGraph, indexOf, 'E', 'H');
        addEdge(depthGraph, indexOf, 'G', 'H');
        addEdge(depthGraph, indexOf, 'F', 'C');
        addEdge(depthGraph, indexOf, 'F', 'H');
        addEdge(depthGraph, indexOf, 'H', 'I');
        addEdge(depthGraph, indexOf, 'C', 'B');
        addEdge(depthGraph, indexOf, 'I', 'F');

        int origin = indexOf.get('A');
        List<Character> order = depthGraph.depthFirstTraversal(origin);

        System.out.print("Depth-first traversal starting at A: ");
        for (char c : order) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}
