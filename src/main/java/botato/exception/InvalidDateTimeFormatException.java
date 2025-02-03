package botato.exception;

public class InvalidDateTimeFormatException extends BotatoException {
    public InvalidDateTimeFormatException() {
        super("Please check your date format(s)!\nSupported date formats include:\n" +
                "yyyy-MM-dd HH:mm:ss\n" +
                "yyyy-MM-dd HH:mm\n" +
                "yyyy-MM-dd\n" +
                "dd/MM/yyyy HH:mm:ss\n" +
                "dd/MM/yyyy HH:mm\n" +
                "dd/MM/yyyy\n" +
                "dd/MM/yy HH:mm:ss\n" +
                "dd/MM/yy HH:mm\n" +
                "dd/MM/yy\n" +
                "dd MMM yyyy HH:mm:ss\n" +
                "dd MMM yyyy HH:mm\n" +
                "dd MMM yyyy\n");
    }
}
