package ADTPackage;

/** Runtime exception thrown when queue operations are applied to an empty queue. */
public class EmptyQueueException extends RuntimeException {
    public EmptyQueueException() {
        super();
    }

    public EmptyQueueException(String message) {
        super(message);
    }
}
