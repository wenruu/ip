package botato.command;

import botato.Storage;
import botato.TaskList;
import botato.Ui;
import botato.exception.InvalidTaskNumberException;
import botato.exception.NoTaskSelectedException;

public class MarkCommand extends Command {
    private String cmd;
    public MarkCommand(String cmd) {
        this.cmd = cmd;
    }
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        // TODO: handle invalid cases i.e. num <=0 or > total tasks, then set corresponding task to done
        if (cmd.substring(4).isBlank()) {
            throw new NoTaskSelectedException(tasks.size());
        }
        String taskStr = cmd.substring(4).strip(); // extract desired task index
        if (taskStr.matches("-?\\d+")) {
            // Validate that taskStr is an integer
            int taskNum = Integer.parseInt(taskStr);
            if (taskNum <= 0 || taskNum > tasks.size()) {
                throw new InvalidTaskNumberException(tasks.size());
            }
            tasks.setTaskStatus(taskNum, true);
        } else {
            throw new InvalidTaskNumberException(tasks.size());
        }
    }
}
