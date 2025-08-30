package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.TaskList;
import kleebot.task.ToDo;
import kleebot.ui.Ui;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo todo = new ToDo(description);
        tasks.addToList(todo);
    }
}
