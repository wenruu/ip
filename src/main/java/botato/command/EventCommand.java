package botato.command;

import botato.TaskList;
import botato.Ui;
import botato.exception.MissingParamException;
import botato.task.Event;

public class EventCommand extends Command {
    private String cmd;
    public EventCommand(String cmd) {
        this.cmd = cmd;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) {
        //TODO: brush up on this
        if (cmd.contains("/to") && cmd.contains(("/from"))) {
            Event task = new Event(cmd);
            tasks.addTask(task);
            System.out.println("Event added:\n" + task);
        } else {
            throw new MissingParamException("/to' and '/from");
        }
    }
}
