package botato.command;

import botato.TaskList;
import botato.Ui;
import botato.exception.MissingParamException;
import botato.task.Event;

public class EventCommand extends Command {
    private final String cmd;
    public EventCommand(String cmd) {
        this.cmd = cmd;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) {
        Event task = new Event(cmd);
        tasks.addTask(task);
        System.out.println("Event added:\n" + task);
    }
}
