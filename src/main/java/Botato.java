import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Botato {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String line = "---------------------------------------------------------------------------------------------------------------";
        String helloMessage = "Hello from BOTato!\nWhat can I do for you?\n";
        System.out.println(line);
        ArrayList<Task> tasks = loadData();
        System.out.println(helloMessage + line);
        int terminate = 0;
        while (terminate != 1) {
            // Keeps reading commands until "bye" is typed
            String cmd = reader.nextLine();
            if (cmd.equals("bye")) {
                // Save data and exit chatbot
                System.out.println(line);
                writeData(tasks);
                System.out.println("Hope I helped! See you again!\n" + line);
                terminate = 1;
            } else if (cmd.equals("list")) {
                // Display list of tasks
                System.out.println(line + "\nHere are your current tasks:");
                IntStream.range(0, tasks.size())
                        .forEach(i -> System.out.println((i + 1) + "." + tasks.get(i)));
                System.out.println(line);
            } else if (cmd.startsWith("mark ")) {
                // Mark specific task as completed
                if (cmd.substring(4).isBlank()) {
                    System.out.println(line + "\nPlease choose the task you completed! You have " + tasks.size() +
                            " tasks!\n" + line);
                }
                String taskStr = cmd.substring(4).strip(); // extract desired task index
                if (taskStr.matches("-?\\d+")) {
                    // Validate that taskStr is an integer
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
                            " to mark as done!\n" + line);
                }
            } else if (cmd.startsWith("unmark ")) {
                // Mark specified task as not done
                if (cmd.substring(6).isBlank()) {
                    System.out.println(line + "\nPlease choose the task you completed! You have " + tasks.size() +
                            " task(s)!\n" + line);
                }
                String taskStr = cmd.substring(6).strip(); // extract desired task index
                if (taskStr.matches("-?\\d+")) {
                    // Validate that taskStr is an integer
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
                } else {
                    // Handle invalid cases
                    System.out.println(line + "\nPlease choose a value between 1 and " + tasks.size() +
                            " to mark as not done!\n" + line);
                }
            } else if (cmd.startsWith("todo ")){
                // Add a Task of type Todo to tasks
                Task task = new Todo(cmd);
                if (task.description.isEmpty()) {
                    System.out.println(line + "\nPlease add a description for your task!\n" + line);
                    continue;
                }
                tasks.add(task);
                System.out.println(line + "\nAdded:\n" + task + "\n" + line);
            } else if (cmd.startsWith("deadline")){
                // Add a Task of type Deadline to tasks
                if (cmd.contains("/by")) {
                    Deadline task = new Deadline(cmd);
                    if (task.description.isEmpty()) {
                        System.out.println(line + "\nPlease add a description to your task!\n" + line);
                        continue;
                    } else if (task.by == null) {
                        System.out.println(line + "\nPlease add a deadline in a valid format!\n"
                                + "Type 'dateformats' to get a full list of supported formats.\n" + line);
                        continue;
                    }
                    tasks.add(task);
                    System.out.println(line + "\nAdded:\n" + task + "\n" + line);
                } else {
                    System.out.println(line + "\nMake sure your 'deadline' command contains '/by' to indicate your " +
                            "deadline!\n" + line);
                }
            } else if (cmd.startsWith("event ")) {
                // Add a Task of type Event to tasks
                if (cmd.contains("/to") && cmd.contains(("/from"))) {
                    Event task = new Event(cmd);
                    if (task.description.isEmpty()) {
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
        }
    }

    /**
     * Saves data to './data/data.txt'.
     * If './data/' does not exist, create it and save.
     * @param data 'tasks' ArrayList
     */
    private static void writeData (ArrayList<Task> data)  {
        // Check if data directory exists; if not, create it
        String directoryPath = "./data/";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (FileOutputStream fileOut = new FileOutputStream("./data/data.txt");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(data);
            System.out.println("Data has been saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads data from './data/data.txt'.
     * If file does not exist, return empty ArrayList instead.
     * @return ArrayList containing tasks.
     */
    private static ArrayList<Task> loadData() {
        try (FileInputStream fileIn = new FileInputStream("./data/data.txt");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            Object obj = in.readObject();
            if (obj instanceof ArrayList) {
                // Safe to cast to ArrayList<Task>
                @SuppressWarnings("unchecked")
                ArrayList<Task> data = (ArrayList<Task>) obj;
                System.out.println("Data has been loaded!");
                return data;
            } else {
                System.out.println("Unexpected object type: " + obj.getClass().getName());
                return new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
