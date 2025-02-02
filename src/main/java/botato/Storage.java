package botato;

import botato.task.Task;

import java.util.ArrayList;

public class Storage {
    public static ArrayList<Task> load() {
        // TODO: load data if available, else returns empty AL
        return new ArrayList<>();
    }

    public static void save(ArrayList<Task> taskArrayList) {
        // TODO: save data to ./data/data.txt
    }
}
