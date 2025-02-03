package botato.exception;

public class MissingParamException extends BotatoException {
    public MissingParamException(String message) {
        super("Please make sure your command contains '" + message +"'!");
    }
}
