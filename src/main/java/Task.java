public class Task {
    protected String description;
    protected boolean isDone;
    final String type = "Todo";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
            return String.format("[%s] %s: %s", getStatusIcon(), type, description);
    }
}