package botato.exception;

/**
 * This exception is thrown when an invalid date and time format is inputted when a date and time is expected.
 */
public class InvalidDateTimeFormatException extends BotatoException {
    public InvalidDateTimeFormatException() {
        super("Please check your date format(s)! Type 'help' to learn more.");
    }
}
