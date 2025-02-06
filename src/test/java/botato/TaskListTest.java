package botato;

import botato.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class TaskListTest {
    @Test
    void testTaskListSize() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        Storage.save(tasks);
        TaskList taskList = new TaskList();

        assertEquals(1, taskList.size());
    }

    @Test
    void testAddTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        Storage.save(tasks);
        TaskList taskList = new TaskList();

        taskList.addTask(new Task());
        assertEquals(2, taskList.size());
    }

    @Test
    void testDeleteTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        Storage.save(tasks);
        TaskList taskList = new TaskList();

        taskList.deleteTask(1);
        assertEquals(0, taskList.size());
    }

    @Test
    void setTaskStatus() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        Storage.save(tasks);
        TaskList taskList = new TaskList();

        // assertEquals(taskList.setTaskStatus(1, false), 0);
    }
}
