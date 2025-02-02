package botato.exception;

public class MissingParamException extends BotatoException {
    public MissingParamException(String message) {
        super("Please add " + message + "to your command!");
    }
}
