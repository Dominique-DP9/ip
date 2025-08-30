package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.Event;
import kleebot.task.TaskList;
import kleebot.ui.Ui;

/**
 * A command to create an event task.
 */
public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;


    /**
     * Constructs a new {@code EventCommand}.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Adds an event task to the task list.
     *
     * @param tasks   The task list to add the event to.
     * @param ui      The UI to display messages.
     * @param storage The storage used for saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(description, from, to);
        tasks.addToList(event);
    }

    public String getDescription() {
        return description;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
