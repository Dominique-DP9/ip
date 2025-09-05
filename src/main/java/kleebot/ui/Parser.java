package kleebot.ui;

import kleebot.command.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Parser {

    public static Command parse(String fullCommand) throws KleeExceptions {
        String[] parts = fullCommand.split("\\s+");
        String taskDescription;
        switch (parts[0]) {
        case "bye":
            return new ExitCommand();
        case "todo":
            if (parts.length <= 1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_DETAILS.getMessage());
            taskDescription = Arrays.stream(parts, 1, parts.length)
                    .reduce("", (a, b) -> a + " " + b);
            return new TodoCommand(taskDescription);
        case "deadline":
            if (parts.length <= 1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_DETAILS.getMessage());
            int byIndex = IntStream.range(0, parts.length)
                    .filter(i -> parts[i].equals("/by"))
                    .findFirst()
                    .orElse(-1);
            if (byIndex == -1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_BY.getMessage());
            if (byIndex == parts.length - 1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_BY_2.getMessage());
            taskDescription = Arrays.stream(parts, 1, byIndex)
                    .reduce("", (a, b) -> a + " " + b)
                    .trim();
            String by = Arrays.stream(parts, byIndex + 1, parts.length)
                    .reduce("", (a, b) -> a + " " + b)
                    .trim();
            String dated_by = Parser.parseDateStr(by); // returns the string format in MMM dd yyyy if the input is a valid date
            return new DeadlineCommand(taskDescription, dated_by);
        case "event":
            if (parts.length <= 1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_DETAILS.getMessage());
            int fromIndex = IntStream.range(0, parts.length)
                    .filter(i -> parts[i].equals("/from"))
                    .findFirst()
                    .orElse(-1);

            if (fromIndex == -1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_FROM.getMessage());
            // test is separated from others bc the toIndex stream uses fromIndex

            int toIndex = IntStream.range(fromIndex, parts.length)
                    .filter(i -> parts[i].equals("/to"))
                    .findFirst()
                    .orElse(-1);

            if (toIndex == -1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_TO.getMessage());
            if (fromIndex == toIndex - 1) throw new KleeExceptions("Gimmie more info on when it starts!!");
            if (toIndex == parts.length - 1) throw new KleeExceptions("Gimmie more info on when it ends!!");

            taskDescription = Arrays.stream(parts, 1, fromIndex)
                    .reduce("", (a, b) -> a + " " + b)
                    .trim();
            String from = Arrays.stream(parts, fromIndex + 1, toIndex)
                    .reduce("", (a, b) -> a + " " + b)
                    .trim();
            String to = Arrays.stream(parts, toIndex + 1, parts.length)
                    .reduce("", (a, b) -> a + " " + b)
                    .trim();
            String dated_from = Parser.parseDateStr(from);
            String dated_to = Parser.parseDateStr(to);
            return new EventCommand(taskDescription, dated_from, dated_to);
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(parts);
        case "unmark":
            return new UnmarkCommand(parts);
        case "delete":
            return new DeleteCommand(parts[1]);
        case "find":
            if (parts.length == 1) throw new KleeExceptions(Ui.ErrorMessage.MISSING_SEARCH_TERM.getMessage());
            return new FindCommand(parts[1]);
        case "echo":
            return new EchoCommand(fullCommand);
        default:
            return new UnknownCommand();
        }

    }



    public static String parseDateStr(String str) {
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