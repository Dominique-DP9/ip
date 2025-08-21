import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class KleeBot {

    public String lineBreak = "____________________________________________________________";
    public String kleeLogo =
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

    void greet() {
        System.out.println(lineBreak);
        System.out.println(kleeLogo);
        System.out.println("Hello, I'm Klee, your bestest elf-friend! (◕‿◕✿) \nWhat can I do for you today? :3");
        System.out.println(lineBreak);
    }
    void exit() {
        System.out.println(lineBreak);
        System.out.println("Aww mann, you're leaving already?? (╥﹏╥) ...Well!! Come back soon to play with me again alright!!");
        System.out.println(lineBreak);
    }

    // Impt stuff

    public ArrayList<Task> list = new ArrayList<>();

    void echo(String input) {
        System.out.println(lineBreak);
        System.out.println("\"" + input + "\"" + ", hehee!");
        System.out.println(lineBreak);
    }

    void addToList(Task task) {
        System.out.println(lineBreak);
        list.add(task);
        System.out.println("OK! Adding this to your list!: ");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list!!! Felicitations!!");
        System.out.println(lineBreak);
    }

    // different tasks handlers

    void handleTodo(String[] input) {
        String taskDescription = Arrays.stream(input, 1, input.length)
                                       .reduce("", (a, b) -> a + " " + b);
        addToList(new ToDo(taskDescription));
    }

    void handleDeadline(String[] input) {
        int byIndex = IntStream.range(0, input.length)
                                        .filter(i -> input[i].equals("/by"))
                                        .findFirst()
                                        .orElse(-1);

        String taskDescription = Arrays.stream(input, 1, byIndex)
                .reduce("", (a, b) -> a + " " + b);
        String by = Arrays.stream(input, byIndex + 1, input.length)
                .reduce("", (a, b) -> a + " " + b);
        addToList(new Deadline(taskDescription, by));
    }

    void handleEvent(String[] input) {
        int fromIndex = IntStream.range(0, input.length)
                .filter(i -> input[i].equals("/from"))
                .findFirst()
                .orElse(-1);

        int toIndex = IntStream.range(fromIndex, input.length)
                .filter(i -> input[i].equals("/to"))
                .findFirst()
                .orElse(-1);

        String taskDescription = Arrays.stream(input, 1, fromIndex)
                .reduce("", (a, b) -> a + " " + b);
        String from = Arrays.stream(input, fromIndex + 1, toIndex)
                .reduce("", (a, b) -> a + " " + b);
        String to = Arrays.stream(input, toIndex + 1, input.length)
                .reduce("", (a, b) -> a + " " + b);
        addToList(new Event(taskDescription, from, to));
    }


    void markItem(String[] input) {
        System.out.println(lineBreak); System.out.println("You're amazing, friend!! I've marked this task as DHONE!!:");
        Task task = list.get(Integer.parseInt(input[1]) - 1);
        task.markAsDone();
        System.out.println("\t" + task.toString());
        System.out.println(lineBreak);
    }

    void unmarkItem(String[] input) {
        System.out.println(lineBreak); System.out.println("AWW, it's alright! You can work on this the next time!:");
        Task task = list.get(Integer.parseInt(input[1]) - 1);
        task.unmarkAsDone();
        System.out.println("\t" + task.toString()); System.out.println(lineBreak);
    }

    void readList() {
        System.out.println(lineBreak + "\nHere are a list of things you've made!!");
        for (int i=0; i<list.size(); i++) {
            Task task = list.get(i);
            System.out.println(i+1 + "." + task.toString());
        }
        System.out.println(lineBreak);
    }




    public static void main(String[] args) {
        // Initialise the klee
        KleeBot klee = new KleeBot();
        Scanner textScanner = new Scanner(System.in);

        enum TaskTypes {
            TODO,
            DEADLINE,
            EVENT
        }

        // Program Entry
        klee.greet();

        loop: while (true) { // need to label the loop a name to break out of it w switch-case
            String userInput = textScanner.nextLine();
            String[] splitted = userInput.split("\\s+");
//            for (String word : splitted) System.out.println(word);
            switch (splitted[0]) {
                case "bye":
                    break loop;
                case "todo":
                    klee.handleTodo(splitted);
                    break;
                case "deadline":
                    klee.handleDeadline(splitted);
                    break;
                case "event":
                    klee.handleEvent(splitted);
                    break;
                case "list":
                    klee.readList();
                    break;
                case "mark":
                    klee.markItem(splitted);
                    break;
                case "unmark":
                    klee.unmarkItem(splitted);
                    break;
                default:
                    klee.echo(userInput);
//                    klee.addToList(userInput);
            }
        }

        // Program Exit
        klee.exit();

    }
}