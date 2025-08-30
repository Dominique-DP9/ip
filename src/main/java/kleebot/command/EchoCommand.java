package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.TaskList;
import kleebot.ui.Ui;

public class EchoCommand extends Command {
    private final String userInput;

    public EchoCommand(String input) {
        this.userInput = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.echo(userInput);
    }
}
