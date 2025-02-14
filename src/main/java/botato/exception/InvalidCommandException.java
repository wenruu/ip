package botato.exception;

/**
 * This exception is thrown when a command is not valid or not formatted correctly.
 */
public class InvalidCommandException extends BotatoException {
    /**
     * Adds a custom error message to print out.
     */
    public InvalidCommandException() {
        super("Make sure your command is valid and formatted correctly! Type 'help' to learn more.");
    }
}
