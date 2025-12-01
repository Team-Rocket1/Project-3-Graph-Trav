package GraphPackage;

import java.util.Iterator;

import ADTPackage.*; // DictionaryInterface, SortedLinkedDictionary, QueueInterface, LinkedQueue, StackInterface, LinkedStack

/**
 * A class that implements the ADT directed graph.
 * Uses Carrano & Henry's Dictionary + Vertex-based representation.
 */
public class DirectedGraph<T extends Comparable<? super T>>
        implements GraphInterface<T> {

    private DictionaryInterface<T, VertexInterface<T>> vertices;
    private int edgeCount;

    public DirectedGraph() {
        // Use the textbook's dictionary implementation
        vertices = new SortedLinkedDictionary<>();
        edgeCount = 0;
    }

    /*BasicGraphInterface<T>*/

    @Override
    public boolean addVertex(T vertexLabel) {
        // returns previous value if key existed, null otherwise
        VertexInterface<T> addOutcome =
                vertices.add(vertexLabel, new Vertex<>(vertexLabel));
        return addOutcome == null; // true if new vertex added
    }

    @Override
    public boolean addEdge(T begin, T end, double edgeWeight) {
        boolean result = false;
        VertexInterface<T> beginVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex   = vertices.getValue(end);

        if ((beginVertex != null) && (endVertex != null)) {
            result = beginVertex.connect(endVertex, edgeWeight);
        }
        if (result) {
            edgeCount++;
        }
        return result;
    }

    @Override
    public boolean addEdge(T begin, T end) {
        return addEdge(begin, end, 0.0);
    }

    @Override
    public boolean hasEdge(T begin, T end) {
        boolean found = false;
        VertexInterface<T> beginVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex   = vertices.getValue(end);

        if ((beginVertex != null) && (endVertex != null)) {
            Iterator<VertexInterface<T>> neighbors =
                    beginVertex.getNeighborIterator();
            while (!found && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor)) {
                    found = true;
                }
            }
        }
        return found;
    }

    @Override
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    @Override
    public void clear() {
        vertices.clear();
        edgeCount = 0;
    }

    @Override
    public int getNumberOfVertices() {
        return vertices.getSize();
    }

    @Override
    public int getNumberOfEdges() {
        return edgeCount;
    }

    /* ===================== Utility: reset vertices ===================== */

    // Reset visit flags, costs, and predecessors
    protected void resetVertices() {
        Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
        while (vertexIterator.hasNext()) {
            VertexInterface<T> nextVertex = vertexIterator.next();
            nextVertex.unvisit();
            nextVertex.setCost(0);
            nextVertex.setPredecessor(null);
        }
    }

    /* ===================== GraphAlgorithmsInterface<T> ===================== */

    /**
     * Iterative breadth-first traversal using the ADT queues.
     * Returns a queue of labels in BFS order, starting at origin.
     */
    @Override
    public QueueInterface<T> getBreadthFirstTraversal(T origin) {
        resetVertices();
        QueueInterface<T> traversalOrder = new LinkedQueue<>();
        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();

        VertexInterface<T> originVertex = vertices.getValue(origin);
        if (originVertex == null) {
            return traversalOrder;
        }

        originVertex.visit();
        traversalOrder.enqueue(origin);     // enqueue label
        vertexQueue.enqueue(originVertex);  // enqueue vertex

        while (!vertexQueue.isEmpty()) {
            VertexInterface<T> frontVertex = vertexQueue.dequeue();
            Iterator<VertexInterface<T>> neighbors =
                    frontVertex.getNeighborIterator();

            while (neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited()) {
                    nextNeighbor.visit();
                    traversalOrder.enqueue(nextNeighbor.getLabel());
                    vertexQueue.enqueue(nextNeighbor);
                }
            }
        }

        return traversalOrder;
    }

    /**
     * Iterative depth-first traversal using the ADT stack.
     */
    @Override
public QueueInterface<T> getDepthFirstTraversal(T origin) {
    resetVertices();
    QueueInterface<T> traversalOrder = new LinkedQueue<>();
    StackInterface<VertexInterface<T>> vertexStack = new LinkedStack<>();

    VertexInterface<T> originVertex = vertices.getValue(origin);
    if (originVertex == null) {
        return traversalOrder;
    }

    // Start by pushing origin (not visited yet)
    vertexStack.push(originVertex);

    while (!vertexStack.isEmpty()) {
        VertexInterface<T> topVertex = vertexStack.pop();

        if (!topVertex.isVisited()) {
            // "Visit" when we pop
            topVertex.visit();
            traversalOrder.enqueue(topVertex.getLabel());

            // Push neighbors in REVERSE order so that the first neighbor
            // in the adjacency list is processed first (like recursion).
            StackInterface<VertexInterface<T>> temp = new LinkedStack<>();
            Iterator<VertexInterface<T>> neighbors =
                    topVertex.getNeighborIterator();
            while (neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited()) {
                    temp.push(nextNeighbor);
                }
            }
            while (!temp.isEmpty()) {
                vertexStack.push(temp.pop());
            }
        }
    }

    return traversalOrder;
}

     /**
     * Build the breadth-first search tree starting at the given origin.
     * The tree is represented as a new DirectedGraph that contains all
     * the same vertices as this graph, but only the tree edges discovered
     * during BFS. Traversing the tree in BFS order yields the same order
     * as getBreadthFirstTraversal on the original graph.
     */
    public DirectedGraph<T> getBreadthFirstTree(T origin) {
        resetVertices();
        DirectedGraph<T> tree = new DirectedGraph<>();

        // Copy all vertex labels into the tree
        Iterator<T> keyIterator = vertices.getKeyIterator();
        while (keyIterator.hasNext()) {
            tree.addVertex(keyIterator.next());
        }

        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();
        VertexInterface<T> originVertex = vertices.getValue(origin);
        if (originVertex == null) {
            return tree;
        }

        originVertex.visit();
        vertexQueue.enqueue(originVertex);

        while (!vertexQueue.isEmpty()) {
            VertexInterface<T> frontVertex = vertexQueue.dequeue();
            Iterator<VertexInterface<T>> neighbors =
                    frontVertex.getNeighborIterator();

            while (neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited()) {
                    nextNeighbor.visit();
                    // Tree edge: parent -> child
                    tree.addEdge(frontVertex.getLabel(), nextNeighbor.getLabel());
                    vertexQueue.enqueue(nextNeighbor);
                }
            }
        }

        return tree;
    }

    /**
     * Build the depth-first search tree starting at the given origin.
     * The tree is represented as a new DirectedGraph that contains all
     * the same vertices as this graph, but only the tree edges discovered
     * during DFS. Traversing the tree in DFS order yields the same order
     * as getDepthFirstTraversal on the original graph.
     */
    public DirectedGraph<T> getDepthFirstTree(T origin) {
    resetVertices();
    DirectedGraph<T> tree = new DirectedGraph<>();

    // Copy all vertex labels into the tree
    Iterator<T> keyIterator = vertices.getKeyIterator();
    while (keyIterator.hasNext()) {
        tree.addVertex(keyIterator.next());
    }

    StackInterface<VertexInterface<T>> vertexStack = new LinkedStack<>();
    VertexInterface<T> originVertex = vertices.getValue(origin);
    if (originVertex == null) {
        return tree;
    }

    // Start with origin; no predecessor yet
    originVertex.setPredecessor(null);
    vertexStack.push(originVertex);

    while (!vertexStack.isEmpty()) {
        VertexInterface<T> topVertex = vertexStack.pop();

        if (!topVertex.isVisited()) {
            topVertex.visit();

            // If this vertex has a predecessor, that's the tree edge
            if (topVertex.hasPredecessor()) {
                VertexInterface<T> parent = topVertex.getPredecessor();
                tree.addEdge(parent.getLabel(), topVertex.getLabel());
            }

            // As in DFS above: push neighbors in reverse order,
            // recording their predecessor (first time only).
            StackInterface<VertexInterface<T>> temp = new LinkedStack<>();
            Iterator<VertexInterface<T>> neighbors =
                    topVertex.getNeighborIterator();
            while (neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited()) {
                    if (!nextNeighbor.hasPredecessor()) {
                        nextNeighbor.setPredecessor(topVertex);
                    }
                    temp.push(nextNeighbor);
                }
            }
            while (!temp.isEmpty()) {
                vertexStack.push(temp.pop());
            }
        }
    }

    return tree;
}

    /**
     * Unweighted shortest path (by number of edges) using BFS.
     * (Not strictly needed for Task 1, but required by the interface.)
     */
    @Override
    public int getShortestPath(T begin, T end, StackInterface<T> path) {
        resetVertices();
        boolean done = false;
        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();
        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex    = vertices.getValue(end);

        if (originVertex == null || endVertex == null) {
            return -1;
        }

        originVertex.visit();
        vertexQueue.enqueue(originVertex);

        while (!done && !vertexQueue.isEmpty()) {
            VertexInterface<T> frontVertex = vertexQueue.dequeue();
            Iterator<VertexInterface<T>> neighbors =
                    frontVertex.getNeighborIterator();

            while (!done && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited()) {
                    nextNeighbor.visit();
                    nextNeighbor.setCost(1 + frontVertex.getCost());
                    nextNeighbor.setPredecessor(frontVertex);
                    vertexQueue.enqueue(nextNeighbor);
                }
                if (nextNeighbor.equals(endVertex)) {
                    done = true;
                }
            }
        }

        int pathLength = (int) endVertex.getCost();
        path.push(endVertex.getLabel());

        VertexInterface<T> vertexOnPath = endVertex;
        while (vertexOnPath.hasPredecessor()) {
            vertexOnPath = vertexOnPath.getPredecessor();
            path.push(vertexOnPath.getLabel());
        }

        return pathLength;
    }

    /**
     * Attempts to compute a topological ordering of the graph's vertices.
     * <p>This implementation is not included as needed for the assignment,
     * so it signals this by throwing UnsupportedOperationException.</p>
     *
     * @throws UnsupportedOperationException always (not implemented)
     */
    @Override
    public StackInterface<T> getTopologicalOrder() {
        throw new UnsupportedOperationException("Topological order not implemented for this project.");
    }

    /**
     * Finds the least-cost path between two vertices.
     * <p>Not implemented for this project: this method always throws
     * UnsupportedOperationException.</p>
     *
     * @throws UnsupportedOperationException always (not implemented)
     */
    @Override
    public double getCheapestPath(T begin, T end, StackInterface<T> path) {
        throw new UnsupportedOperationException("Cheapest path not implemented for this project.");
    }
}
