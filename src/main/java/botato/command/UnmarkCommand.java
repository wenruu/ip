package botato.command;

import botato.TaskList;
import botato.Ui;
import botato.exception.InvalidTaskNumberException;

/**
 * This class encapsulates the actions to be performed when an unmark command is parsed from the user input string.
 * When executed, it marks a command as incomplete based on the index from the user input string.
 */
public class UnmarkCommand extends Command {
    private final String cmd;
    public UnmarkCommand(String cmd) {
        this.cmd = cmd;
    }
    @Override
    public String execute(TaskList tasks, Ui ui) {
        int taskNum = Integer.parseInt(cmd.substring(6).strip());
        if (taskNum <= 0 || taskNum > tasks.size()) {
            throw new InvalidTaskNumberException(tasks.size());
        }
        return tasks.setTaskStatus(taskNum, false);
    }
}
