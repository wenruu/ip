package botato.command;

import botato.Storage;
import botato.TaskList;
import botato.Ui;
import botato.task.Todo;

public class TodoCommand extends Command {
    private String cmd;
    public TodoCommand(String cmd) {
        this.cmd = cmd;
    }
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        // TODO: handle exceptions eg missing description
    }
}
