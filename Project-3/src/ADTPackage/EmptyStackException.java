package ADTPackage;

/** Runtime exception thrown when stack operations are applied to an empty stack. */
public class EmptyStackException extends RuntimeException {
    public EmptyStackException() {
        super();
    }

    public EmptyStackException(String message) {
        super(message);
    }
}
