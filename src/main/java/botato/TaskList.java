package botato;

import botato.exception.SameStatusException;
import botato.task.Task;

import java.util.ArrayList;
import java.util.stream.IntStream;

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
        System.out.println((status ? "Good job! You finished this:\n" : "Aww.. Guess you didn't do this yet:\n") + task);
    }

    public void show() {
        System.out.println(taskArrayList.isEmpty() ? "You have no tasks!" : "Here are your current tasks:");
        IntStream.range(0, taskArrayList.size())
                .forEach(i -> System.out.println((i + 1) + "." + taskArrayList.get(i)));
    }

    public void save() {
        Storage.save(taskArrayList);
    }
}
