public class Task {
    protected String description;
    protected boolean isDone;
    final String msg;

    public Task(String cmd) {
        this.description = cmd;
        this.isDone = false;
        this.msg = cmd.substring(4).strip();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
            return String.format("[%s] Todo: %s", getStatusIcon(), msg);
    }
}