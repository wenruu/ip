import java.util.*;
import java.util.stream.IntStream;

public class BOTato {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String line = "---------------------------------------------------------------------------------------------------------------";
        String helloMessage = line + "\nHello from BOTato!\nWhat can I do for you?\n" + line;
        String goodbyeMessage = line + "\nHope I helped! See you again!\n" + line;
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println(helloMessage);
        int terminate = 0;
        while (terminate != 1) {
            String cmd = reader.nextLine();
            if (cmd.equals("bye")) {
                System.out.println(goodbyeMessage);
                terminate = 1;
            } else if (cmd.equals("list")) {
                System.out.println(line + "\nHere are your current tasks:");
                IntStream.range(0, tasks.size())
                        .forEach(i -> System.out.println((i + 1) + "." + tasks.get(i)));
                System.out.println(line);
            } else if (cmd.startsWith("mark ")) {
                if (cmd.substring(4).isBlank()) {
                    System.out.println(line + "\nPlease choose the task you completed! You have " + tasks.size() +
                            " tasks!\n" + line);
                }
                String taskStr = cmd.substring(4).strip(); // extract desired task index
                if (taskStr.matches("-?\\d+")) { // validate that taskStr is an integer
                    int taskNum = Integer.parseInt(taskStr);
                    if (taskNum > tasks.size()) {
                        System.out.println(line + (tasks.isEmpty() ? "\nYou have no tasks!\n" :
                                "\nYou only have " + tasks.size() + " task(s)!\n") + line);
                        continue;
                    } else if (taskNum <= 0){
                        System.out.println(line + "\nTask number cannot be less than 1!\n" + line);
                        continue;
                    }
                    tasks.get(taskNum - 1).isDone = true;
                    System.out.println(line + "\nGood job! I've marked this task as done:\n"
                            + tasks.get(taskNum - 1) + "\n" + line);
                } else { // if taskStr is not an integer
                    System.out.println(line + "\nPlease choose a value between 1 and " + tasks.size() +
                            "to mark as done!\n" + line);
                }
            } else if (cmd.startsWith("unmark ")) {
                if (cmd.substring(6).isBlank()) {
                    System.out.println(line + "\nPlease choose the task you completed! You have " + tasks.size() +
                            " task(s)!\n" + line);
                }
                String taskStr = cmd.substring(6).strip(); // extract desired task index
                if (taskStr.matches("-?\\d+")) { // validate that taskStr is an integer
                    int taskNum = Integer.parseInt(taskStr);
                    if (taskNum > tasks.size()) {
                        System.out.println(line + (tasks.isEmpty() ? "\nYou have no tasks!\n" :
                                "\nYou only have " + tasks.size() + " task(s)!\n") + line);
                        continue;
                    } else if (taskNum <= 0) {
                        System.out.println(line + "\nTask number cannot be less than 1!\n" + line);
                        continue;
                    }
                    tasks.get(taskNum - 1).isDone = false;
                    System.out.println(line + "\nAww... Guess you didn't do this after all:\n"
                            + tasks.get(taskNum - 1) + "\n" + line);
                } else { // if taskStr is not an integer
                    System.out.println(line + "\nPlease choose a value between 1 and " + tasks.size() +
                            " to mark as not done!\n" + line);
                }
            } else if (cmd.startsWith("todo ")){
                Task task = new Task(cmd);
                if (task.msg.isEmpty()) {
                    System.out.println(line + "\nPlease add a description for your task!\n" + line);
                    continue;
                }
                tasks.add(task);
                System.out.println(line + "\nAdded:\n" + task + "\n" + line);
            } else if (cmd.startsWith("deadline")){
                if (cmd.contains("/by")) {
                    Deadline task = new Deadline(cmd);
                    if (task.msg.isEmpty()) {
                        System.out.println(line + "\nPlease add a description to your task!\n" + line);
                        continue;
                    } else if (task.by.isEmpty()) {
                        System.out.println(line + "\nPlease add a deadline!\n" + line);
                        continue;
                    }
                    tasks.add(task);
                    System.out.println(line + "\nAdded:\n" + task + "\n" + line);
                } else {
                    System.out.println(line + "\nMake sure your 'deadline' command contains '/by' to indicate your " +
                            "deadline!\n" + line);
                }
            } else if (cmd.startsWith("event ")) {
                if (cmd.contains("/to") && cmd.contains(("/from"))) {
                    Event task = new Event(cmd);
                    if (task.msg.isEmpty()) {
                        System.out.println(line + "\nPlease add a description to your task!\n" + line);
                        continue;
                    } else if (task.from.isEmpty()) {
                        System.out.println(line + "\nPlease add a starting date!\n" + line);
                        continue;
                    } else if (task.to.isEmpty()) {
                        System.out.println(line + "\nPlease add an ending date!\n" + line);
                        continue;
                    }
                    tasks.add(task);
                    System.out.println(line + "\nAdded:\n" + task + "\n" + line);
                } else {
                    System.out.println(line + "\nMake sure your 'event' command contains '/from' and '/to' to set " +
                            "your start and end dates!\n" + line);
                }
            } else if (cmd.startsWith("delete ")) {
                if (cmd.substring(6).strip().isBlank()) { // no task number chosen
                    System.out.println(line + "\nPlease choose a task you want to delete! You have " + tasks.size() +
                            " tasks.\n" + line);
                    continue;
                }
                String taskStr = cmd.substring(6).strip(); // extract desired task index
                if (taskStr.matches("-?\\d+")) { // validate that taskStr is an integer
                    int taskNum = Integer.parseInt(taskStr); // convert to int
                    if (taskNum > tasks.size()) {
                        System.out.println(line + (tasks.isEmpty() ? "\nYou have no tasks!\n" :
                                "\nYou only have " + tasks.size() + " task(s)!\n") + line);
                        continue;
                    } else if (taskNum <= 0) {
                        System.out.println(line + "\nTask number cannot be less than 1!\n" + line);
                        continue;
                    }
                    Task temp = tasks.get(taskNum - 1);
                    tasks.remove(taskNum - 1);
                    System.out.println((line + "\nTask has been successfully removed:\n" + temp + "\n" + line));
                } else {
                    System.out.println(line + "\nPlease choose a value between 1 and " + tasks.size() +
                            "to delete!\n" + line);
                }
            } else {
                System.out.println(line + "\nSorry, I don't know what that means... Here are the commands you can " +
                        "use:\n'bye', 'list', 'mark ', 'unmark ', 'todo ', 'deadline ', 'event ', 'delete '\n" + line);
            }
        }
    }
}
