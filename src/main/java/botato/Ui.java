package botato;

import java.util.Scanner;

public class Ui {
    private Scanner reader;
    public Ui() {
        reader = new Scanner(System.in);
    }
    public void showWelcome() {
        String welcomeMessage = """
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⠤⠴⠒⠋⠉⠉⠉⠉⠙⠒⠲⠤⢴⣤⣄⠀⠀⠀
⠀⠀⠀⠀⠀⠀⢀⣀⡴⠋⠁⠀⠀⠳⠀⠀⠀⢀⠀⠶⢤⣄⣀⠀⠀⠈⠉⠳⡄⠀
⠀⠀⠀⢀⠴⠊⠁⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠘⠆⠘⡄
⠀⢀⠜⠁⢠⠄⢀⣀⣑⣀⡀⠀⠀⠀⠀⠀⡤⠤⠤⠤⡄⠀⠀⢀⠀⠀⠀⡤⠀⢱
⢀⠎⠀⠀⠈⠀⢸⠠⠤⠄⢹⠀⠀⠀⠀⠀⡇⠤⠤⠄⠇⠀⠀⠀⠃⠀⠀⠀⠀⢸
⡎⠀⠀⠀⠀⠀⠨⠉⠉⠉⠉⠀⠀⡆⠀⠀⠉⠉⠉⠩⠃⠀⠀⠀⠠⠄⢀⣀⣠⠏
⣧⠀⠈⡆⠀⠀⠀⠀⠀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠠⢀⣀⠀⠀⠀⠀⠀⠀⣨⠁⠀
⡟⠄⠀⢸⡄⠀⠀⠀⠀⠈⢯⡉⠉⠈⠉⠉⠉⠉⡝⠈⠛⠃⠀⠀⠀⣠⡾⠃⠀⠀
⢱⡈⠀⠀⠁⠀⠀⠀⠀⠀⠀⠉⠒⠒⠐⠒⠒⠊⠁⠀⢀⣠⠀⣀⡴⠋⠀⠀⠀⠀
⠀⠑⢤⣀⠀⠀⣖⡆⠀⠀⠀⢤⡤⣀⣀⠰⠄⠀⠀⠀⠀⢀⣠⠏⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠈⠙⠒⠢⠤⠤⣄⣀⣠⣤⣬⠶⠶⠦⠤⠶⠖⠚⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
Welcome to BOTato! How may I help you today?""";
        showLine();

        System.out.println(welcomeMessage);
        showLine();
    }

    public String readCommand() {
        return reader.nextLine();
    }
    public void showLine() {
        String line = "------------------------------------------------------------------------------------------------"
                + "---------------";
        System.out.println(line);
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
