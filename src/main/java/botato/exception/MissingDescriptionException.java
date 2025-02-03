package botato.exception;

public class MissingDescriptionException extends BotatoException {
    public MissingDescriptionException() {
        super("Your task needs to have a description! How would you know what to do otherwise~");
    }
}
