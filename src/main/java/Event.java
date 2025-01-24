public class Event extends Task {
    protected String from;
    protected String to;
    final String type = "Event";

    public Event(String description, String from, String to) {
        super(description);
        this.to = to;
    }

    @Override
    public String toString() {
        return  String.format("[%s] %s: %s (from %s to %s)", getStatusIcon(), type, description, from, to);
    }
}
