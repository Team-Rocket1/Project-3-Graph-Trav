import java.util.*;
/**
 * Task 3 - Generates BFS and DFS traversal trees based on parent arrays
 * returned from Task 1 (BFS) and Task 2 (DFS). 
 *
 * This program:
 * <ul>
 *   <li>Builds the given directed graph (A–I)</li>
 *   <li>Constructs BFS and DFS trees from traversal parent arrays</li>
 *   <li>Traverses the generated trees using queue (BFS) and stack (DFS)</li>
 *   <li>Outputs the tree structure and the visitation order of vertices</li>
 * </ul>
 */


public class TransversalTrees {
    /**
     * Entry method for Task 3 — constructs graph, creates BFS + DFS trees,
     * and prints traversal sequences.
     *
     * @param args no command line arguments used
     */
    public static void main(String[] args) {
        Graph<String> g = new Graph<>();
        int start = 0; // Vertex at "A"

        /**
         * STEP 1 — Load Graph Vertices
         */
        String[] vertices = {"A", "B", "C", "D","E","F","G","H","I"};
        for (int i = 0; i < vertices.length; i++) {
            g.setVertex(i, vertices[i]);
        } 

<<<<<<< HEAD
        /**
         * STEP 2 — Insert Edges from Assignment Specification
         */
=======
        //Add edges from initial graph
>>>>>>> 63adc4171e106f00f2f6bcf026758890c7344748
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

<<<<<<< HEAD
        /**
         * STEP 3 — Retrieve parent relationship arrays from BFS/DFS 
         * (generated in Task 1 + Task 2).
         */
        int[] bfsParents = g.bfs(start);
        int[] dfsParents = g.dfs(start);
 
        // ----------------------  BFS TREE  ---------------------- //

        /**
         * Build BFS Tree from parent array.
         */
=======
        // Get BFS and DFS parent arrays from Task 1 and Task 2
        int[] bfsParents = g.bfs(start);
        int[] dfsParents = g.dfs(start);
 
        //-------------------------
        // BFS Tree //
        //-------------------------
        // Creating tree graph structure using BFS parent array
>>>>>>> 63adc4171e106f00f2f6bcf026758890c7344748
        Map<Integer, List<Integer>> bfsTree = treeFromParents(bfsParents);
        System.out.println("BFS Tree (parent -> children): " );
        printTree(bfsTree, g);
        System.out.println();

<<<<<<< HEAD
        /**
         * Traverse BFS tree using queue so tree traversal
         * matches the real BFS visit order.
         */
=======
        //Traverse BFS Tree so that it matches BFS order
>>>>>>> 63adc4171e106f00f2f6bcf026758890c7344748
        List<Integer> bfsTreeOrder = breadthFirstTraversalOnTree(bfsTree, start);
        System.out.println("BFS Traversal on Tree (labels): " );
        printOrder(bfsTreeOrder, g);
        System.out.println();
        System.out.println("--------------------------------------------------");

<<<<<<< HEAD
       // ----------------------  DFS TREE  ---------------------- //

        /**
         * Build DFS Tree from parent array.
         */
=======
        //-------------------------
        // DFS Tree //
        //-------------------------
        //Creating DFS tree structure using DFS parent array
>>>>>>> 63adc4171e106f00f2f6bcf026758890c7344748
        Map<Integer, List<Integer>> dfsTree = treeFromParents(dfsParents);
        System.out.println("DFS Tree (parent -> children): " );
        printTree(dfsTree, g);
        System.out.println();

<<<<<<< HEAD
        /**
         * Traverse DFS tree using stack (iterative)
         * so traversal matches real DFS behavior.
         */
=======
        //Traverse DFS Tree so that it matches DFS order
>>>>>>> 63adc4171e106f00f2f6bcf026758890c7344748
        List<Integer> dfsTreeOrder = depthFirstTraversalOnTree(dfsTree, start);
        System.out.println("DFS Traversal on Tree (labels): " );
        printOrder(dfsTreeOrder, g);
        System.out.println();
        System.out.println("--------------------------------------------------");

<<<<<<< HEAD
        /** Print structure of original graph and tree outputs */
=======
        // Display final results
>>>>>>> 63adc4171e106f00f2f6bcf026758890c7344748
        displayGraphStructure(g);
        displayTraversalResults(g, start, vertices);
    }

    // =============================================================
    //  SUPPORTING METHODS WITH JAVADOC
    // =============================================================
    
<<<<<<< HEAD
    /**
     * Displays adjacency structure of graph.
     *
     * @param graph full graph containing directed edges
     */
=======
    // Displays the graph structure
>>>>>>> 63adc4171e106f00f2f6bcf026758890c7344748
    private static void displayGraphStructure(Graph<String> graph) {
        System.out.println("Graph:");
        graph.printGraph();
    }
    
<<<<<<< HEAD
    /**
     * Prints traversal trees (generated in Task 3)
     *
     * @param graph original graph provided
     * @param startVertex starting index for traversal
     * @param vertices label array for output printing
     */
=======
    // Displays traversal trees starting from a specified vertex with header
>>>>>>> 63adc4171e106f00f2f6bcf026758890c7344748
    private static void displayTraversalResults(Graph<String> graph, int startVertex, String[] vertices) {
        System.out.println("\nTraversal Trees starting from vertex " + vertices[startVertex] + ":");
        graph.printTransversalTrees(startVertex);
    }

<<<<<<< HEAD
    /**
     * Creates a tree structure from BFS/DFS parent array
     * Each index stores parent of vertex i, where -1 = root.
     *
     * @param parents parent relationship array
     * @return LinkedHashMap mapping parent -> children
     */
=======
    // Constructs a tree representation (parent -> children) from parent array 
>>>>>>> 63adc4171e106f00f2f6bcf026758890c7344748
    private static Map<Integer, List<Integer>> treeFromParents(int[] parents) {
        Map<Integer, List<Integer>> tree = new LinkedHashMap<>();
        int n = parents.length;

        //Ensure all vertices are initialized in the tree
        for (int i = 0; i < n; i++) {
            tree.put(i, new ArrayList<>()); // Initialize each vertex in the tree
        }
        //Fills the tree structure based on parent-child relationships
        for (int i = 0; i < n; i++) {
            if (parents[i] != -1) { // Skip root node
                tree.get(parents[i]).add(i); // Add child to parent's list
            }
        }
        return tree;
    }

<<<<<<< HEAD
    /**
     * Prints parents and children of generated traversal tree.
     *
     * @param tree BFS or DFS tree as adjacency mapping
     * @param g    Graph used to convert index → vertex label
     */
=======
    // Prints tree structure showing parent-child relationships
>>>>>>> 63adc4171e106f00f2f6bcf026758890c7344748
    private static void printTree(Map<Integer, List<Integer>> tree, Graph<String> g) {
        for (Map.Entry<Integer, List<Integer>> entry : tree.entrySet()) {
            int parent = entry.getKey();
            System.out.print(g.getVertex(parent) + " -> ");

            List<Integer> children = entry.getValue();
            if (children.isEmpty()) {
                System.out.println("[]");
            } else {
                for (int child : children) {
                    System.out.print(g.getVertex(child) + " ");
                }
            }
            System.out.println();
        }
    }

<<<<<<< HEAD
    /**
     * Prints vertices in label format (A-I)
     *
     * @param order list of node indices visited
     * @param g     reference graph for label lookup
     */
=======
    // Prints the order of vertices in the traversal list
>>>>>>> 63adc4171e106f00f2f6bcf026758890c7344748
    private static void printOrder(List<Integer> order, Graph<String> g) {
        for (int vertex : order) {
            System.out.print(g.getVertex(vertex) + " ");
        }
        System.out.println();
    }

<<<<<<< HEAD
    /**
     * BFS traversal on tree (NOT original graph)
     * Uses queue to match real BFS exploration.
     *
     * @param tree BFS tree as adjacency mapping
     * @param start starting root node index
     * @return BFS visitation order
     */
=======
    // Performs BFS traversal on tree using queue based approach starting from 'start' vertex
>>>>>>> 63adc4171e106f00f2f6bcf026758890c7344748
    private static List<Integer> breadthFirstTraversalOnTree(Map<Integer, List<Integer>> tree, int start) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[tree.size()];

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);

            List<Integer> children = tree.get(current);
            if (children != null) {
                for (int child : children) {
                    if (!visited[child]) {
                        queue.offer(child);
                        visited[child] = true;
                    }
                }
            }
        }
        return result;
    }

<<<<<<< HEAD
    /**
     * Iterative DFS Traversal using a stack-based approach
     *
     * @param tree DFS tree
     * @param start root node
     * @return ordered DFS visitation list
     */
=======
    // Performs DFS transversal on tree starting from 'start' vertex
>>>>>>> 63adc4171e106f00f2f6bcf026758890c7344748
    private static List<Integer> depthFirstTraversalOnTree(Map<Integer, List<Integer>> tree, int start) {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[tree.size()];
        Stack<Integer> stack = new Stack<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            int current = stack.pop();
            
            if (!visited[current]) {
                result.add(current);
                visited[current] = true;

                List<Integer> children = tree.get(current);
                if (children != null) {
                    // Push children in reverse order to maintain left-to-right traversal
                    for (int i = children.size() - 1; i >= 0; i--) {
                        int child = children.get(i);
                        if (!visited[child]) {
                            stack.push(child);
                        }
                    }
                }
            }
        }
        return result;
    }

<<<<<<< HEAD
    /**
     * Recursive helper for DFS tree traversal
     * @param tree DFS tree structure
     * @param vertex current vertex being visited
     * @param result accumulating list of visited vertices
     */
=======
    // Helper method for DFS traversal: visits a vertex and its children recursively 
>>>>>>> 63adc4171e106f00f2f6bcf026758890c7344748
    private static void dfsHelper(Map<Integer, List<Integer>> tree, int vertex, List<Integer> result) {
        result.add(vertex);
        if (tree.containsKey(vertex)) {
            for (int child : tree.get(vertex)) {
                dfsHelper(tree, child, result);
            }
        }
    }
}