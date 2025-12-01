
public class Depth_First_Traversal {
    private List<list<Integer>> edges;
    private E[] labels;

    public Depth_First_Traversal(int n) {
        edges = new ArrayList<>(n);
        labels = (E[]) new Object[n];
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
        edges[source][target] = true;
    }

    // returns the number of vertices in the graph
    public int getSize() {
        return labels.length;
    }
    
    // get a list of neighbors of a given vertex
    public List<Integer> getNeighbors(int vertex){
        return edges.get(vertex);
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
        
    }
    
}
