package botato.exception;

public class KeywordNotFoundException extends BotatoException{
    public KeywordNotFoundException() {
        super("Sorry, I didn't find any tasks matching your keyword...");
    }
}
