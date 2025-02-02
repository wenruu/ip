package botato.exception;

public class UnknownCommandException extends BotatoException {
    public UnknownCommandException() {
        super("Sorry, I don't know what that means...");
    }
}
