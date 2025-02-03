package botato.task;

import botato.exception.MissingDescriptionException;

/**
 * Represents a Todo task, which is a specific type of Task.
 * A Todo task has a description but no additional date or time constraints.
 * This class extends the {@link Task} class and overrides the {@code toString()}
 * method to provide a custom string representation of the task.
 * <p>
 * The description of the Todo task is extracted from the input command string
 * and stored in the {@code description} field inherited from the {@link Task} class.
 * <p>
 * Example usage:
 * <pre>
 * Todo todo = new Todo("todo Buy groceries");
 * System.out.println(todo); // Outputs: "[ ] Todo: Buy groceries"
 * </pre>
 */
public class Todo extends Task {
    public Todo(String cmd) {
        description = cmd.substring(4).strip();
        if (description.isBlank()) {
            throw new MissingDescriptionException();
        }
    }

    /**
     * Overrides toString() method to print custom String
     * @return String containing status, Todo task type and description
     */
    @Override
    public String toString() {
        return String.format("[%s] Todo: %s", getStatusIcon(), description);
    }
}
