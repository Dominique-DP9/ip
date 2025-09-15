package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.Task;
import kleebot.task.TaskList;
import kleebot.ui.KleeExceptions;
import kleebot.ui.Ui;


public class SetpriorityCommand extends Command {
    private final int newValue;
    private final int index;

    public SetpriorityCommand(int index, int value) {
        this.newValue = value;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KleeExceptions {
        Task task = tasks.getTask(index);
        task.setPriority(newValue);
        ui.updatePriority(task, newValue);
    }

    @Override
    public String executeGUI(TaskList tasks, Ui ui, Storage storage) throws KleeExceptions {
        Task task = tasks.getTask(index);
        task.setPriority(newValue);
        return ui.formatPriority(task, newValue);
    }
}
