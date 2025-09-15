package kleebot.ui;

import java.util.List;

import kleebot.task.Task;
import kleebot.task.TaskList;

public class Ui {

    public static final String LINE_BREAK = "____________________________________________________________";
    public static final String KLEE_LOGO =
            "⠈⢃⠢⠀⠀⠀⠀⢸⠀⠀⠀⣀⣠⣤⣤⣧⣦⣤⣄⡀⠀⠀⠀⠀⢢⠀⠀⠐⡄\n" +
                    "⠀⠀⡆⠀⣇⠀⠀⢈⣷⣶⡿⣟⣷⣻⣞⡶⣟⡶⣭⣟⠷⣦⣀⣠⠂⠀⢀⢠⠓\n" +
                    "⠀⠀⠤⣀⢸⡴⠾⣟⣿⢾⣽⣻⣞⡷⣯⣿⣽⣿⣿⣿⡿⢟⣋⣁⡀⡌⣀⠊⠈\n" +
                    "⠀⠀⠈⣶⠋⡐⠆⣹⣾⣟⣾⠷⠛⠋⠉⠉⠉⠉⠉⣉⠙⠳⢾⣶⣿⡎⠁⠀⠀\n" +
                    "⠀⠀⢰⡇⠤⢁⣾⣯⡿⠋⠀⠀⠀⠈⡜⠀⠀⠀⠀⠈⢧⡀⠀⠉⠻⣷⠀⠀⠀\n" +
                    "⠀⠀⠘⣷⣮⣽⣻⡟⠀⠀⠀⢀⡟⠀⡇⠀⠀⠈⠀⠀⠈⣷⢆⠀⠀⠘⢧⠀⠀\n" +
                    "⠀⠀⠀⣿⣟⣿⣿⠀⠀⡀⠀⣾⣠⢐⡥⠖⠋⡀⠡⠀⠚⡿⣜⡆⠠⠀⢌⣧⠀\n" +
                    "⠀⠀⠀⠸⣿⣿⣿⡀⠀⡀⢰⡟⠁⣩⣾⣧⣶⠀⠁⢨⣤⣏⢸⡇⢀⠁⢸⣿⠀\n" +
                    "⠀⠀⠀⠀⢻⣿⡽⣶⡄⠀⢸⣿⡿⢹⣿⣧⡏⠑⢤⣸⣿⣿⢷⡇⢀⠊⣼⠏⠀\n" +
                    "⠀⠀⠀⣠⠾⢶⣦⣤⣿⣦⣌⣉⠓⠚⠯⠿⠃⠀⠀⢻⣿⠏⣾⣓⣤⡾⠃⠀⠀\n" +
                    "⠀⣰⠞⣁⣼⠯⣼⡉⠑⠶⡮⣍⠁⠀⠀⢸⡟⣳⡶⠀⠀⠈⣻⠏⠻⣆⠀⠀⠀\n" +
                    "⣴⠋⠐⣾⢣⢏⡖⣷⡀⠄⠙⠪⣟⡷⢶⣦⣯⣭⣤⣴⠖⡻⣝⡆⠡⠈⢷⡀⠀\n" +
                    "⡣⢸⠰⣧⢋⡞⣼⣿⣷⣌⠀⣥⠞⠀⢐⣾⡏⢭⠙⣾⣔⠀⠜⣿⠀⡄⣧⡧⠀\n" +
                    "⠀⠲⠾⢧⣯⡜⣿⣿⣿⣿⠿⣿⣿⣶⣷⠿⣌⣒⣼⢾⣷⣶⣶⣾⣆⡼⠃⠀⠀\n" +
                    "⠀⠀⠀⠀⠈⢛⣿⣿⣿⡇⠀⠀⠀⠉⢻⣶⡟⠹⣷⣎⠉⠉⠉⠉⢻⠄⠀⠀⠀";


    /**
     * Contains all error messages to be thrown as part of {@link KleeExceptions}.
     */
    public enum ErrorMessage {
        MISSING_DETAILS("Hey! Gimmie more details about this task!"),
        MISSING_BY("Include a timing you'll finish your task by! Using /by ..."),
        MISSING_BY_2("NonoNonoNO! Tell me WHEN you're gonna finish the task by!! Share with me the DATE!!"),
        MISSING_FROM("Include a timing the task starts from! Using /from ..."),
        MISSING_TO("Include a timing when the task ends! Using /to ..."),
        MISSING_SEARCH_TERM("Hey! Tell me what task you're searching for!"),
        MISSING_DELETE("What task would you like to delete!"),
        MISSING_PRIORITY("Hey! What priority should I assign this task?");

        private final String message;
        ErrorMessage(String message) { this.message = message; }
        public String getMessage() { return message; }
    }

    /**
     * Adds a simple line break.
     */
    public void showLine() {
        System.out.println(LINE_BREAK);
    }

    /**
     * Shows a message to the whole world.
     * @param msg The message to be shown.
     */
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * The first thing that the user sees!
     */
    public void greet() {
        showLine();
        System.out.println(KLEE_LOGO);
        System.out.println("Hello, I'm Klee, your bestest elf-friend! (◕‿◕✿) \nWhat can I do for you today? :3");
        showLine();
    }

    /**
     * Klee says bye to you!
     */
    public void exit() {
        System.out.println("Aww mann, you're leaving already?? (╥﹏╥) ...Well!! Come back soon to play with me again alright!!");
    }

    /**
     * Klee likes to mimic you!
     * @param input The string to be parroted back to you.
     */
    public void echo(String input) {
        System.out.println("\"" + input + "\"" + ", hehee!");
    }

    public void updateList(Task task, int totalTasks) {
        System.out.println("OK! Adding this to your list!: ");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + totalTasks + " tasks in the list!!! Felicitations!!");
    }

    public void updateDelete(Task task, int totalTasks) {
        showMessage("Okay!!! I've removed this item from your list:");
        showMessage("\t" + task.toString());
        showMessage("Now you have " + totalTasks + " tasks left in the list!!! ");
    }

    public void updateMark(Task task) {
        showMessage("You're amazing, friend!! I've marked this task as DHONE!!:");
        showMessage("\t" + task);
    }

    public void updateUnmark(Task task) {
        showMessage("AWW, it's alright! You can work on this the next time!:");
        showMessage("\t" + task);
    }

    public void updatePriority(Task task, int priority) {
        showMessage("OKAY!! I've reordered your priorities!!");
        showMessage("\t" + task + " now has a priority of:" + priority + "!!!!!");
    }


    /**
     * Throws a tantrum.
     */
    public void throwTantrum() {
        System.out.println("Huh?? I'm not sure I quite understand o(╥﹏╥)o... could you be more specific please?");
    }



    // ========== GUI METHODS ==========
    public String formatAddTask(Task task, int totalTasks) {
        return "OK! Adding this to your list!:\n\t" + task
                + "\nNow you have " + totalTasks + " tasks in the list!!! Felicitations!!";
    }

    // ========== Delete ==========
    public String formatDeleteTask(Task task, int totalTasks) {
        return "Okay!!! I've removed this item from your list:\n\t" + task
                + "\nNow you have " + totalTasks + " tasks in the list!!!!! ";
    }

    public String formatEcho(String input) {
        return "\"" + input + "\"" + ", hehee!";
    }

    public String formatExit() {
        return "Aww mann, you're leaving already?? (╥﹏╥) ...Well!! Come back soon to play with me again alright!!";
    }

    public String formatTantrum() {
        return "Huh?? I'm not sure I quite understand o(╥﹏╥)o... could you be more specific please?";
    }

    // ========== Mark / Unmark ==========
    public String formatMarkTask(Task task) {
        return "You're amazing, friend!! I've marked this task as DHONE!!:\n\t " + task;
    }

    public String formatUnmarkTask(Task task) {
        return "AWW, it's alright! You can work on this the next time!:\n  " + task;
    }

    public String formatPriority(Task task, int value) {
        return "OKAY!! I've reordered your priorities!!\n\t" + task + " now has a priority of:" + value + "!!!!!";
    }

    public String formatList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return "AWWW mannn :(( your task list is empty!";
        }
        StringBuilder sb = new StringBuilder("Here are a list of things you've made!!\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.getTask(i)).append("\n");
        }
        return sb.toString();
    }


    // ========== Find ==========
    public String formatFind(List<String> list) {
        if (list.isEmpty()) {
            return "AWWW man :( I couldn't find anything matching your request T_T";
        }
        StringBuilder sb = new StringBuilder("OKAY!! Klee worked really hard to find the matching tasks based on your request!! >_<\n");
//        for (int i = 0; i < list.size(); i++) {
//            sb.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
//        }
        for (String s: list) {
            sb.append(s).append("\n");
        }
        return sb.toString();
    }



}
