package botato.exception;

public class InvalidTaskNumberException extends BotatoException {
    public InvalidTaskNumberException(int size) {
        super(size == 0 ? "You have no tasks!" : "Invalid task number! Choose an integer between 1 and " + size + ".");
    }
}
