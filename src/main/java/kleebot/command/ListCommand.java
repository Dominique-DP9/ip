package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.TaskList;
import kleebot.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are a list of things you've made!!");
        for (int i = 0; i < tasks.getSize(); i++) {
            ui.showMessage((i + 1) + ". " + tasks.getTask(i));
        }
    }
}
