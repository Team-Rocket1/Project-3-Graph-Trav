package ADTPackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simple sorted linked implementation of DictionaryInterface.
 * Keys must be comparable and are kept in ascending order.
 * This is sufficient for use by DirectedGraph.
 */
public class SortedLinkedDictionary<K extends Comparable<? super K>, V>
        implements DictionaryInterface<K, V> {

    private Node firstNode;     // Head of chain
    private int numberOfEntries;

    public SortedLinkedDictionary() {
        firstNode = null;
        numberOfEntries = 0;
    }

    /**
     * Adds a new entry to this dictionary. If the given search key already
     * exists in the dictionary, replaces the corresponding value.
     *
     * @param key   The search key of the new entry
     * @param value The value associated with the key
     * @return Either null if the new entry was added to the dictionary or the
     *         value that was associated with key if that value was replaced.
     * @throws IllegalArgumentException if key or value is null
     */
    @Override
    public V add(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Cannot add null key or value.");
        }

        V result = null;
        Node currentNode = firstNode;
        Node nodeBefore = null;

        // Find insertion point or existing key
        while (currentNode != null &&
               key.compareTo(currentNode.key) > 0) {
            nodeBefore = currentNode;
            currentNode = currentNode.next;
        }

        if (currentNode != null && key.equals(currentNode.key)) {
            // Key already exists -> replace value
            result = currentNode.value;
            currentNode.value = value;
        } else {
            // Insert new node in order
            Node newNode = new Node(key, value, currentNode);
            if (nodeBefore == null) {
                firstNode = newNode;
            } else {
                nodeBefore.next = newNode;
            }
            numberOfEntries++;
        }

        return result;
    }

    /**
     * Removes the entry with the given key, if present.
     *
     * @param key The key for the entry to remove
     * @return The value that was associated with the key, or null if not found
     */
    @Override
    public V remove(K key) {
        V result = null;

        Node currentNode = firstNode;
        Node nodeBefore = null;

        while (currentNode != null &&
               key.compareTo(currentNode.key) > 0) {
            nodeBefore = currentNode;
            currentNode = currentNode.next;
        }

        if (currentNode != null && key.equals(currentNode.key)) {
            // Remove currentNode
            result = currentNode.value;

            if (nodeBefore == null) {
                firstNode = currentNode.next;
            } else {
                nodeBefore.next = currentNode.next;
            }

            numberOfEntries--;
        }

        return result;
    }

    /**
     * Retrieves the value associated with the given key, if present.
     *
     * @param key The search key to look for
     * @return The associated value, or null if there is no entry for the key
     */
    @Override
    public V getValue(K key) {
        Node currentNode = firstNode;

        while (currentNode != null &&
               key.compareTo(currentNode.key) > 0) {
            currentNode = currentNode.next;
        }

        if (currentNode != null && key.equals(currentNode.key)) {
            return currentNode.value;
        }

        return null;
    }

    /**
     * Determines whether the dictionary contains the given key.
     *
     * @param key The search key to look for
     * @return True if the dictionary contains key, or false otherwise
     */
    @Override
    public boolean contains(K key) {
        return getValue(key) != null;
    }

    /**
     * Creates an iterator that traverses the dictionary's keys in ascending order.
     *
     * @return An iterator over keys
     */
    @Override
    public Iterator<K> getKeyIterator() {
        return new KeyIterator();
    }

    /**
     * Creates an iterator that traverses the dictionary's values corresponding
     * to the keys in ascending order.
     *
     * @return An iterator over values
     */
    @Override
    public Iterator<V> getValueIterator() {
        return new ValueIterator();
    }

    /**
     * Checks whether the dictionary is empty (no key/value pairs).
     *
     * @return True if the dictionary contains no entries
     */
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    /**
     * Gets the number of key/value pairs in the dictionary.
     *
     * @return The number of entries
     */
    @Override
    public int getSize() {
        return numberOfEntries;
    }

    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    // ===== Node class =====
    private class Node {
        private K key;
        private V value;
        private Node next;

        private Node(K key, V value, Node nextNode) {
            this.key = key;
            this.value = value;
            this.next = nextNode;
        }
    }

    // ===== Key iterator =====
    private class KeyIterator implements Iterator<K> {
        private Node currentNode;

        private KeyIterator() {
            currentNode = firstNode;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            K result = currentNode.key;
            currentNode = currentNode.next;
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // ===== Value iterator =====
    private class ValueIterator implements Iterator<V> {
        private Node currentNode;

        private ValueIterator() {
            currentNode = firstNode;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public V next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            V result = currentNode.value;
            currentNode = currentNode.next;
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
