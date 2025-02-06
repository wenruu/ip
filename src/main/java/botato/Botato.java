package botato;
import botato.command.Command;
import botato.exception.BotatoException;

public class Botato {
    private final TaskList tasks;
    private final Ui ui;
    public Botato() {
        ui = new Ui();
        tasks = new TaskList();
    }
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String cmd = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(cmd);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (BotatoException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Botato().run();
    }
}
