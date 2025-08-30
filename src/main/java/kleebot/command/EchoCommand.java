package kleebot.command;

import kleebot.storage.Storage;
import kleebot.task.TaskList;
import kleebot.ui.Ui;

/**
 * Represents a command to echo user input back to them.
 */
public class EchoCommand extends Command {
    private final String userInput;

    /**
     * Constructs a new {@code EchoCommand}.
     *
     * @param input The user input to echo.
     */
    public EchoCommand(String input) {
        this.userInput = input;
    }

    /**
     * Echoes the user input back via the UI.
     *
     * @param tasks   Not used in this command.
     * @param ui      The UI responsible for output.
     * @param storage Not used in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.echo(userInput);
    }
}
