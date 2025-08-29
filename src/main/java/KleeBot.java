
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.IntStream;


public class KleeBot {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public KleeBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
//            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        Scanner textScanner = new Scanner(System.in);
//        boolean isExit = false;
        loop: while (true) {
            String userInput = textScanner.nextLine();
            String[] splitted = userInput.split("\\s+");
            try {
                switch (splitted[0]) {
                    case "bye":
                        break loop;
                    case "todo":
                        tasks.handleTodo(splitted);
                        break;
                    case "deadline":
                        tasks.handleDeadline(splitted);
                        break;
                    case "event":
                        tasks.handleEvent(splitted);
                        break;
                    case "list":
                        ui.showLine();
                        ui.showMessage("\nHere are a list of things you've made!!");
                        tasks.readList();
                        ui.showLine();
                        break;
                    case "mark":
                        ui.showLine();
                        tasks.markItem(splitted);
                        ui.showLine();
                        break;
                    case "unmark":
                        ui.showLine();
                        tasks.unmarkItem(splitted);
                        ui.showLine();
                        break;
                    case "delete":
                        ui.showLine();
                        ui.showMessage("Okay!!! I've removed this item from your list:");
                        tasks.delete(splitted);
                        ui.showMessage("Now you have " + tasks.getTasks().size() + " tasks left in the list!!! ");
                        ui.showLine();
                        break;
                    case "echo":
                        ui.echo(userInput);
                        break;
                    default:
                        ui.throwTantrum();
                        break;
                    // klee.addToList(userInput);
                }
            } catch (KleeExceptions e) {
                System.out.println(e.getMessage());
            }
        }
        ui.exit();
        storage.saveTasksToLocal(tasks.getTasks());

    }



    public static void main(String[] args) {
//        enum TaskTypes {
//            TODO,
//            DEADLINE,
//            EVENT
//        }

        // Initialise the klee
        new KleeBot("KleeData/taskFile").run();

    }
}