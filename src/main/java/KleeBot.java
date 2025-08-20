import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

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

    public ArrayList<String> list = new ArrayList<>();

    void echo(String input) {
        System.out.println(lineBreak);
        System.out.println("\"" + input + "\"" + ", hehee!");
        System.out.println(lineBreak);
    }

    void addToList(String input) {
        System.out.println(lineBreak);
        list.add(input);
        System.out.println("OK! Adding to your list!: " + input);
        System.out.println(lineBreak);
    }

    void readList() {
        System.out.println(lineBreak + "\nHere are a list of things you've made!!");
        for (int i=0; i<list.size(); i++) {
            System.out.println(i+1 + ". " + list.get(i));
        }
        System.out.println(lineBreak);
    }




    public static void main(String[] args) {
        // Initialise the klee
        KleeBot klee = new KleeBot();
        Scanner textScanner = new Scanner(System.in);

        // Program Entry
        klee.greet();

        loop: while (true) { // need to label the loop a name to break out of it w switch-case
            String userInput = textScanner.nextLine();
            String[] splitted = userInput.split(" ");
            switch (splitted[0]) {
                case "bye":
                    break loop;
                case "list":
                    klee.readList();
                    break;
                default:
                    // klee.echo(userInput);
                    klee.addToList(userInput);
            }
        }

        // Program Exit
        klee.exit();

    }
}

