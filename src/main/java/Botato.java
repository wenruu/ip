import java.util.*;
import java.util.stream.IntStream;

public class Botato {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String line = "-------------------------------------------------";
        String helloMessage = line + "\nHello from Botato!\nWhat can I do for you?\n" + line;
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
                    tasks.get(taskNum).isDone = true;
                    System.out.println(line + "\nGood job! I've marked this task as done:\n"
                            + tasks.get(taskNum) + "\n" + line);
                }
            } else if (cmd.startsWith("unmark")) {
                if (cmd.length() == 8 && Character.isDigit(cmd.charAt(7))) {
                    int taskNum = Character.getNumericValue(cmd.charAt(7)) - 1;
                    tasks.get(taskNum).isDone = false;
                    System.out.println( line + "\nAww... guess you didn't do this task after all:\n"
                            + tasks.get(taskNum) + "\n" + line);
                }
            } else if (cmd.startsWith("todo")){
                tasks.add(new Task(cmd, "Todo"));
                System.out.println(line + "\nadded: " + cmd + "\n" + line);
            } else if (cmd.startsWith("deadline")){
                Task task = new Task(cmd, "Deadline");
                // TODO: add deadline filtering
                tasks.add(task);
                System.out.println(line + "\nadded: " + cmd + "\n" + line);
            }

        }
    }
}
