package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.TaskList;
import kleebot.ui.Ui;

public class MarkCommand extends Command {
    private final String[] input;

    public MarkCommand(String[] input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markItem(input);
        ui.showMessage("You're amazing, friend!! I've marked this task as DHONE!!:");
        ui.showMessage("\t" + tasks.getTask(Integer.parseInt(input[1]) - 1).toString());
    }
}
