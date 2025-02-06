package botato.command;

import botato.TaskList;
import botato.Ui;
import botato.exception.MissingKeywordException;

public class FindCommand extends Command {
    private final String cmd;

    public FindCommand(String cmd) {
        this.cmd = cmd;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) {
        String keyword = cmd.substring(4).strip();
        if (keyword.isBlank()) {
            throw new MissingKeywordException();
        }
        tasks.find(keyword);
    }
}
