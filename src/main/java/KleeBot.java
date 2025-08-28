import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.IntStream;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

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

        saveTasksToLocal();
    }


//    String home = System.getProperty("user.home");

    void loadTasks() throws FileNotFoundException {
        File dir = new File("./KleeData");
        File taskFile = new File("./KleeData/taskFile");

        if (dir.exists()) {
//            System.out.println("dir exists at " + dir);
            if (taskFile.exists()) {
                try {
                    Scanner reader = new Scanner(taskFile);
                    while (reader.hasNextLine()) {
                        String data = reader.nextLine();
                        if (data.isEmpty()) break;
                        String[] splitted = data.split("\\s+\\|\\s+"); // regex splits by " | " hopefully
                        String type = splitted[0];
                        boolean done = splitted[1].equals("1");

                        switch (type) {
                            case "T":
                                ToDo tmp = new ToDo(splitted[2]);
                                if (done) { tmp.markAsDone(); }
                                list.add(tmp);
                                break;
                            case "D":
                                Deadline tmpD = new Deadline(splitted[2], splitted[3]);
                                if (done) { tmpD.markAsDone(); }
                                list.add(tmpD);
                                break;
                            case "E":
                                Event tmpE = new Event(splitted[2], splitted[3], splitted[4]);
                                if (done) { tmpE.markAsDone(); }
                                list.add(tmpE);
                                break;
                            default:
                                break;
                        }
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else { // file doesnt exist yet
                try {
                    boolean _s = taskFile.createNewFile();
                    System.out.println("File created" + taskFile.getName());
                } catch (IOException e) {
                    System.out.println("Something went wrong with creating a new file!! >_< I'm so sowwy :((");
                    e.printStackTrace();
                }
            }
        } else { // /data folder doesn't exist yet
            boolean _success = dir.mkdirs();
            System.out.println("Directory created at" + dir);
        }
    }

    String parseDateStr(String str) {
//        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
//                .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy")) // bc dd/mm/yy supremacy
//                .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
//                .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
//                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
//                .toFormatter();
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern("[MM/dd/yyyy]" + "[yyyy/MM/dd]" + "[dd-MM-yyyy]" + "[yyyy-MM-dd]"))
                .toFormatter();
        LocalDate date;
        try {
            date = LocalDate.parse(str, formatter);
        } catch (DateTimeParseException e) {
            return str;
        }
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }


    // Impt stuff

    public ArrayList<Task> list = new ArrayList<>();

    enum ErrorMessage {
        MISSING_DETAILS("Hey! Gimmie more details about this task!"),
        MISSING_BY("Include a timing you'll finish your task by! Using /by ..."),
        MISSING_BY_2("NonoNonoNO! Tell me WHEN you're gonna finish the task by!! Share with me the DATE!!"),
        MISSING_FROM("Include a timing the task starts from! Using /from ..."),
        MISSING_TO("Include a timing when the task ends! Using /to ...");

        private final String message;
        ErrorMessage(String message) { this.message = message; }
        public String getMessage() { return message; }
    }

    void echo(String input) {
        System.out.println(lineBreak);
        System.out.println("\"" + input + "\"" + ", hehee!");
        System.out.println(lineBreak);
    }

    void throwTantrum() {
        System.out.println(lineBreak);
        System.out.println("Huh?? I'm not sure I quite understand o(╥﹏╥)o... could you be more specific please?");
        System.out.println(lineBreak);
    }

    void saveTasksToLocal() {
        try {
            FileWriter writer = new FileWriter("./KleeData/taskFile", false); // 2nd param decides whether to overwrite the file or not
            for (Task task : list) {
//                System.out.println("Writing data to local!");
                String fileString = task.getType() + " | " + (task.getStatus() ? "1" : "0") +  " | " + task.getDescription().trim();
                if (task instanceof Deadline d) {
                    fileString += " | " + d.getBy();
                } else if (task instanceof Event e) {
                    fileString += " | " + e.getFrom() + " | " + e.getTo();
                }
                System.out.println(fileString);
                writer.write(fileString + "\n");
            }
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    void handleTodo(String[] input) throws KleeExceptions{
        if (input.length <= 1) throw new KleeExceptions(ErrorMessage.MISSING_DETAILS.getMessage());
        String taskDescription = Arrays.stream(input, 1, input.length)
                                       .reduce("", (a, b) -> a + " " + b);
        addToList(new ToDo(taskDescription));
    }

    void handleDeadline(String[] input) throws KleeExceptions {
        if (input.length <= 1) throw new KleeExceptions(ErrorMessage.MISSING_DETAILS.getMessage());
        int byIndex = IntStream.range(0, input.length)
                                        .filter(i -> input[i].equals("/by"))
                                        .findFirst()
                                        .orElse(-1);
        if (byIndex == -1) throw new KleeExceptions(ErrorMessage.MISSING_BY.getMessage());
        if (byIndex == input.length - 1) throw new KleeExceptions(ErrorMessage.MISSING_BY_2.getMessage());
        String taskDescription = Arrays.stream(input, 1, byIndex)
                .reduce("", (a, b) -> a + " " + b)
                .trim();
        String by = Arrays.stream(input, byIndex + 1, input.length)
                .reduce("", (a, b) -> a + " " + b)
                .trim();
        String dated_by = parseDateStr(by); // returns the string format in MMM dd yyyy if the input is a valid date

        addToList(new Deadline(taskDescription, dated_by));
    }

    void handleEvent(String[] input) throws KleeExceptions{
        if (input.length <= 1) throw new KleeExceptions(ErrorMessage.MISSING_DETAILS.getMessage());
        int fromIndex = IntStream.range(0, input.length)
                .filter(i -> input[i].equals("/from"))
                .findFirst()
                .orElse(-1);

        if (fromIndex == -1) throw new KleeExceptions(ErrorMessage.MISSING_FROM.getMessage());
        // test is separated from others bc the toIndex stream uses fromIndex

        int toIndex = IntStream.range(fromIndex, input.length)
                .filter(i -> input[i].equals("/to"))
                .findFirst()
                .orElse(-1);

        if (toIndex == -1) throw new KleeExceptions(ErrorMessage.MISSING_TO.getMessage());
        if (fromIndex == toIndex - 1) throw new KleeExceptions("Gimmie more info on when it starts!!");
        if (toIndex == input.length - 1) throw new KleeExceptions("Gimmie more info on when it ends!!");

        String taskDescription = Arrays.stream(input, 1, fromIndex)
                .reduce("", (a, b) -> a + " " + b)
                .trim();
        String from = Arrays.stream(input, fromIndex + 1, toIndex)
                .reduce("", (a, b) -> a + " " + b)
                .trim();
        String to = Arrays.stream(input, toIndex + 1, input.length)
                .reduce("", (a, b) -> a + " " + b)
                .trim();
        String dated_from = parseDateStr(from);
        String dated_to = parseDateStr(to);
        addToList(new Event(taskDescription, dated_from, dated_to));
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

    void delete(String[] input) {
        System.out.println(lineBreak); System.out.println("Okay!!! I've removed this item from your list:");
        Task task = list.get(Integer.parseInt(input[1]) - 1);
        System.out.println("\t" + task.toString());
        list.remove(task);
        System.out.println("Now you have " + list.size() + " tasks left in the list!!! ");
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

        // Load tasks from local

        try {
            klee.loadTasks();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        loop: while (true) { // need to label the loop a name to break out of it w switch-case
            String userInput = textScanner.nextLine();
            String[] splitted = userInput.split("\\s+");
//            for (String word : splitted) System.out.println(word);
            try {
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
                case "delete":
                    klee.delete(splitted);
                    break;
                case "echo":
                    klee.echo(userInput);
                default:
                    klee.throwTantrum();
                    // klee.addToList(userInput);
                }
            } catch (KleeExceptions e) {
                System.out.println(e.getMessage());
            }
        }

        // Program Exit
        klee.exit();

    }
}