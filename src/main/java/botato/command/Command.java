package botato.command;

import botato.Storage;
import botato.TaskList;
import botato.Ui;

public abstract class Command {
    protected boolean isExit = false;

    abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit() {
        return isExit;
    }
}
