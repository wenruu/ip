package botato;

import botato.command.Command;
import botato.command.DeadlineCommand;
import botato.command.DeleteCommand;
import botato.command.EventCommand;
import botato.command.ExitCommand;
import botato.command.HelpCommand;
import botato.command.ListCommand;
import botato.command.MarkCommand;
import botato.command.TodoCommand;
import botato.command.UnmarkCommand;
import botato.exception.InvalidCommandException;
import botato.exception.InvalidDateTimeFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    void testParseExitCommand() {
        Command command = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    void testParseListCommand() {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    void testParseMarkCommand() {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    void testParseUnmarkCommand() {
        Command command = Parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    void testParseTodoCommand() {
        Command command = Parser.parse("todo read book");
        assertInstanceOf(TodoCommand.class, command);
    }

    @Test
    void testParseDeadlineCommand() {
        Command command = Parser.parse("deadline return book /by 2023-12-31");
        assertInstanceOf(DeadlineCommand.class, command);
    }

    @Test
    void testParseEventCommand() {
        Command command = Parser.parse("event project meeting /from 2023-12-01 /to 2023-12-02");
        assertInstanceOf(EventCommand.class, command);
    }

    @Test
    void testParseDeleteCommand() {
        Command command = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    void testParseHelpCommand() {
        Command command = Parser.parse("help");
        assertInstanceOf(HelpCommand.class, command);
    }

    @Test
    void testParseUnknownCommand() {
        assertThrows(InvalidCommandException.class, () -> Parser.parse("unknown"));
    }

    @Test
    void testParseInvalidMarkCommand() {
        assertThrows(InvalidCommandException.class, () -> Parser.parse("mark")); // Missing task number
    }

    @Test
    void testParseInvalidUnmarkCommand() {
        assertThrows(InvalidCommandException.class, () -> Parser.parse("unmark")); // Missing task number
    }

    @Test
    void testParseInvalidTodoCommand() {
        assertThrows(InvalidCommandException.class, () -> Parser.parse("todo")); // Missing description
    }

    @Test
    void testParseInvalidDeadlineCommand() {
        assertThrows(InvalidCommandException.class, () -> Parser.parse("deadline")); // Missing details
    }

    @Test
    void testParseInvalidEventCommand() {
        assertThrows(InvalidCommandException.class, () -> Parser.parse("event")); // Missing details
    }

    @Test
    void testParseInvalidDeleteCommand() {
        assertThrows(InvalidCommandException.class, () -> Parser.parse("delete")); // Missing task number
    }

    @Test
    void testParseInvalidEventDate() {
        // Invalid Date
        assertThrows(InvalidDateTimeFormatException.class,
                () -> Parser.parse("event a /from ! /to !").execute(new TaskList(), new Ui()));
    }
}
