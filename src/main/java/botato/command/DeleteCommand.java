package botato.command;

import botato.TaskList;
import botato.Ui;
import botato.exception.InvalidTaskNumberException;
import botato.exception.NoTaskSelectedException;
import botato.task.Task;

public class DeleteCommand extends Command {
    private String cmd;
    public DeleteCommand(String cmd) {
        this.cmd = cmd;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) {
        if (cmd.length() == 6 || cmd.substring(6).strip().isBlank()) {
            // Handle no task number given
            throw new NoTaskSelectedException(tasks.size());
        }
        String taskStr = cmd.substring(6).strip(); // Extract desired task number
        if (taskStr.matches("-?\\d+")) {
            // Validate that taskStr is an integer
            int taskNum = Integer.parseInt(taskStr);
            // Convert to int
            if (taskNum <= 0 || taskNum > tasks.size()) {
                throw new InvalidTaskNumberException(tasks.size());
            }
            Task removedTask = tasks.deleteTask(taskNum);
            System.out.println("Task has been successfully removed:\n" + removedTask);
        } else {
            throw new InvalidTaskNumberException(tasks.size());
        }
    }
}
