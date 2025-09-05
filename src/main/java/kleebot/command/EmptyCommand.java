package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.TaskList;
import kleebot.ui.KleeExceptions;
import kleebot.ui.Ui;

public class EmptyCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    @Override
    public String executeGUI(TaskList tasks, Ui ui, Storage storage) throws KleeExceptions {
        return "";
    }
}
