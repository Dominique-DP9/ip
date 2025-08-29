import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.IntStream;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addToList(Task task) {
        System.out.println(Ui.LINE_BREAK);
        tasks.add(task);
        System.out.println("OK! Adding this to your list!: ");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list!!! Felicitations!!");
        System.out.println(Ui.LINE_BREAK);
    }

    public void delete(String[] input) {
        Task task = tasks.get(Integer.parseInt(input[1]) - 1);
        tasks.remove(task);
        System.out.println("\t" + task.toString());
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void markItem(String[] input) {
        System.out.println("You're amazing, friend!! I've marked this task as DHONE!!:");
        Task task = tasks.get(Integer.parseInt(input[1]) - 1);
        task.markAsDone();
        System.out.println("\t" + task.toString());
    }

    public void unmarkItem(String[] input) {
        System.out.println("AWW, it's alright! You can work on this the next time!:");
        Task task = tasks.get(Integer.parseInt(input[1]) - 1);
        task.unmarkAsDone();
        System.out.println("\t" + task.toString());
    }

    public void readList() {
        for (int i=0; i<tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(i+1 + "." + task.toString());
        };
    }

    void handleTodo(String[] input) throws KleeExceptions{
        if (input.length <= 1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_DETAILS.getMessage());
        String taskDescription = Arrays.stream(input, 1, input.length)
                .reduce("", (a, b) -> a + " " + b);
        addToList(new ToDo(taskDescription));
    }
    void handleDeadline(String[] input) throws KleeExceptions {
        if (input.length <= 1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_DETAILS.getMessage());
        int byIndex = IntStream.range(0, input.length)
                .filter(i -> input[i].equals("/by"))
                .findFirst()
                .orElse(-1);
        if (byIndex == -1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_BY.getMessage());
        if (byIndex == input.length - 1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_BY_2.getMessage());
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
        if (input.length <= 1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_DETAILS.getMessage());
        int fromIndex = IntStream.range(0, input.length)
                .filter(i -> input[i].equals("/from"))
                .findFirst()
                .orElse(-1);

        if (fromIndex == -1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_FROM.getMessage());
        // test is separated from others bc the toIndex stream uses fromIndex

        int toIndex = IntStream.range(fromIndex, input.length)
                .filter(i -> input[i].equals("/to"))
                .findFirst()
                .orElse(-1);

        if (toIndex == -1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_TO.getMessage());
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


    public String parseDateStr(String str) {
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
}
