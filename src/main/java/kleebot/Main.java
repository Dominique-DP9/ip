package kleebot;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import kleebot.controller.MainWindow;
import kleebot.view.DialogBox;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static final String DEFAULT_FILE_PATH = "KleeData/taskFile";

    private KleeBot kleeBot = new KleeBot(DEFAULT_FILE_PATH);


//    public Main(String filePath) {
//
//    }
//
//    public Main() {
//        this(DEFAULT_FILE_PATH);
//    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setKlee(kleeBot);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
