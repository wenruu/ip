package botato.task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task, which is a specific type of Task.
 * A Deadline task has a description and a deadline (stored in the {@code by} field).
 * This class extends the {@link Task} class and overrides the {@code toString()}
 * method to provide a custom string representation of the task, including the deadline.
 * <p>
 * The description and deadline are extracted from the input command string.
 * The description is the part of the command before the "/by" keyword, and the deadline
 * is the part after the "/by" keyword.
 * <p>
 * Example usage:
 * <pre>
 * Deadline deadline = new Deadline("deadline Submit report /by 2023-10-31");
 * System.out.println(deadline); // Outputs: "[ ] Deadline: Submit report (by: 2023-10-31)"
 * </pre>
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String cmd) {
        String dateStr = cmd.substring(cmd.indexOf("/by") + 3).strip();
        by = parseDate(dateStr, LocalTime.of(23, 59));
        description = cmd.substring(9, cmd.indexOf('/')).strip();
    }

    public boolean noBy() {
        return by == null;
    }
    /**
     * Overrides toString() method to print custom String
     * @return String containing status, Deadline Task type, description and deadline
     */
    @Override
    public String toString() {
        return String.format("[%s] Deadline: %s (by: %s)", getStatusIcon(), description,
                DateTimeFormatter.ofPattern("dd MMM yyy HH:mm").format(by));
    }
}
