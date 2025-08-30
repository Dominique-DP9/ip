public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws KleeExceptions;
    public boolean isExit() {
        return false;
    }
}

class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are a list of things you've made!!");
        for (int i = 0; i < tasks.getSize(); i++) {
            ui.showMessage((i + 1) + ". " + tasks.getTask(i));
        }
    }
}

class TodoCommand extends Command {
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

class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(description, by);
        tasks.addToList(deadline);
    }
}


class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(description, from, to);
        tasks.addToList(event);
    }
}

class MarkCommand extends Command {
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

class UnmarkCommand extends Command {
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


class DeleteCommand extends Command {
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

class EchoCommand extends Command {
    private final String userInput;
    public EchoCommand(String input) {
        this.userInput = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.echo(userInput);
    }
}

class UnknownCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.throwTantrum();
    }
}

