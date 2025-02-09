package botato.command;

import botato.Storage;
import botato.TaskList;
import botato.Ui;

public abstract class Command {
    protected boolean isExit = false;

    public abstract void execute(TaskList tasks, Ui ui);

    /**
     * isExit is only true if 'bye' command was typed.
     * @return isExit
     */
    public boolean isExit() {
        return isExit;
    }
}
