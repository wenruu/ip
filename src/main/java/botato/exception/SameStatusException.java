package botato.exception;

/**
 * This exception is thrown when trying to set the status of a class to the same one as it already has.
 */
public class SameStatusException extends BotatoException {
    public SameStatusException(boolean status) {
        super(status ? "This task has already been marked as completed!" : "This task has not been completed yet!");
    }
}
