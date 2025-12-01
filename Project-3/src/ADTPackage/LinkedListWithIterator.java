package ADTPackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A linked implementation of a list that also provides an iterator.
 * Simplified but compatible with ListWithIteratorInterface.
 */
public class LinkedListWithIterator<T> implements ListWithIteratorInterface<T> {

    private Node firstNode;
    private int numberOfEntries;

    public LinkedListWithIterator() {
        firstNode = null;
        numberOfEntries = 0;
    }

    /*ListInterface<T> methods*/

    /**
     * Adds a new entry to the end of this list (appends).
     *
     * @param newEntry The object to be added as a new entry
     */
    @Override
    public void add(T newEntry) {
        add(numberOfEntries + 1, newEntry);
    }

    /**
     * Adds a new entry at a specified position within this list.
     *
     * @param newPosition An integer that specifies the desired position
     *                    of the new entry (1-based)
     * @param newEntry    The object to be added as a new entry
     * @throws IndexOutOfBoundsException if newPosition is out of range
     */
    @Override
    public void add(int newPosition, T newEntry) {
        if (newPosition < 1 || newPosition > numberOfEntries + 1) {
            throw new IndexOutOfBoundsException("Illegal position given to add: " + newPosition);
        }

        Node newNode = new Node(newEntry);

        if (newPosition == 1) {
            newNode.setNextNode(firstNode);
            firstNode = newNode;
        } else {
            Node nodeBefore = getNodeAt(newPosition - 1);
            newNode.setNextNode(nodeBefore.getNextNode());
            nodeBefore.setNextNode(newNode);
        }

        numberOfEntries++;
    }

    /**
     * Removes the entry at a given position from this list.
     *
     * @param givenPosition An integer that indicates the position of the
     *                       entry to be removed (1-based)
     * @return A reference to the removed entry
     * @throws IndexOutOfBoundsException if givenPosition is out of range
     */
    @Override
    public T remove(int givenPosition) {
        if (givenPosition < 1 || givenPosition > numberOfEntries) {
            throw new IndexOutOfBoundsException("Illegal position given to remove: " + givenPosition);
        }

        T result;

        if (givenPosition == 1) {
            result = firstNode.getData();
            firstNode = firstNode.getNextNode();
        } else {
            Node nodeBefore = getNodeAt(givenPosition - 1);
            Node nodeToRemove = nodeBefore.getNextNode();
            result = nodeToRemove.getData();
            nodeBefore.setNextNode(nodeToRemove.getNextNode());
        }

        numberOfEntries--;
        return result;
    }

    /**
     * Removes all entries from this list and resets the internal size to 0.
     */
    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    /**
     * Replaces the entry at a given position in this list.
     *
     * @param givenPosition An integer that indicates the position of the
     *                      entry to be replaced (1-based)
     * @param newEntry      The object that will replace the existing entry
     * @return The original entry that was replaced
     * @throws IndexOutOfBoundsException if givenPosition is out of range
     */
    @Override
    public T replace(int givenPosition, T newEntry) {
        if (givenPosition < 1 || givenPosition > numberOfEntries) {
            throw new IndexOutOfBoundsException("Illegal position given to replace: " + givenPosition);
        }

        Node targetNode = getNodeAt(givenPosition);
        T oldEntry = targetNode.getData();
        targetNode.setData(newEntry);
        return oldEntry;
    }

    /**
     * Retrieves the entry at a given position in this list.
     *
     * @param givenPosition An integer that indicates the position of the
     *                      desired entry (1-based)
     * @return A reference to the indicated entry
     * @throws IndexOutOfBoundsException if givenPosition is out of range
     */
    @Override
    public T getEntry(int givenPosition) {
        if (givenPosition < 1 || givenPosition > numberOfEntries) {
            throw new IndexOutOfBoundsException("Illegal position given to getEntry: " + givenPosition);
        }

        return getNodeAt(givenPosition).getData();
    }

    /**
     * Retrieves all entries that are in this list in the order in which
     * they occur in the list.
     *
     * @return A newly allocated array of all the entries in the list
     */
    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];

        int index = 0;
        Node currentNode = firstNode;
        while (currentNode != null) {
            result[index++] = currentNode.getData();
            currentNode = currentNode.getNextNode();
        }

        return result;
    }

    /**
     * Sees whether this list contains a given entry.
     *
     * @param anEntry The object that is the desired entry
     * @return True if the list contains anEntry, or false if not
     */
    @Override
    public boolean contains(T anEntry) {
        Node currentNode = firstNode;
        while (currentNode != null) {
            T data = currentNode.getData();
            if ((anEntry == null && data == null) ||
                (anEntry != null && anEntry.equals(data))) {
                return true;
            }
            currentNode = currentNode.getNextNode();
        }
        return false;
    }

    /**
     * Gets the current length (number of entries) of this list.
     *
     * @return The integer number of entries currently in the list
     */
    @Override
    public int getLength() {
        return numberOfEntries;
    }

    /**
     * Sees whether this list is empty.
     *
     * @return True if the list is empty, or false otherwise
     */
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    // ==== Iterator support ====

    /**
     * Creates and returns an iterator over the elements of this list.
     *
     * @return an Iterator that traverses the list in order
     */
    @Override
    public Iterator<T> getIterator() {
        return new IteratorForLinkedList();
    }

    /**
     * An alias for getIterator(), for compatibility with Iterable.
     *
     * @return an Iterator that traverses the list in order
     */
    @Override
    public Iterator<T> iterator() {
        return getIterator();
    }

    // ==== Private helpers ====

    private Node getNodeAt(int givenPosition) {
        assert givenPosition >= 1 && givenPosition <= numberOfEntries;

        Node currentNode = firstNode;
        for (int i = 1; i < givenPosition; i++) {
            currentNode = currentNode.getNextNode();
        }
        return currentNode;
    }

    // ==== Inner classes ====

    private class IteratorForLinkedList implements Iterator<T> {

        private Node nextNode;

        private IteratorForLinkedList() {
            nextNode = firstNode;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T result = nextNode.getData();
            nextNode = nextNode.getNextNode();
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        private T data;
        private Node next;

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }

        private T getData() {
            return data;
        }

        private void setData(T newData) {
            data = newData;
        }

        private Node getNextNode() {
            return next;
        }

        private void setNextNode(Node nextNode) {
            next = nextNode;
        }
    }
}
