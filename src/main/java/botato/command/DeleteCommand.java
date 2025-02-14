package botato.command;

import botato.TaskList;
import botato.Ui;
import botato.exception.InvalidTaskNumberException;
import botato.task.Task;

/**
 * This class encapsulates the actions to be performed when {@link botato.Parser} parses a delete command from the
 * user's input command string. When executed, it deletes a task from the given TaskList based on the index in the user
 * input string.
 */
public class DeleteCommand extends Command {
    private final String cmd;
    public DeleteCommand(String cmd) {
        this.cmd = cmd;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) {
        String taskStr = cmd.substring(6).strip(); // Extract desired task number
        int taskNum = Integer.parseInt(taskStr);
        if (taskNum <= 0 || taskNum > tasks.size()) {
            throw new InvalidTaskNumberException(tasks.size());
        }
        Task removedTask = tasks.deleteTask(taskNum);
        System.out.println("Task has been successfully removed:\n" + removedTask);
    }
}
