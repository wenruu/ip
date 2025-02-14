package botato.command;

import botato.TaskList;
import botato.Ui;
import botato.exception.MissingKeywordException;

/**
 * This class encapsulates the actions to be performed when a find command is parsed by the parser.
 * When executed, it finds a specified keyword (or string) in the user's string input by searching within the tasks
 * in the given task list. Then, the found tasks (if any) are printed.
 */
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
