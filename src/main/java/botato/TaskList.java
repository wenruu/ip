package botato;

import botato.exception.SameStatusException;
import botato.task.Task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskArrayList;
    public TaskList() {
        // load data from file into ArrayList
        taskArrayList = Storage.load();
    }
    public int size() {
        return taskArrayList.size();
    }

    public void addTask(Task task) {
        taskArrayList.add(task);
    }
    public Task deleteTask(int taskNum) {
        return taskArrayList.remove(taskNum - 1);
    }

    public void setTaskStatus(int taskNum, boolean status) {
        Task task = taskArrayList.get(taskNum - 1);
        task.complete(status);
    }

    public void save() {
        Storage.save(taskArrayList);
    }
}
