public class Deadline extends Task {
    protected String by;
    final String type = "Deadline";
    private final String msg;
    public Deadline(String cmd) {
        super(cmd);
        this.by = cmd.substring(cmd.indexOf("/by") + 4);
        msg = cmd.substring(9, cmd.indexOf('/') - 1);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %s (by: %s)", getStatusIcon(), type, msg, by);
    }
}
