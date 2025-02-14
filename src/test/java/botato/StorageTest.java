package botato;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import botato.task.Task;

public class StorageTest {
    @Test
    void testSaveLoadData() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        Storage.save(tasks);
        assertEquals(tasks, Storage.load());
    }
}
