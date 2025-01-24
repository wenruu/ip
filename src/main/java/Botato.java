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
                System.out.println(line + "\nHere are your current tasks: ");
                IntStream.range(0, tasks.size())
                        .forEach(i -> System.out.println((i + 1) + "." + tasks.get(i)));
                System.out.println(line);
            } else if (cmd.startsWith("mark")) {
                if (cmd.length() == 6 && Character.isDigit(cmd.charAt(5))) {
                    int taskNum = Character.getNumericValue(cmd.charAt(5)) - 1;
                    if (taskNum > tasks.size()) {
                        System.out.println(line + "\nYou don't have that many tasks!\n" + line);
                        continue;
                    }
                    tasks.get(taskNum).isDone = true;
                    System.out.println(line + "\nGood job! I've marked this task as done:\n"
                            + tasks.get(taskNum) + "\n" + line);
                }
            } else if (cmd.startsWith("unmark")) {
                if (cmd.length() == 8 && Character.isDigit(cmd.charAt(7))) {
                    int taskNum = Character.getNumericValue(cmd.charAt(7)) - 1;
                    if (taskNum > tasks.size()) {
                        System.out.println(line + "\nYou don't have that many tasks!\n" + line);
                        continue;
                    }
                    tasks.get(taskNum).isDone = false;
                    System.out.println( line + "\nAww... guess you didn't do this task after all:\n"
                            + tasks.get(taskNum) + "\n" + line);
                }
            } else if (cmd.startsWith("todo")){
                tasks.add(new Task(cmd));
                System.out.println(line + "\nadded: " + cmd + "\n" + line);
            } else if (cmd.startsWith("deadline")){
                if (cmd.contains("/by")) {
                    Task task = new Deadline(cmd);
                    tasks.add(task);
                    System.out.println(line + "\nadded: " + task + "\n" + line);
                } else {
                    System.out.println(line + "\nMake sure your 'deadline' command contains '/by' to indicate your " +
                            "deadline!\n" + line);
                }
            } else if (cmd.startsWith("event")) {
                if (cmd.contains("/to") && cmd.contains(("/from"))) {
                    Task task = new Event(cmd);
                    tasks.add(task);
                    System.out.println(line + "\nadded: " + task + "\n" + line);
                } else {
                    System.out.println(line + "\nMake sure your 'deadline' command contains '/from' and '/to' to set " +
                            "your deadline!\n" + line);
                }
            } else {
                System.out.println(line + "\nInvalid command! Current supported commands: 'bye', 'list', 'mark', " +
                        "'unmark', 'todo', 'deadline', 'event'\n" + line);
            }
        }
    }
}
