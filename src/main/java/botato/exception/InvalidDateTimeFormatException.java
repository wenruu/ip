package botato.exception;

public class InvalidDateTimeFormatException extends BotatoException {
    public InvalidDateTimeFormatException() {
        super("Please check your date format(s)! Type 'help' to learn more.");
    }
}
