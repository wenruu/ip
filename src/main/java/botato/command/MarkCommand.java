package botato.command;

import botato.TaskList;
import botato.Ui;
import botato.exception.InvalidTaskNumberException;

/**
 * This class encapsulates the actions to be performed when a mark command is parsed by the parser.
 * When executed, it marks a task as complete based on the user's input string.
 */
public class MarkCommand extends Command {
    private final String cmd;

    public MarkCommand(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        int taskNum = Integer.parseInt(cmd.substring(4).strip());
        if (taskNum <= 0 || taskNum > tasks.size()) {
            throw new InvalidTaskNumberException(tasks.size());
        }
        tasks.setTaskStatus(taskNum, true);
    }
}
