package botato.command;

import botato.TaskList;
import botato.Ui;

/**
 * Encapsulates what actions to perform when a help command is parsed by the parser.
 * When executed, brings the user into the help UI, which can be interacted with to learn more about the program's
 * user commands and date formats accepted.
 */
public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui) {
        System.out.println("""
                    Welcome to the help dialogue!
                    Type 'commands' for a full list of commands.
                    Type 'date formats' for a full list of supported date formats.
                    Type 'exit' to leave this dialogue.""");
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            String cmd = ui.readCommand();
            ui.showLine();
            switch (cmd) {
            case "exit" -> {
                isExit = true;
                System.out.println("Hope this helped!");
            }
            case "commands" -> System.out.println("""
                    Here are the commands you can use:
                    'bye', 'list', 'help'
                    'mark [task number]', 'unmark [task number]', 'delete [task number]'
                    'todo [description]', 'deadline [description] /by [date]',
                    'event [description] /from [date] /to [date]'""");
            case "date formats" -> System.out.println("""
                    Supported date formats include:
                    yyyy-MM-dd HH:mm:ss
                    yyyy-MM-dd HH:mm
                    yyyy-MM-dd
                    dd/MM/yyyy HH:mm:ss
                    dd/MM/yyyy HH:mm
                    dd/MM/yyyy
                    dd/MM/yy HH:mm:ss
                    dd/MM/yy HH:mm
                    dd/MM/yy
                    dd MMM yyyy HH:mm:ss
                    dd MMM yyyy HH:mm
                    dd MMM yyyy""");
            default -> System.out.println("Sorry, I didn't understand that...");
            }
            ui.showLine();
        }
        System.out.println("Welcome back to BOTato! Hope you understand me a bit better now!");
        return "This function is not available right now!";
    }
}
