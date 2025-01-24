public class Event extends Task {
    protected String from;
    protected String to;
    final String msg;

    public Event(String cmd) {
        super(cmd);
        this.from = cmd.substring(cmd.indexOf("/from") + 6, cmd.indexOf("/to")).strip();
        this.to = cmd.substring(cmd.indexOf("/to") + 3).strip();
        msg = description.substring(6, description.indexOf('/')).strip();
    }

    @Override
    public String toString() {
        return String.format("[%s] Event: %s (from %s to %s)", getStatusIcon(), msg, from, to);
    }
}
