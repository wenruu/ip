import java.util.Scanner;

public class Botato {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String line = "-------------------------------------------------";
        String helloMessage = line + "\nHello from Botato!\nWhat can I do for you?\n" + line;
        String goodbyeMessage = line + "\nHope I helped! See you again!\n" + line;
        System.out.println(helloMessage);
        while (true) {
            String cmd = reader.nextLine();
            if (cmd.equals("bye")) {
                System.out.println(goodbyeMessage);
                break;
            }
            System.out.println(line + "\n" + cmd + "\n" + line);
        }
    }
}
