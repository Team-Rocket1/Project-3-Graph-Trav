package ADTPackage;

/**
 * A linked implementation of the StackInterface using a singly linked chain.
 */
public final class LinkedStack<T> implements StackInterface<T> {

    private Node topNode;  // References the first node in the chain (top of stack)

    public LinkedStack() {
        topNode = null;
    }

    /**
     * Adds a new entry to the top of this stack.
     *
     * @param newEntry the element to push on the stack
     */
    @Override
    public void push(T newEntry) {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
    }

    /**
     * Removes and returns this stack's top entry.
     *
     * @return the element previously at the top of the stack
     * @throws EmptyStackException if the stack is empty before the operation
     */
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty");
        }

        T top = topNode.getData();
        topNode = topNode.getNextNode();
        return top;
    }

    /**
     * Retrieves this stack's top entry without removing it.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty");
        }
        return topNode.getData();
    }

    /**
     * Detects whether this stack is empty.
     *
     * @return true if the stack contains no elements
     */
    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    /**
     * Removes all entries from this stack. After this call, the stack is empty.
     */
    @Override
    public void clear() {
        topNode = null;
    }

    /** Node for the linked chain*/
    private class Node {
        private T data;  // Entry in stack
        private Node next; // Link to next node

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
    }
}
