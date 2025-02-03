package botato.exception;

public class InvalidTaskNumberException extends BotatoException {
    public InvalidTaskNumberException(int size) {
        super("Invalid task number!"
                + (size == 0 ? "You have no tasks." : "Choose an integer between 1 and " + size + "."));
    }
}
