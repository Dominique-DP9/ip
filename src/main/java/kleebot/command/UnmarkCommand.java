package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.TaskList;
import kleebot.ui.Ui;

public class UnmarkCommand extends Command {
    private final String[] input;

    public UnmarkCommand(String[] input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmarkItem(input);
        ui.showMessage("AWW, it's alright! You can work on this the next time!:");
        ui.showMessage("\t" + tasks.getTask(Integer.parseInt(input[1]) - 1).toString());
    }
}
