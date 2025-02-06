package botato.exception;

public class MissingKeywordException extends BotatoException {
    public MissingKeywordException() {
        super("Keyword cannot be blank!");
    }
}
