package chatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that inherits from Task.
 *
 * @author Owen Yeo
 */
public class Deadline extends Task {

    private LocalDateTime deadline;

    private String originalString;

    /**
     * Constructor for a deadline object.
     *
     * @param label Descriptor for the task with deadline
     * @param deadline String representing deadline
     */
    public Deadline(String label, String deadline) {
        super(label);

        assert deadline.length() > 0 : "Deadline cannot be empty";

        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter
            .ofPattern("yyyy-MM-dd HHmm"));
        this.originalString = deadline;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSaveString() {
        return "D " + super.toSaveString() + " | " + originalString;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline
            .format(DateTimeFormatter.ofPattern("MMM dd YYYY ha")) + ")";
    }
}
