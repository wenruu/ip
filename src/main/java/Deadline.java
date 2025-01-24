public class Deadline extends Task {
    protected String by;
    final String type = "Deadline";
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %s (by: %s)", getStatusIcon(), type, description, by);
    }
}
