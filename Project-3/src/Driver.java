import GraphPackage.*;   // DirectedGraph, GraphInterface
import ADTPackage.*;     // QueueInterface

/**
 * Application driver for building and demonstrating the sample graph.
 *
 * <p>This simple program constructs a sample directed graph, then
 * performs a breadth-first traversal beginning at vertex 'A'. It prints
 * the resulting traversal order to standard output.</p>
 *
 * @author Project-3
 */
public class Driver {

    /**
     * Builds a sample directed graph that matches the assignment example.
     *
     * @return a graph instance with vertices A..I and a fixed set of edges
     */
    private static DirectedGraph<Character> buildAssignmentGraph() {
        DirectedGraph<Character> graph = new DirectedGraph<>();

        // Vertices V = {A, B, C, D, E, F, G, H, I}
        char[] labels = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I' };
        for (char c : labels) {
            graph.addVertex(c);
        }

        graph.addEdge('A', 'B');
        graph.addEdge('A', 'D');
        graph.addEdge('A', 'E');
        graph.addEdge('B', 'E');
        graph.addEdge('D', 'G');
        graph.addEdge('E', 'F');
        graph.addEdge('E', 'H');
        graph.addEdge('G', 'H');
        graph.addEdge('F', 'C');
        graph.addEdge('F', 'H');
        graph.addEdge('H', 'I');
        graph.addEdge('C', 'B');
        graph.addEdge('I', 'F');

        return graph;
    }

    /**
     * Entry point for the demo. Builds the sample graph, performs a
     * breadth-first traversal starting from 'A', and prints the order.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {

        DirectedGraph<Character> graph = buildAssignmentGraph();

        /*Task 1: iterative breadth-first traversal starting at A*/
        QueueInterface<Character> bfsOrder =
                graph.getBreadthFirstTraversal('A');

        System.out.print("Breadth-first traversal starting at A: ");
        printQueue(bfsOrder);  // expected order: A B D E G F H C I

        /*Task 2: iterative depth-first traversal starting at A*/
        QueueInterface<Character> dfsOrder =
                graph.getDepthFirstTraversal('A');
        System.out.print("Depth-first traversal starting at A: ");
        printQueue(dfsOrder);  // expected order: A E H I F C B
         
        System.out.print("\n"); // blank line between tasks

        /*Task 3:  BFS Tree*/
        DirectedGraph<Character> bfsTree =
            graph.getBreadthFirstTree('A');
        QueueInterface<Character> bfsTreeOrder =
            bfsTree.getBreadthFirstTraversal('A');
        System.out.print("Breadth-first TREE traversal starting at A: ");
        printQueue(bfsTreeOrder);

        /*Task 4: DFS Tree */
        DirectedGraph<Character> dfsTree =
            graph.getDepthFirstTree('A');
        QueueInterface<Character> dfsTreeOrder =
            dfsTree.getDepthFirstTraversal('A');
        System.out.print("Depth-first TREE traversal starting at A: ");
        printQueue(dfsTreeOrder);

    }

    /**
     * Utility helper that consumes a queue and prints its entries in order
     * on a single line separated by spaces.
     *
     * @param q an enqueued collection of items to print in FIFO order
     * @param <T> the element type of the queue
     */
    private static <T> void printQueue(QueueInterface<T> q) {
        while (!q.isEmpty()) {
            System.out.print(q.dequeue() + " ");
        }
        System.out.println();
    }
}
