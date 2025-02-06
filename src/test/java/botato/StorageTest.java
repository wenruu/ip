package botato;

import botato.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    @Test
    void testSaveLoadData() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        Storage.save(tasks);
        assertEquals(tasks, Storage.load());
    }
}
