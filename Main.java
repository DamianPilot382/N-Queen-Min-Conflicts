/**
 * @author Damian Ugalde
 * @date 2020-03-15
 * @version 1.0
 *
 * Project 3
 * CS 4200 - Artificial Intelligence
 * California State Polytechnic University, Pomona
 * Computer Science Department
 *
 * Instructor: Dominick A. Atanasio
 *
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Main extends Application {


    /**
     * Initializes the GUI.
     * @param primaryStage stage to use for this GUI.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

        //Get the info from the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("screen.fxml"));
        
        //Set the title, scene, and unresizable.
        primaryStage.setTitle("CS 4200 - Project 3 | N-Queens with Minimum Conflicts Algorithm");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);

        //Show the GUI.
        primaryStage.show();

    }

    /**
     * Initialize the GUI for the Minimum Conflicts Algorithm.
     * @param args no arguments. Ignored.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
