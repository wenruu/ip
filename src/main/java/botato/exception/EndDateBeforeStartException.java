package botato.exception;

/**
 * Exception thrown when the given end date is before the start date.
 */
public class EndDateBeforeStartException extends BotatoException{
    /**
     * Constructs a new BotatoException with the specified error message.
     */
    public EndDateBeforeStartException() {
        super("Your end date is before the start date!");
    }
}
