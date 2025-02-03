package botato;

import botato.command.*;
import botato.exception.InvalidTaskNumberException;
import botato.exception.NoTaskSelectedException;
import botato.exception.UnknownCommandException;
import botato.task.Deadline;
import botato.task.Event;
import botato.task.Task;
import botato.task.Todo;

public class Parser {
    public static Command parse(String cmd) {
        if (cmd.equals("bye")) {
            // Save data and exit chatbot
            return new ExitCommand();
        } else if (cmd.equals("list")) {
            // Display list of tasks
            return new ListCommand();
        } else if (cmd.startsWith("mark ")) {
            // Mark specific task as completed
            return new MarkCommand(cmd);
        } else if (cmd.startsWith("unmark ")) {
            // Mark specified task as not done
                return new UnmarkCommand(cmd);
        } else if (cmd.startsWith("todo ")){
            // Add a Task of type Todo to tasks
            return new TodoCommand(cmd);
        } else if (cmd.startsWith("deadline")){
            // Add a Task of type Deadline to tasks
            return new DeadlineCommand(cmd);
        } else if (cmd.startsWith("event ")) {
            // Add a Task of type Event to tasks
            return new EventCommand(cmd);
        } else if (cmd.startsWith("delete ")) {
            // Delete a specified task based on task number given
            return new DeleteCommand(cmd);
        } else if (cmd.equals("help")) {
            // Prints out available commands
            return new HelpCommand();
        } else {
            // Handle unknown commands
            throw new UnknownCommandException();
        }
    }
}
