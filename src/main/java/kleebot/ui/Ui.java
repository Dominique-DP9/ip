package kleebot.ui;

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


    public enum ErrorMessage {
        MISSING_DETAILS("Hey! Gimmie more details about this task!"),
        MISSING_BY("Include a timing you'll finish your task by! Using /by ..."),
        MISSING_BY_2("NonoNonoNO! Tell me WHEN you're gonna finish the task by!! Share with me the DATE!!"),
        MISSING_FROM("Include a timing the task starts from! Using /from ..."),
        MISSING_TO("Include a timing when the task ends! Using /to ..."),
        MISSING_SEARCH_TERM("Hey! Tell me what task you're searching for!");

        private final String message;
        ErrorMessage(String message) { this.message = message; }
        public String getMessage() { return message; }
    }

    public void showLine() {
        System.out.println(LINE_BREAK);
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void greet() {
        showLine();
        System.out.println(KLEE_LOGO);
        System.out.println("Hello, I'm Klee, your bestest elf-friend! (◕‿◕✿) \nWhat can I do for you today? :3");
        showLine();
    }

    public void exit() {
        System.out.println("Aww mann, you're leaving already?? (╥﹏╥) ...Well!! Come back soon to play with me again alright!!");
    }

    public void echo(String input) {
        System.out.println("\"" + input + "\"" + ", hehee!");
    }

    public void throwTantrum() {
        System.out.println("Huh?? I'm not sure I quite understand o(╥﹏╥)o... could you be more specific please?");
    }




}
