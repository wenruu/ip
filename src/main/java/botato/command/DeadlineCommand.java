package botato.command;

import botato.TaskList;
import botato.Ui;
import botato.task.Deadline;
import botato.task.Task;

public class DeadlineCommand extends Command {
    private String cmd;
    public DeadlineCommand(String cmd) {
        this.cmd = cmd;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) {
        Task task = new Deadline(cmd);
        tasks.addTask(task);
        System.out.println("Deadline added: \n" + task);
    }
}
