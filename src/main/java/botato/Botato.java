package botato;
import botato.command.Command;
import botato.exception.BotatoException;

/**
 * The Botato class represents the main bot application that interacts with the user to manage tasks.
 * It initializes and runs the user interface, processes commands, and manages tasks.
 */
public class Botato {
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a new Botato instance with an empty task list and initializes the user interface.
     */
    public Botato() {
        ui = new Ui();
        tasks = new TaskList();
    }

    /**
     * Runs the bot application by displaying a welcome message, reading user input,
     * parsing commands, and executing the respective actions. The bot continues running until
     * an exit command is received.
     */
    public void run() {
        ui.showWelcome();  // Show welcome message to the user.
        boolean isExit = false;

        // Loop that keeps the bot running until the user requests an exit.
        while (!isExit) {
            try {
                String cmd = ui.readCommand();  // Read the user's command.
                ui.showLine();  // Display a separator line.
                Command c = Parser.parse(cmd);  // Parse the command entered by the user.
                c.execute(tasks, ui);  // Execute the parsed command, affecting the task list and UI.
                isExit = c.isExit();  // Check if the exit condition is met.
            } catch (BotatoException e) {
                ui.showError(e.getMessage());  // Show error message if there's an issue with the command.
            } finally {
                ui.showLine();  // Always show a line separator after command execution.
            }
        }
    }

    /**
     * The main entry point of the Botato application.
     * Initializes and starts the bot's execution.
     *
     * @param args Command line arguments (not used in this case).
     */
    public static void main(String[] args) {
        new Botato().run();  // Create an instance of Botato and run the application.
    }
}
