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
                if (cmd.length() == 6 && Character.isDigit(cmd.charAt(5))) {
                    int taskNum = Character.getNumericValue(cmd.charAt(5));
                    if (taskNum > tasks.size()) {
                        System.out.println(line + (tasks.isEmpty() ? "\nYou have no tasks!\n" :
                                "\nYou only have " + tasks.size() + " task(s)!\n") + line);
                        continue;
                    } else if (taskNum <= 0){
                        System.out.println(line + "\nInvalid Task Number!\n" + line);
                        continue;
                    }
                    tasks.get(taskNum - 1).isDone = true;
                    System.out.println(line + "\nGood job! I've marked this task as done:\n"
                            + tasks.get(taskNum - 1) + "\n" + line);
                }
            } else if (cmd.startsWith("unmark ")) {
                if (cmd.length() == 8 && Character.isDigit(cmd.charAt(7))) {
                    int taskNum = Character.getNumericValue(cmd.charAt(7));
                    if (taskNum > tasks.size()) {
                        System.out.println(line + (tasks.isEmpty() ? "\nYou have no tasks!\n" :
                                "\nYou only have " + tasks.size() + " task(s)!\n") + line);
                        continue;
                    } else if (taskNum <= 0){
                        System.out.println(line + "\nInvalid Task Number!\n" + line);
                        continue;
                    }
                    tasks.get(taskNum - 1).isDone = false;
                    System.out.println(line + "\nAww... guess you didn't do this task after all:\n"
                            + tasks.get(taskNum - 1) + "\n" + line);
                }
            } else if (cmd.startsWith("todo ")){
                Task task = new Task(cmd.substring(5));
                if (task.description.isEmpty()) {
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
            } else {
                System.out.println(line + "\nSorry, I don't know what that means... Here are the commands you can " +
                        "use:\n'bye', 'list', 'mark ', 'unmark ', 'todo ', 'deadline ', 'event '\n" + line);
            }
        }
    }
}
