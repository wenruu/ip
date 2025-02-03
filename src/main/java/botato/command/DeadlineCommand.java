package botato.command;

import botato.TaskList;
import botato.Ui;

public class DeadlineCommand extends Command {
    private String cmd;
    public DeadlineCommand(String cmd) {
        this.cmd = cmd;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) {
        // TODO: check for exceptions and throw them
    }
}
