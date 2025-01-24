public class Event extends Task {
    protected String from;
    protected String to;
    final String type = "Event";
    private final String msg;

    public Event(String cmd) {
        super(cmd);
        this.from = cmd.substring(cmd.indexOf("/from") + 6, cmd.indexOf("/to") - 1);
        this.to = cmd.substring(cmd.indexOf("/to") + 4);
        msg = description.substring(6, description.indexOf('/') - 1);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %s (from %s to %s)", getStatusIcon(), type, msg, from, to);
    }
}
