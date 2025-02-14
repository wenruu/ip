package botato;


import botato.command.Command;
import botato.command.DeadlineCommand;
import botato.command.DeleteCommand;
import botato.command.EventCommand;
import botato.command.ExitCommand;
import botato.command.FindCommand;
import botato.command.HelpCommand;
import botato.command.ListCommand;
import botato.command.MarkCommand;
import botato.command.TodoCommand;
import botato.command.UnmarkCommand;
import botato.exception.InvalidCommandException;

/**
 * The parser class takes a user input string and returns a {@link Command} object.
 */
public class Parser {
    /**
     * Parses user input string and returns relevant command.
     * If input string is not a valid command, throws {@link InvalidCommandException}.
     *
     * @param cmd is user input read by {@link Ui#readCommand()}.
     * @return {@link Command} object based on input.
     */
    public static Command parse(String cmd) {
        if (cmd.equals("bye")) {
            // Save data and exit chatbot
            return new ExitCommand();
        } else if (cmd.equals("list")) {
            // Display list of tasks
            return new ListCommand();
        } else if (cmd.matches("^mark \\d+$")) {
            // Mark specific task as completed
            return new MarkCommand(cmd);
        } else if (cmd.matches("^unmark \\d+$")) {
            // Mark specified task as not done
            return new UnmarkCommand(cmd);
        } else if (cmd.matches("^todo\\s+.+$")) {
            // Add a Task of type Todo to tasks
            return new TodoCommand(cmd);
        } else if (cmd.matches("^deadline\\s+.+/by\\s+.+$")) {
            // Add a Task of type Deadline to tasks
            return new DeadlineCommand(cmd);
        } else if (cmd.matches("^event\\s+.+/from\\s+.+/to\\s+.+$")) {
            // Add a Task of type Event to tasks
            return new EventCommand(cmd);
        } else if (cmd.matches("^delete \\d+$")) {
            // Delete a specified task based on task number given
            return new DeleteCommand(cmd);
        } else if (cmd.equals("help")) {
            // Opens help interface
            return new HelpCommand();
        } else if (cmd.matches("^find\\s+.+")) {
            return new FindCommand(cmd);
        } else {
            // Handle invalid commands
            throw new InvalidCommandException();
        }
    }
}
