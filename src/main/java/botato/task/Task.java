package botato.task;

import botato.exception.InvalidDateTimeFormatException;
import botato.exception.SameStatusException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a description and a completion status.
 * This class is serializable, allowing instances to be serialized and saved to storage.
 */
public class Task implements Serializable {
    protected boolean isDone;
    protected String description;

    /**
     * Checks task completion and returns corresponding String status icon
     * @return "X" if Task has been marked as completed, " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Set task status.
     * @param isDone sets task status
     * @throws SameStatusException if task is already complete / incomplete
     */
    public void complete(boolean isDone)  {
        if (this.isDone == isDone) {
            throw new SameStatusException(isDone);
        }
        this.isDone = isDone;
    }
    /**
     * Parses a given date string into a {@link LocalDateTime} object based on various supported date formats.
     * If the date string does not include a time component, the provided {@link LocalTime} defaultTime is used
     * to construct the {@link LocalDateTime} object.
     * <p>
     * Supported date formats include:
     * - "yyyy-MM-dd HH:mm:ss"
     * - "yyyy-MM-dd HH:mm"
     * - "yyyy-MM-dd"
     * - "dd/MM/yyyy HH:mm:ss"
     * - "dd/MM/yyyy HH:mm"
     * - "dd/MM/yyyy"
     * - "dd/MM/yy HH:mm:ss"
     * - "dd/MM/yy HH:mm"
     * - "dd/MM/yy"
     * - "dd MMM yyyy HH:mm:ss"
     * - "dd MMM yyyy HH:mm"
     * - "dd MMM yyyy"
     *</p>
     * @param dateStr     The date string to parse.
     * @param defaultTime The default time to use if the date string does not include a time component.
     * @return A {@link LocalDateTime} object representing the parsed date and time or null if the format is
     * unsupported.
     */
    public LocalDateTime parseDate(String dateStr, LocalTime defaultTime) {
        try {
            if (dateStr.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
                return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }
            if (dateStr.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}")) {
                return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            }
            if (dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                LocalDate date = LocalDate.parse(dateStr);
                return LocalDateTime.of(date, defaultTime);
            }
            if (dateStr.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}")) {
                return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            }
            if (dateStr.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}")) {
                return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            }
            if (dateStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                return LocalDateTime.of(date, defaultTime);
            }
            if (dateStr.matches("\\d{2}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
                return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss"));
            }
            if (dateStr.matches("\\d{2}/\\d{2}/\\d{2} \\d{2}:\\d{2}")) {
                return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"));
            }
            if (dateStr.matches("\\d{2}/\\d{2}/\\d{2}")) {
                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yy"));
                return LocalDateTime.of(date, defaultTime);
            }
            if (dateStr.matches("\\d{2} \\w{3} \\d{4} \\d{2}:\\d{2}:\\d{2}")) {
                return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss"));
            }
            if (dateStr.matches("\\d{2} \\w{3} \\d{4} \\d{2}:\\d{2}")) {
                return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
            }
            if (dateStr.matches("\\d{2} \\w{3} \\d{4}")) {
                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd MMM yyyy"));
                return LocalDateTime.of(date, defaultTime);
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        }
        return null;
    }

}