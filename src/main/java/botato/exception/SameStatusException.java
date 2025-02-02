package botato.exception;

public class SameStatusException extends BotatoException {
    public SameStatusException(boolean status) {
        super(status ? "This task has already been marked as completed!" : "This task has not been completed yet!");
    }
}
