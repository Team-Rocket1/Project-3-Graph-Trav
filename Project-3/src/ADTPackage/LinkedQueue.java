package ADTPackage;

/**
 * A linked implementation of the QueueInterface using a singly linked chain.
 */
public final class LinkedQueue<T> implements QueueInterface<T> {

    private Node firstNode; // front of queue
    private Node lastNode;  // back of queue

    public LinkedQueue() {
        firstNode = null;
        lastNode = null;
    }

    /**
     * Adds a new entry to the back of this queue.
     *
     * @param newEntry the element to add to the queue
     */
    @Override
    public void enqueue(T newEntry) {
        Node newNode = new Node(newEntry, null);

        if (isEmpty()) {
            firstNode = newNode;
        } else {
            lastNode.setNextNode(newNode);
        }

        lastNode = newNode;
    }

    /**
     * Removes and returns the entry at the front of this queue.
     *
     * @return the element removed from the front of the queue
     * @throws EmptyQueueException if the queue is empty before the operation
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }

        T front = firstNode.getData();
        firstNode = firstNode.getNextNode();

        if (firstNode == null) {
            lastNode = null;
        }

        return front;
    }

    /**
     * Retrieves the entry at the front of this queue without removing it.
     *
     * @return the element at the front of the queue
     * @throws EmptyQueueException if the queue is empty
     */
    @Override
    public T getFront() {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
        return firstNode.getData();
    }

    /**
     * Detects whether this queue is empty.
     *
     * @return true if the queue contains no elements
     */
    @Override
    public boolean isEmpty() {
        return firstNode == null;
    }

    /**
     * Removes all entries from this queue. After this call, the queue is empty.
     */
    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
    }

    /** Node for the linked chain*/
    private class Node {
        private T data;
        private Node next;

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }

        private T getData() {
            return data;
        }

        private Node getNextNode() {
        return next;
        }

        private void setNextNode(Node nextNode) {
            next = nextNode;
        }
    }
}
