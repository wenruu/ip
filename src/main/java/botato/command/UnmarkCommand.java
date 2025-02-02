package botato.command;

import botato.Storage;
import botato.TaskList;
import botato.Ui;
import botato.exception.InvalidTaskNumberException;
import botato.exception.NoTaskSelectedException;

public class UnmarkCommand extends Command {
    private String cmd;
    public UnmarkCommand(String cmd) {
        this.cmd = cmd;
    }
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        if (cmd.substring(6).isBlank()) {
            throw new NoTaskSelectedException(tasks.size());
        }
        String taskStr = cmd.substring(6).strip(); // extract desired task index
        if (taskStr.matches("-?\\d+")) {
            // Validate that taskStr is an integer
            int taskNum = Integer.parseInt(taskStr);
            if (taskNum <= 0 || taskNum > tasks.size()) {
                throw new InvalidTaskNumberException(tasks.size());
            }
            tasks.setTaskStatus(taskNum, false);
        } else {
            throw new InvalidTaskNumberException(tasks.size());
        }
    }
}
