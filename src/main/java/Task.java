public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String deadline;
    protected String eventFrom;
    protected String eventTo;

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return switch (type) {
            case "Todo" -> String.format("[%s] %s: %s", getStatusIcon(), type, description);
            case "Deadline" -> String.format("[%s] %s: %s (by: %s)", getStatusIcon(), type, description, deadline);
            case "Event" -> String.format("[%s] %s: %s (from %s to %s)", getStatusIcon(), type, description, eventFrom
                    , eventTo);
            default -> "Task type undefined!";
        };
    }
}