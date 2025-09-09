package kleebot.storage;

import kleebot.task.Deadline;
import kleebot.task.Event;
import kleebot.task.Task;
import kleebot.task.ToDo;

import java.io.*;
import java.util.*;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
//        File dir = new File("./KleeData");
        File file = new File(filePath);
        // if (dir.exists()) // not sure if this will still be needed?
        if (file.exists()) {
            try {
                Scanner reader = new Scanner(file);
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
                        tasks.add(tmp);
                        break;
                    case "D":
                        Deadline tmpD = new Deadline(splitted[2], splitted[3]);
                        if (done) { tmpD.markAsDone(); }
                        tasks.add(tmpD);
                        break;
                    case "E":
                        Event tmpE = new Event(splitted[2], splitted[3], splitted[4]);
                        if (done) { tmpE.markAsDone(); }
                        tasks.add(tmpE);
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
                boolean _s = file.createNewFile();
                System.out.println("File created" + file.getName());
            } catch (IOException e) {
                System.out.println("Something went wrong with creating a new file!! >_< I'm so sowwy :((");
                e.printStackTrace();
            }
        }
//        } else { // /data folder doesn't exist yet
//            boolean _success = dir.mkdirs();
//            System.out.println("Directory created at" + dir);
//        }
        return tasks;
    }

    public void saveTasksToLocal(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath, false); // 2nd param decides whether to overwrite the file or not
            for (Task task : tasks) {
//                System.out.println("Writing data to local!");
                String fileString = task.getType() + " | " + (task.getStatus() ? "1" : "0") +  " | " + task.getDescription().trim();
                if (task instanceof Deadline d) {
                    fileString += " | " + d.getBy();
                } else if (task instanceof Event e) {
                    fileString += " | " + e.getFrom() + " | " + e.getTo();
                }
//                System.out.println(fileString); // debug
                writer.write(fileString + "\n");
            }
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

