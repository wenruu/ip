package botato.command;

import botato.Storage;
import botato.TaskList;
import botato.Ui;

public class DeadlineCommand extends Command {
    private String cmd;
    public DeadlineCommand(String cmd) {
        this.cmd = cmd;
    }
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        // TODO: check for exceptions and throw them
    }
}
