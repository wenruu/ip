package botato.exception;

public class NoTaskSelectedException extends BotatoException {
    public NoTaskSelectedException(int size) {
        super("You did not select a task! You have " + size + " tasks.");
    }
}
