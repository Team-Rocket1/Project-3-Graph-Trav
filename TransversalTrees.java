import java.util.*;

public class TransversalTrees {
    public static void main(String[] args) {
        Graph<String> g = new Graph<>();
        int start = 0; // Vertex at "A"

        // Adding vertices from initial graph
        String[] vertices = {"A", "B", "C", "D","E","F","G","H","I"};
        for (int i = 0; i < vertices.length; i++) {
            g.setVertex(i, vertices[i]);
        } 

        //Add edges from initial graph
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

        // Get BFS and DFS parent arrays from Task 1 and Task 2
        int[] bfsParents = g.bfs(start);
        int[] dfsParents = g.dfs(start);
 
        //-------------------------
        // BFS Tree //
        //-------------------------
        // Creating tree graph structure using BFS parent array
        Map<Integer, List<Integer>> bfsTree = treeFromParents(bfsParents);
        System.out.println("BFS Tree (parent -> children): " );
        printTree(bfsTree, g);
        System.out.println();

        //Traverse BFS Tree so that it matches BFS order
        List<Integer> bfsTreeOrder = breadthFirstTraversalOnTree(bfsTree, start);
        System.out.println("BFS Traversal on Tree (labels): " );
        printOrder(bfsTreeOrder, g);
        System.out.println();
        System.out.println("--------------------------------------------------");

        //-------------------------
        // DFS Tree //
        //-------------------------
        //Creating DFS tree structure using DFS parent array
        Map<Integer, List<Integer>> dfsTree = treeFromParents(dfsParents);
        System.out.println("DFS Tree (parent -> children): " );
        printTree(dfsTree, g);
        System.out.println();

        //Traverse DFS Tree so that it matches DFS order
        List<Integer> dfsTreeOrder = depthFirstTraversalOnTree(dfsTree, start);
        System.out.println("DFS Traversal on Tree (labels): " );
        printOrder(dfsTreeOrder, g);
        System.out.println();
        System.out.println("--------------------------------------------------");

        // Display final results
        displayGraphStructure(g);
        displayTraversalResults(g, start, vertices);
    }
    
    // Displays the graph structure
    private static void displayGraphStructure(Graph<String> graph) {
        System.out.println("Graph:");
        graph.printGraph();
    }
    
    // Displays traversal trees starting from a specified vertex with header
    private static void displayTraversalResults(Graph<String> graph, int startVertex, String[] vertices) {
        System.out.println("\nTraversal Trees starting from vertex " + vertices[startVertex] + ":");
        graph.printTransversalTrees(startVertex);
    }

    // Constructs a tree representation (parent -> children) from parent array 
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

    // Prints tree structure showing parent-child relationships
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

    // Prints the order of vertices in the traversal list
    private static void printOrder(List<Integer> order, Graph<String> g) {
        for (int vertex : order) {
            System.out.print(g.getVertex(vertex) + " ");
        }
        System.out.println();
    }

    // Performs BFS traversal on tree using queue based approach starting from 'start' vertex
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

    // Performs DFS transversal on tree starting from 'start' vertex
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

    // Helper method for DFS traversal: visits a vertex and its children recursively 
    private static void dfsHelper(Map<Integer, List<Integer>> tree, int vertex, List<Integer> result) {
        result.add(vertex);
        if (tree.containsKey(vertex)) {
            for (int child : tree.get(vertex)) {
                dfsHelper(tree, child, result);
            }
        }
    }
}