
public class Graph<E> implements GraphInterface<E> {
    private boolean[][] edges; // edges[i][j] = true if there is a vertex i to j
    private E[] labels;     // labels[i] = label for vertex i

    public Graph(int n) {
        edges = new boolean[n][n];  //all values false by default
        labels = (E[]) new Object[n]; //all values null by default
    }

    //gets the label of a vertex
    public E getLabel(int vertex){
        return labels[vertex];
    }

    // tests if an edge exists
    public boolean isEdge(int source, int target){
        return edges[source][target];
    }

    // adds an edge
    public void addEdge(int source, int target){
        edges[source][target] = true;
    }

    // get a list of neighbors of a given vertex
    public int[] getNeighbors(int vertex){
        int i;
        int count = 0;
        int[] answer;

        for (i=0; i < labels.length; i++) {
            if (edges[vertex][i]){
                count++;
            }
        }

        answer = new int[count];
        count = 0;
        for (i=0; i < labels.length; i++) {
            if (edges[vertex][i])
                answer[count++] = i;
        }
        
        return answer;
    }

    // remove an edge
    public void removeEdge(int source, int target) {
        edges[source][target] = false;
    }

    // change the label of a vertex
    public void setLabel(int vertex, E newLabel) {
        labels[vertex] = newLabel;
    }

    // returns the number of vertices in the graph
    public int getSize() {
        return labels.length;
    }

    public static void main(String[] args){
            Graph<String> depthGraph = new Graph<>(5);
            depthGraph.setLabel(0, "A");
            depthGraph.setLabel(1, "B");
            depthGraph.setLabel(2, "C");

            System.out.println(depthGraph.getLabel(0)); // prints label at vertex 0: A
            System.out.println(depthGraph.getSize()); // prints size of graph: 5
            depthGraph.addEdge(0,1);
            System.out.println(depthGraph.getLabel(2));
            System.out.println(depthGraph.getLabel(3));
        }

}
