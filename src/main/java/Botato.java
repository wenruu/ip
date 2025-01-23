import java.util.*;

public class Botato {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String line = "-------------------------------------------------";
        String helloMessage = line + "\nHello from Botato!\nWhat can I do for you?\n" + line;
        String goodbyeMessage = line + "\nHope I helped! See you again!\n" + line;
        ArrayList<String> tasks = new ArrayList<String>();
        System.out.println(helloMessage);
        int terminate = 0;
        while (terminate != 1) {
            String cmd = reader.nextLine();
            switch (cmd) {
                case "bye":
                    System.out.println(goodbyeMessage);
                    terminate = 1;
                    break;
                case "list":
                    System.out.println(line + "\nHere are your current tasks: ");
                    tasks.forEach(System.out::println);
                    System.out.println(line);
                    break;
                default:
                    tasks.add(cmd);
                    System.out.println(line + "\nadded: " + cmd + "\n" + line);
            }
        }
    }
}
