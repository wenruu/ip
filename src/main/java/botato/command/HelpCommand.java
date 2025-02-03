package botato.command;

import botato.TaskList;
import botato.Ui;

public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        System.out.println("""
                Here are the commands you can use:
                'bye', 'list', 'mark ', 'unmark ', 'todo ', 'deadline ', 'event ', 'delete ', 'help'""");
    }
}
