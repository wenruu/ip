package botato;

import botato.task.Task;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    public static ArrayList<Task> load() {
        try (FileInputStream fileIn = new FileInputStream("./data/data.txt");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            Object obj = in.readObject();
            @SuppressWarnings("unchecked")
            ArrayList<Task> data = (ArrayList<Task>) obj;
            System.out.println("Data has been loaded!");
            return data;
        } catch (Exception e) {
            System.out.println("Save file not found or encountered an unexpected error!");
            return new ArrayList<>();
        }
    }

    public static void save(ArrayList<Task> taskArrayList) {
        String directoryPath = "./data/";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (FileOutputStream fileOut = new FileOutputStream("./data/data.txt");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(taskArrayList);
            System.out.println("Data has been saved!");
        } catch (Exception e) {
            System.out.println("An error was encountered and your data was unable to be saved...");
        }
    }
}
