package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.Deadline;
import kleebot.task.TaskList;
import kleebot.ui.Ui;

public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(description, by);
        tasks.addToList(deadline);
    }
}
