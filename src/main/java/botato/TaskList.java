package botato;

import botato.task.Task;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Manages a list of tasks, providing functionalities to add, delete, update, and display tasks.
 * Interacts with {@link Storage} to load tasks from and save tasks to a file. The class also allows marking
 * tasks as complete or incomplete and provides a method to display all tasks in a formatted manner.
 */
public class TaskList {
    private static ArrayList<Task> taskArrayList;
    public TaskList() {
        // load data from file into ArrayList 
        taskArrayList = Storage.load();
    }

    /**
     * Return number of tasks.
     *
     * @return number of elements in taskArrayList.
     */
    public int size() {
        return taskArrayList.size();
    }

    /**
     * Adds a specified task to the task list.
     *
     * @param task to add to taskArrayList.
     */
    public void addTask(Task task) {
        taskArrayList.add(task);
    }

    /**
     * Deletes a specified task index from taskArrayList.
     *
     * @param taskNum to delete from task list.
     * @return Task deleted.
     */
    public Task deleteTask(int taskNum) {
        return taskArrayList.remove(taskNum - 1);
    }

    /**
     * Sets a task to completed or incomplete status.
     *
     * @param taskNum to set status.
     * @param status to set task to.
     */
    public void setTaskStatus(int taskNum, boolean status) {
        Task task = taskArrayList.get(taskNum - 1);
        task.complete(status);
        System.out.println((status ? "Good job! You finished this:\n" : "Aww.. Guess you didn't do this yet:\n") + task);
    }

    /**
     * Shows the current task list.
     */
    public void show() {
        System.out.println(taskArrayList.isEmpty() ? "You have no tasks!" : "Here are your current tasks:");
        IntStream.range(0, taskArrayList.size())
                .forEach(i -> System.out.println((i + 1) + "." + taskArrayList.get(i)));
    }

    /**
     * Saves task list.
     */
    public void save() {
        Storage.save(taskArrayList);
    }
}
