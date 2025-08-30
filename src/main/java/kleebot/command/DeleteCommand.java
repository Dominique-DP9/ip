package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.TaskList;
import kleebot.ui.Ui;

public class DeleteCommand extends Command {
    private final String[] input;

    public DeleteCommand(String[] input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Okay!!! I've removed this item from your list:");
        tasks.delete(input);
        ui.showMessage("Now you have " + tasks.getTasks().size() + " tasks left in the list!!! ");

    }
}
