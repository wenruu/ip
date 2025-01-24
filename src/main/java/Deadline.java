public class Deadline extends Task {
    protected String by;
    final String msg;
    public Deadline(String cmd) {
        super(cmd);
        this.by = cmd.substring(cmd.indexOf("/by") + 3).strip();
        msg = cmd.substring(9, cmd.indexOf('/')).strip();
    }

    @Override
    public String toString() {
        return String.format("[%s] Deadline: %s (by: %s)", getStatusIcon(), msg, by);
    }
}
