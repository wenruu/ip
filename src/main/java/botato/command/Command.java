package botato.command;

import botato.Storage;
import botato.TaskList;
import botato.Ui;

public abstract class Command {
    protected boolean isExit = false;

    void execute(TaskList tasks, Ui ui, Storage storage) {
        execute(tasks, ui);
    }

    public abstract void execute(TaskList tasks, Ui ui);
    public boolean isExit() {
        return isExit;
    }
}
