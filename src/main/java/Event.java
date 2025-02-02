import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task, which is a specific type of Task.
 * An Event task has a description, a start time (stored in the {@code from} field),
 * and an end time (stored in the {@code to} field).
 * This class extends the {@link Task} class and overrides the {@code toString()}
 * method to provide a custom string representation of the task, including the start and end times.
 * <p>
 * The description, start time, and end time are extracted from the input command string.
 * The description is the part of the command before the "/from" keyword, the start time
 * is the part between the "/from" and "/to" keywords, and the end time is the part after
 * the "/to" keyword.
 * <p>
 * Example usage:
 * <pre>
 * Event event = new Event("event Team meeting /from 2023-10-31 2pm /to 2023-10-31 4pm");
 * System.out.println(event); // Outputs: "[ ] Event: Team meeting (from 2023-10-31 2pm to 2023-10-31 4pm)"
 * </pre>
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String cmd) {
        String fromStr = cmd.substring(cmd.indexOf("/from") + 6, cmd.indexOf("/to")).strip();
        String toStr = cmd.substring(cmd.indexOf("/to") + 3).strip();
        this.from = parseDate(fromStr, LocalTime.of(0, 0));
        this.to = parseDate(toStr, LocalTime.of(23, 59));
        description = cmd.substring(6, cmd.indexOf('/')).strip();
    }

    /**
     * Overrides toString() method to print custom String
     * @return String with status, Event Task type, description and start and end date
     */
    @Override
    public String toString() {
        return String.format("[%s] Event: %s (from %s to %s)", getStatusIcon(), description,
                DateTimeFormatter.ofPattern("dd MMM yyy HH:mm").format(from),
                DateTimeFormatter.ofPattern("dd MMM yyy HH:mm").format(to));
    }
}
