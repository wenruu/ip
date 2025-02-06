package botato.command;

import botato.TaskList;
import botato.Ui;
import botato.exception.MissingDescriptionException;
import botato.task.Task;
import botato.task.Todo;

public class TodoCommand extends Command {
    private final String cmd;
    public TodoCommand(String cmd) {
        this.cmd = cmd;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) {
        Task task = new Todo(cmd);
        tasks.addTask(task);
        System.out.println("Todo added:\n" + task);
    }
}
