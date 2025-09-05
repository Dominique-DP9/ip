package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.Task;
import kleebot.task.TaskList;
import kleebot.ui.KleeExceptions;
import kleebot.ui.Ui;

/**
 * A command to delete a task from the task list.
 */

public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a new {@code DeleteCommand}.
     *
     * @param input The string format index of the task to delete, corresponding to its position
     *              on the task list.
     */
    public DeleteCommand(String input) {
        this.index = Integer.parseInt(input);
    }

    /**
     * Deletes the specified task from the task list.
     *
     * @param tasks   The task list containing tasks.
     * @param ui      The UI for displaying messages.
     * @param storage The storage for saving task list updates.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Okay!!! I've removed this item from your list:");
        tasks.delete(index);
        ui.showMessage("Now you have " + tasks.getTasks().size() + " tasks left in the list!!! ");

    }

    @Override
    public String executeGUI(TaskList tasks, Ui ui, Storage storage) throws KleeExceptions {
        Task toBeDeleted = tasks.getTask(index);
        tasks.delete(index);
        return ui.formatDeleteTask(toBeDeleted, tasks.getSize());
    }
}
