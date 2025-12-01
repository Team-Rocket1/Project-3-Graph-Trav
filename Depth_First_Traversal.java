import java.util.*;

public class Depth_First_Traversal {

    public static class Graph<E> {
        private List<List<Integer>> edges;
        private E[] labels;
    
        @SuppressWarnings("unchecked")
        public Graph(int n) {
            edges = new ArrayList<>(n);
            labels = (E[]) new Object[n];
    
            for (int i = 0; i < n; i++) {
                edges.add(new ArrayList<>());
            }
        }
    
        // set the label of a vertex
        public void setLabel(int vertex, E newLabel) {
            labels[vertex] = newLabel;
        }
    
        // gets the label of a vertex
        public E getLabel(int vertex){
            return labels[vertex];
        }
    
        // adds an edge
        public void addEdge(int source, int target){
            edges.get(source).add(target);
        }
    
        // returns the number of vertices in the graph
        public int getSize() {
            return labels.length;
        }
        
        // get a list of neighbors of a given vertex
        public List<Integer> getNeighbors(int vertex){
            return edges.get(vertex);
        }

        public List<E> depthFirstTraversal(int origin) {
            boolean[] visited = new boolean[getSize()];
            Stack<Integer> stack = new Stack<>();
            List<E> order = new ArrayList,>();
    
            stack.push(origin);
    
            while (!stack.isEmpty()) {
                int v = stack.pop();
    
                if (!visited[v]) {
                    visited[v] = true;
                    order.add(getLabel(v));
    
                    // push neighbors in reverse order for natural traversal
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

    public static void addEdge(Graph<Character> depthGraph, Map<Character,Integer> indexOf, char source, char target) {
        depthGraph.addEdge(indexOf.get(from), indexOf.get(to));
    }

    public static void main(String[] args){
        Graph<Character> depthGraph = new Graph<>(9);
        char[] labels = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I' };
        
        // set labels
        for (int i = 0; i < labels.length; i++) {
            g.setLabel(i, labels[i]);
        }

        // create char to index map
        Map<Character, Integer> indexOf = new HashMap<>();
        for (int i = 0; i < labels.length; i++) {
            indexOf.put(labels[i], i);
        }

        // add edges
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
