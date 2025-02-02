package botato;

public class Ui {
    public void showWelcome() {
        String welcomeMessage = "Welcome to BOTato! How may I help you today?";
        showLine();
        System.out.println(welcomeMessage);
        showLine();
    }

    public void showLine() {
        String line = "------------------------------------------------------------------------------------------------"
                + "---------------";
        System.out.println(line);
    }

    public void showError(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }
}
