package kleebot.command;

import kleebot.ui.KleeExceptions;
import kleebot.storage.Storage;
import kleebot.task.TaskList;
import kleebot.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws KleeExceptions;
    
    public boolean isExit() {
        return false;
    }
}


