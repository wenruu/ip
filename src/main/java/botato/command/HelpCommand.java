package botato.command;

import botato.TaskList;
import botato.Ui;

/**
 * Encapsulates what actions to perform when a help command is parsed by the parser.
 * When executed, shows the user the list of commands and date formats they can use.
 */
public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui) {
        String helpStr = """
        Here are the commands you can use:
        'bye', 'list', 'help'
        'mark [task number]', 'unmark [task number]', 'delete [task number]'
        'todo [description]', 'deadline [description] /by [date]',
        'event [description] /from [date] /to [date]', find [keyword(s)]

        Supported date formats include:
        yyyy-MM-dd HH:mm:ss
        yyyy-MM-dd HH:mm
        yyyy-MM-dd
        dd/MM/yyyy HH:mm:ss
        dd/MM/yyyy HH:mm
        dd/MM/yyyy
        dd/MM/yy HH:mm:ss
        dd/MM/yy HH:mm
        dd/MM/yy
        dd MMM yyyy HH:mm:ss
        dd MMM yyyy HH:mm
        dd MMM yyyy""";
        System.out.println(helpStr);
        return helpStr;
    }
}
