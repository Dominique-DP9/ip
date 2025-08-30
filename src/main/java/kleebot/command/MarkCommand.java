package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.TaskList;
import kleebot.ui.Ui;


/**
 * A command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final String[] input;

    /**
     * Constructs a new {@code MarkCommand}.
     *
     * @param input The parsed user input containing the index of the task to mark.
     */
    public MarkCommand(String[] input) {
        this.input = input;
    }

    /**
     * Marks the specified task as done.
     *
     * @param tasks   The task list containing tasks.
     * @param ui      The UI for displaying messages.
     * @param storage The storage for saving task list updates.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markItem(input);
        ui.showMessage("You're amazing, friend!! I've marked this task as DHONE!!:");
        ui.showMessage("\t" + tasks.getTask(Integer.parseInt(input[1]) - 1).toString());
    }
}
