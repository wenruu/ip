package botato;

import botato.exception.KeywordNotFoundException;
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
     * Finds a specified keyword among all the tasks in taskArrayList.
     *
     * @param keyword String to search among current tasks.
     */
    public void find(String keyword) {
        ArrayList<Task> temp = new ArrayList<>();
        taskArrayList.forEach(task -> {
            if (task.toString().contains(keyword)) {
                temp.add(task);
            }
        });
        if (temp.isEmpty()) {
            throw new KeywordNotFoundException();
        }
        System.out.println("Here are your tasks containing the keyword:");
        IntStream.range(0, temp.size())
                .forEach(i -> System.out.println((i + 1) + "." + temp.get(i)));
    }

    /**
     * Interacts with {@link Storage} to save taskArrayList to local memory.
     */
    public void save() {
        Storage.save(taskArrayList);
    }
}
