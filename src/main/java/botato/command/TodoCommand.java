package botato.command;

import botato.TaskList;
import botato.Ui;

public class TodoCommand extends Command {
    private String cmd;
    public TodoCommand(String cmd) {
        this.cmd = cmd;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) {
        // TODO: handle exceptions eg missing description
    }
}
