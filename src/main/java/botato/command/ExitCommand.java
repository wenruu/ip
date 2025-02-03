package botato.command;

import botato.TaskList;
import botato.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        super.isExit = true;
        tasks.save();
    }
}
