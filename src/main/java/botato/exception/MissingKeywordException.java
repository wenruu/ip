package botato.exception;

/**
 * This exception is thrown when the find command's keyword field is empty.
 */
public class MissingKeywordException extends BotatoException {
    public MissingKeywordException() {
        super("Keyword cannot be blank!");
    }
}
