package botato;
import botato.command.Command;
import botato.exception.BotatoException;
import botato.task.Deadline;
import botato.task.Event;
import botato.task.Task;
import botato.task.Todo;

import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Botato {
    private TaskList tasks;
    private Ui ui;
    public Botato() {
        ui = new Ui();
        tasks = new TaskList();
    }
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            // Keeps reading commands until "bye" is typed
            try {
                String cmd = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(cmd);
                c.execute(tasks, ui);
                isExit = c.isExit();
            } catch (BotatoException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Botato().run();
    }
}
