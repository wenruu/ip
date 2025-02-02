package botato;

import botato.command.*;
import botato.exception.InvalidTaskNumberException;
import botato.exception.NoTaskSelectedException;
import botato.task.Deadline;
import botato.task.Event;
import botato.task.Task;
import botato.task.Todo;

public class Parser {
    public static Command parse(String cmd) {
        if (cmd.equals("bye")) {
            // Save data and exit chatbot
            return new ExitCommand();
        } else if (cmd.equals("list")) {
            // Display list of tasks
            return new ListCommand();
        } else if (cmd.startsWith("mark ")) {
            // Mark specific task as completed
            return new MarkCommand(cmd);
        } else if (cmd.startsWith("unmark ")) {
            // Mark specified task as not done
                return new UnmarkCommand(cmd);
        } else if (cmd.startsWith("todo ")){
            // Add a Task of type Todo to tasks
            return new TodoCommand(cmd);
        } else if (cmd.startsWith("deadline")){
            // Add a Task of type Deadline to tasks
            return new DeadlineCommand(cmd);
        } else if (cmd.startsWith("event ")) {
            // Add a Task of type Event to tasks
            if (cmd.contains("/to") && cmd.contains(("/from"))) {
                Event task = new Event(cmd);
                if (task.noDescription()) {
                    System.out.println(line + "\nPlease add a description to your task!\n" + line);
                    continue;
                } else if (task.from == null) {
                    System.out.println(line + "\nPlease add a starting date in a valid format!\n"
                            + "Type 'dateformats' to get a full list of supported formats.\n" + line);
                    continue;
                } else if (task.to == null) {
                    System.out.println(line + "\nPlease add an ending date in a valid format!\n"
                            + "Type 'dateformats' to get a full list of supported formats.\n" + line);
                    continue;
                }
                tasks.add(task);
                System.out.println(line + "\nAdded:\n" + task + "\n" + line);
            } else {
                System.out.println(line + "\nMake sure your 'event' command contains '/from' and '/to' to set "
                        + "your start and end dates!\n" + line);
            }
        } else if (cmd.startsWith("delete ")) {
            // Delete a specified task based on task number given
            if (cmd.substring(6).strip().isBlank()) {
                // Handle no task number given
                System.out.println(line + "\nPlease choose a task you want to delete! You have " + tasks.size()
                        + " tasks.\n" + line);
                continue;
            }
            String taskStr = cmd.substring(6).strip();
            // Extract desired task number
            if (taskStr.matches("-?\\d+")) {
                // Validate that taskStr is an integer
                int taskNum = Integer.parseInt(taskStr);
                // Convert to int
                if (taskNum > tasks.size()) {
                    System.out.println(line + (tasks.isEmpty() ? "\nYou have no tasks!\n"
                            : "\nYou only have " + tasks.size() + " task(s)!\n") + line);
                    continue;
                } else if (taskNum <= 0) {
                    // Handle invalid task numbers
                    System.out.println(line + "\nTask number cannot be less than 1!\n" + line);
                    continue;
                }
                Task temp = tasks.get(taskNum - 1);
                tasks.remove(taskNum - 1);
                System.out.println((line + "\nTask has been successfully removed:\n" + temp + "\n" + line));
            } else {
                System.out.println(line + "\nPlease choose a value between 1 and " + tasks.size()
                        + " to delete!\n" + line);
            }
        } else if (cmd.equals("dateformats")) {
            System.out.println(line + "\nSupported date formats include:\n" +
                    "yyyy-MM-dd HH:mm:ss\n" +
                    "yyyy-MM-dd HH:mm\n" +
                    "yyyy-MM-dd\n" +
                    "dd/MM/yyyy HH:mm:ss\n" +
                    "dd/MM/yyyy HH:mm\n" +
                    "dd/MM/yyyy\n" +
                    "dd/MM/yy HH:mm:ss\n" +
                    "dd/MM/yy HH:mm\n" +
                    "dd/MM/yy\n" +
                    "dd MMM yyyy HH:mm:ss\n" +
                    "dd MMM yyyy HH:mm\n" +
                    "dd MMM yyyy\n" + line);
        } else {
            // Handle unknown commands
            System.out.println(line + "\nSorry, I don't know what that means... Here are the commands you can use:"
                    + "\n'bye', 'list', 'mark ', 'unmark ', 'todo ', 'deadline ', 'event ', 'delete ', "
                    + "dateformats\n" + line);
        }
        return null;
    }
}
