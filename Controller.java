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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

public class Controller {

    //Create a reference to all the objects in the GUI that will be needed.
    @FXML private Pane boardPane;
    @FXML private Pane parentGrid;
    @FXML private Button minConflictAlgBtn;
    @FXML private Label attackingLbl;
    @FXML private Label timeLbl;

    /**
     * Initializes the controller by adding the action listener to the button
     * and creating the GUI chess board.
     */
    @FXML
    private void initialize(){

        //Create the chessboard and add it to the GUI.
        ChessBoard board = new ChessBoard(25);
        boardPane.getChildren().add(board);

        //Add the event listener for the button.
        minConflictAlgBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                //Solve the board and place it on the board.
                //Also change the label for the amount of time it took to solve.
                timeLbl.setText(board.minConflictAlgorithm() + "");
                board.placeQueens();

                //Set the text for the amount of attacking queens.
                attackingLbl.setText(board.getPuzzle().getAttackingValue()+"");
            }
        });

    }

}
