package botato;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BotatoTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testBotatoWelcomeMessage() {
        new Botato();
        String expectedOutput = "Hello! I'm Botato.\nWhat can I do for you?\n";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    public void testBotatoExitCommand() {
        Botato botato = new Botato();
        // Simulate user input for the "bye" command
        ByteArrayInputStream in = new ByteArrayInputStream("bye".getBytes());
        System.setIn(in);

        botato.run();

        String expectedOutput = "Bye. Hope to see you again soon!\n";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    public void testBotatoAddTodoCommand() {
        Botato botato = new Botato();
        // Simulate user input for adding a todo task
        ByteArrayInputStream in = new ByteArrayInputStream("todo Read book".getBytes());
        System.setIn(in);

        botato.run();

        String expectedOutput = "Got it. I've added this task:\n[T][ ] Read book\n";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    public void testBotatoInvalidCommand() {
        Botato botato = new Botato();
        // Simulate user input for an invalid command
        ByteArrayInputStream in = new ByteArrayInputStream("invalid command".getBytes());
        System.setIn(in);

        botato.run();

        String expectedOutput = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(System.in);
    }
}
