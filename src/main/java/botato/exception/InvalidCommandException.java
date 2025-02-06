package botato.exception;

public class InvalidCommandException extends BotatoException {
    public InvalidCommandException() {
        super("Make sure your command is valid and formatted correctly! Type 'help' "
                + "to learn more.");
    }
}
