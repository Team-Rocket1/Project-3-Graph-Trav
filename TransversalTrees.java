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

        //Adding edges from initial graph
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

        // Get BFS and DFS results
        int[] bfsResult = g.bfs(start);
        int[] dfsResult = g.dfs(start);
 
        // DFS Tree //
        // Creating tree graph structure using BFS parent array
        Map<Integer, List<Integer>> bfsTree = TreeFromParents(bfsResult);
        System.out.println("BFS Tree: " + bfsTree);
        printTree(bfsTree, g);
        System.out.println();

        //Traverse BFS Tree following BFS order
        List<Integer> bfsTreeOrder = breadthFirstTraversalOnTree(bfsTree, start);
        System.out.println("BFS Traversal on Tree: " + bfsTreeOrder);
        printOrder(bfsTreeOrder, g);
        System.out.println();
        System.out.println("--------------------------------------------------");

        //BFS Tree //
        //Creating tree graph structure using DFS parent array
        Map<Integer, List<Integer>> dfsTree = TreeFromParents(dfsResult);
        System.out.println("DFS Tree: " + dfsTree);
        printTree(dfsTree, g);
        System.out.println();

        //Traverse DFS Tree following DFS order
        List<Integer> dfsTreeOrder = depthFirstTraversalOnTree(dfsTree, start);
        System.out.println("DFS Traversal on Tree: " + dfsTreeOrder);
        printOrder(dfsTreeOrder, g);
        System.out.println();
        System.out.println("--------------------------------------------------");

        displayGraphStructure(g);
        displayTraversalResults(g, start, vertices);
    }
    
    private static void displayGraphStructure(Graph<String> graph) {
        System.out.println("Graph:");
        graph.printGraph();
    }
    
    private static void displayTraversalResults(Graph<String> graph, int startVertex, String[] vertices) {
        System.out.println("\nTraversal Trees starting from vertex " + vertices[startVertex] + ":");
        graph.printTransversalTrees(startVertex);
    }

    private static Map<Integer, List<Integer>> TreeFromParents(int[] parents) {
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] != -1) {
                tree.computeIfAbsent(parents[i], k -> new ArrayList<>()).add(i);
            }
        }
        return tree;
    }

    private static void printTree(Map<Integer, List<Integer>> tree, Graph<String> g) {
        for (Map.Entry<Integer, List<Integer>> entry : tree.entrySet()) {
            System.out.print(g.getVertex(entry.getKey()) + " -> ");
            for (int child : entry.getValue()) {
                System.out.print(g.getVertex(child) + " ");
            }
            System.out.println();
        }
    }

    private static void printOrder(List<Integer> order, Graph<String> g) {
        for (int vertex : order) {
            System.out.print(g.getVertex(vertex) + " ");
        }
        System.out.println();
    }

    private static List<Integer> breadthFirstTraversalOnTree(Map<Integer, List<Integer>> tree, int start) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);
            if (tree.containsKey(current)) {
                queue.addAll(tree.get(current));
            }
        }
        return result;
    }

    private static List<Integer> depthFirstTraversalOnTree(Map<Integer, List<Integer>> tree, int start) {
        List<Integer> result = new ArrayList<>();
        dfsHelper(tree, start, result);
        return result;
    }

    private static void dfsHelper(Map<Integer, List<Integer>> tree, int vertex, List<Integer> result) {
        result.add(vertex);
        if (tree.containsKey(vertex)) {
            for (int child : tree.get(vertex)) {
                dfsHelper(tree, child, result);
            }
        }
    }
}